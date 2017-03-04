package ru.weierstrass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Properties;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Properties props;

    static {
        props = new Properties();
        props.put( "server.error.whitelabel.enabled", "false" );
    }

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder builder ) {
        builder.properties( props );
        return builder.sources( Application.class );
    }

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }

}
