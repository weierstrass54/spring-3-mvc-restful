package ru.weierstrass.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation( ContentNegotiationConfigurer configurer ) {
        super.configureContentNegotiation( configurer );
        configurer.defaultContentType( MediaType.APPLICATION_JSON_UTF8 );
    }

    @Bean
    public DataSource db() throws ClassNotFoundException {
        Class.forName( "com.impossibl.postgres.jdbc.PGDriver" );
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl( "jdbc:pgsql://192.168.128.233:5432/opentech" );
        config.setUsername( "php-asup" );
        config.setPassword( "Wpn6U7F1vgWP" );
        config.addDataSourceProperty( "cachePrepStmts", "true" );
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource( config );
    }

}
