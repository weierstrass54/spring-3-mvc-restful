package ru.weierstrass.components.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.weierstrass.utils.HashUtils;

@Service
public class ApiAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ApiUserDetailsService _userService;

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
        //TODO: any kind of authenticate
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        ApiUserDetails user = _userService.loadUserByUsername( username );
        if( user == null || !user.getUsername().equals( username ) ) {
            throw new UsernameNotFoundException( "User not found" );
        }
        if( !user.getPassword().equals( HashUtils.md5( password ) ) ) {
            throw new BadCredentialsException( "Bad credentials" );
        }

        return new ApiAuthenticationToken( user );
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return true;
    }

}
