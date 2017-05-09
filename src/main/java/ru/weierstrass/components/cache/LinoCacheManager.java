package ru.weierstrass.components.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import ru.weierstrass.pglino.common.LinoConfig;
import ru.weierstrass.pglino.common.LinoHandler;
import ru.weierstrass.pglino.common.LinoListener;
import ru.weierstrass.pglino.pg.PgLinoListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLException;

public class LinoCacheManager extends ConcurrentMapCacheManager {

    private static final Logger _log = LoggerFactory.getLogger( LinoCacheManager.class );

    private String _name;
    private String _host;
    private String _user;
    private String _password;
    private int _threadCount;

    private LinoListener _listener;

    public LinoCacheManager( LinoCacheProperties properties ) {
        _name = properties.getName();
        _host = properties.getHost();
        _user = properties.getUser();
        _password = properties.getPassword();
        _threadCount = properties.getThreadCount();
    }

    @Override
    protected Cache createConcurrentMapCache( String name ) {
        _log.info( "Add new cache with name {}", name );
        _listener.addHandler( new LinoHandler() {

            @Override
            public String getChannel() {
                return name;
            }

            @Override
            public Runnable process( String notify ) {
                return () -> evictByNotify( name, notify );
            }

        } );
        return super.createConcurrentMapCache( name );
    }

    private void evictByNotify( String cacheName, String notify ) {
        _log.info( "Cache {} eviction with notify {}", cacheName, notify );
        getCache( cacheName ).evict( notify );
    }

    @PostConstruct
    private void init() {
        LinoConfig config = new LinoConfig( _host, _user, _password, _threadCount );
        try {
            _log.info( "LinoCache start.." );
            _listener = new PgLinoListener( _name, config );
        }
        catch( SQLException e ) {
            _log.error( "LinoCacheManager init error: {}", e.getLocalizedMessage(), e );
        }
    }

    @PreDestroy
    private void destroy() {
        _listener.destroy();
    }

}