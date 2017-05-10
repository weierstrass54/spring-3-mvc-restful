package ru.weierstrass.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.weierstrass.components.cache.LinoCacheManager;
import ru.weierstrass.components.cache.LinoCacheProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@EnableConfigurationProperties( LinoCacheProperties.class )
public class CacheConfig {

    private static final Logger _log = LoggerFactory.getLogger( CacheConfig.class );

    private LinoCacheProperties _properties;

    @Autowired
    public CacheConfig( LinoCacheProperties properties ) {
        _properties = properties;
    }

    @Bean
    public CacheManager cacheManager() {
        return new LinoCacheManager( _properties );
    }

    public CacheManager ttlCacheManager() {
        return new LinoCacheManager( _properties, 1, TimeUnit.HOURS );
    }

}
