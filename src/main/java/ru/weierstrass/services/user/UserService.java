package ru.weierstrass.services.user;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.user.User;

@Service
public class UserService extends ORMDatabaseService<User> {

    @Autowired
    public UserService(DataSource db) {
        super(db);
    }

}
