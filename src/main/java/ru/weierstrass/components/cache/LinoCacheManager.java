package ru.weierstrass.components.cache;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import ru.weierstrass.pglino.common.LinoConfig;
import ru.weierstrass.pglino.common.LinoHandler;
import ru.weierstrass.pglino.common.LinoListener;
import ru.weierstrass.pglino.pg.PgLinoListener;

public class LinoCacheManager extends ConcurrentMapCacheManager {

    private static final Logger _log = LoggerFactory.getLogger(LinoCacheManager.class);

    private final LinoCacheProperties _properties;
    private LinoListener _listener;

    public LinoCacheManager(LinoCacheProperties properties) {
        _properties = properties;
    }

    @Override
    protected Cache createConcurrentMapCache(String name) {
        _log.debug("Add new cache with name {}", name);
        _listener.addHandler(new LinoHandler() {

            @Override
            public String getChannel() {
                return name;
            }

            @Override
            public Runnable process(String notify) {
                return () -> evictByNotify(name, notify);
            }

        });
        return new ConcurrentMapCache(name, new LinoCacheStore(256), false);
    }

    private void evictByNotify(String cacheName, String notify) {
        _log.debug("Cache {} eviction with notify {}", cacheName, notify);
        getCache(cacheName).evict(notify);
    }

    @PostConstruct
    private void init() {
        LinoConfig config = new LinoConfig(_properties.getHost(), _properties.getUser(),
            _properties.getPassword(), _properties.getThreadCount());
        try {
            _log.info("LinoCache start..");
            _listener = new PgLinoListener(_properties.getName(), config);
        } catch (SQLException e) {
            _log.error("LinoCacheManager init error: {}", e.getLocalizedMessage(), e);
        }
    }

    @PreDestroy
    private void destroy() {
        _listener.destroy();
    }

}
