package ru.weierstrass.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.user.User;

import javax.sql.DataSource;

@Service
public class UserService extends DatabaseService<User> {

    @Autowired
    public UserService( DataSource db ) {
        super( db );
    }

}
