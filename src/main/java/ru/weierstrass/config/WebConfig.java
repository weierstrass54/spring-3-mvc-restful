package ru.weierstrass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation( ContentNegotiationConfigurer configurer ) {
        super.configureContentNegotiation( configurer );
        configurer.defaultContentType( MediaType.APPLICATION_JSON_UTF8 );
    }
}