package ru.weierstrass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.weierstrass.components.authentication.ApiAuthenticationToken;

import java.util.Properties;

@SpringBootApplication
public class Api extends SpringBootServletInitializer {

    private static final Properties props;

    static {
        props = new Properties();
        props.put( "server.error.whitelabel.enabled", "false" );
    }

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder builder ) {
        builder.properties( props );
        return builder.sources( Api.class );
    }

    public static ApiAuthenticationToken user() {
        return (ApiAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
    }

    public static void main( String[] args ) {
        SpringApplication.run( Api.class, args );
    }

}
