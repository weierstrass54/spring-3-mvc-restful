package ru.weierstrass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.weierstrass.components.Db;

import java.sql.SQLException;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation( ContentNegotiationConfigurer configurer ) {
        super.configureContentNegotiation( configurer );
        configurer.defaultContentType( MediaType.APPLICATION_JSON_UTF8 );
    }

    @Bean
    public Db db() throws SQLException {
        return new Db( "192.168.128.233:5432/opentech", "php-asup", "Wpn6U7F1vgWP" );
    }

}
