package ru.weierstrass.models.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import ru.weierstrass.models.commons.DatabaseModel;

public class User implements DatabaseModel {

    private int id;
    private String login;
    private String password;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void mapping(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.login = rs.getString("login");
        this.password = rs.getString("password");
    }

}
