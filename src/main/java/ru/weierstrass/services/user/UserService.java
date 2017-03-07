package ru.weierstrass.services.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.authentication.ApiUserDetails;
import ru.weierstrass.components.authentication.ApiUserDetailsService;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.user.User;

@Service
public class UserService extends DbService<User> implements ApiUserDetailsService {

    @Override
    public ApiUserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        try {
            return loadObject( User.class, "SELECT * FROM site.users WHERE login = ?", username );
        }
        catch ( Exception e ) {
            throw new UsernameNotFoundException( "jdbc error", e );
        }
    }

}
