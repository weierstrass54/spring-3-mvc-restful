package ru.weierstrass.components.cache;

import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TTLConcurrentMapCache extends ConcurrentMapCache {

    private final ScheduledExecutorService _ttlService;
    private final long _ttl;
    private final TimeUnit _timeUnit;

    protected TTLConcurrentMapCache( String name, ConcurrentMap<Object, Object> store, long ttl, TimeUnit unit ) {
        super( name, store, false, null );
        _ttl = ttl;
        _timeUnit = unit;
        _ttlService = Executors.newSingleThreadScheduledExecutor( r -> new Thread( r, "TTLConcurrentMapCache{" + name + "}" ) );
    }

    @Override
    public void put( Object key, Object value ) {
        super.put( key, value );
        if ( _ttl > 0 ) {
            _ttlService.scheduleAtFixedRate( () -> evict( key ), _ttl, _ttl, _timeUnit );
        }
    }

}
