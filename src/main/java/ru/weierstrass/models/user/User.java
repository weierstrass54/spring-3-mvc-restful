package ru.weierstrass.models.user;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import ru.weierstrass.components.authentication.ApiUserDetails;
import ru.weierstrass.models.commons.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class User extends DbModel implements ApiUserDetails, CredentialsContainer {

    private int id;
    private String login;
    private String password;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList( () -> "USER" );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void bind( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.login = rs.getString( "login" );
        this.password = rs.getString( "password" );
    }

}
