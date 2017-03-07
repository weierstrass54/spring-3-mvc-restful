package ru.weierstrass.components.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class ApiAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final int id;

    //TODO: generate access token for user
    //Question: this token will be returned by App.user(), perhaps it should be able to allow access to user class

    public ApiAuthenticationToken( ApiUserDetails user ) {
        super( user.getUsername(), user.getPassword(), user.getAuthorities() );
        this.id = user.getId();
    }

    public int getId() {
        return this.id;
    }

}
