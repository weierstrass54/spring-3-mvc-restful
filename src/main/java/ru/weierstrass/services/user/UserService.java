package ru.weierstrass.services.user;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.user.User;

@Service
public class UserService extends DbService<User> {
}
