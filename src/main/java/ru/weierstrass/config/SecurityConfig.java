package ru.weierstrass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.weierstrass.components.authentication.HttpBasicFailure;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final HttpBasicFailure _failure;

    @Autowired
    public SecurityConfig( HttpBasicFailure failure ) {
        _failure = failure;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
            //only authenticated users has access to private area
            .antMatchers( "/user/**", "/order/**" ).authenticated()
            //others can browse public area
            .antMatchers( "/**" ).permitAll()
            .and()
            .sessionManagement()
                //REST API has no sessions
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
            .and()
            .httpBasic()
                .realmName( _failure.getRealm() )
                //Set entry point for fail
                .authenticationEntryPoint( _failure );
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication().withUser( "test" ).password( "test" ).roles( "USER" );
    }

}
