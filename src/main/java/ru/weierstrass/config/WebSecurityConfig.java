package ru.weierstrass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.weierstrass.components.handlers.ExceptionHandlerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM = "e2e4-api-realm";

    @Autowired
    private AuthenticationProvider _authentication;

    private static final AuthenticationEntryPoint _basicEntryPoint = (
        HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) -> {
        response.setHeader( "WWW-Authenticate", "Basic realm=\"" + REALM + "\"" );
        response.setContentType( MediaType.APPLICATION_JSON_UTF8_VALUE );
        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
        response.getWriter().println( ExceptionHandlerAdvice.toJson( authException, HttpStatus.UNAUTHORIZED ) );
    };

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
                //only authenticated users has access to private area
                .antMatchers( "/user/**", "/order/**" ).authenticated()
                //others can browse public area
                .antMatchers( "/**" ).permitAll()
            .and()
                //REST API has no sessions
                .sessionManagement()
                    .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
            .and()
                //Use HTTP Basic Authentication
                .httpBasic()
                    //Set REALM
                    .realmName( REALM )
                    //Set entry point for fail
                    .authenticationEntryPoint( _basicEntryPoint );
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.authenticationProvider( _authentication );
    }

}
