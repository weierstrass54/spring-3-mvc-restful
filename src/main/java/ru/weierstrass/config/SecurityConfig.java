package ru.weierstrass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.weierstrass.components.authentication.HttpBasicFailure;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource _db;
    private final HttpBasicFailure _failure;

    @Autowired
    public SecurityConfig( DataSource db, HttpBasicFailure failure ) {
        _db = db;
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
        auth.jdbcAuthentication()
            .dataSource( _db )
            .usersByUsernameQuery(
                "SELECT login, LOWER( password ), true FROM site.users WHERE login = ?" )
            .passwordEncoder( new Md5PasswordEncoder() )
            .authoritiesByUsernameQuery( "SELECT login, 'USER' FROM site.users WHERE login = ?" );
    }

}
