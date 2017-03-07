package ru.weierstrass.components.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ApiUserDetailsService extends UserDetailsService {

    @Override
    ApiUserDetails loadUserByUsername( String username ) throws UsernameNotFoundException;

}
