package ru.weierstrass.components.authentication;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApiUserDetails extends UserDetails {

    /**
     * Returns the id of authenticated user.
     *
     * @return the id
     */
    int getId();

}
