package ru.weierstrass.models.user;

import ru.weierstrass.models.commons.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends DbModel {

    private int id;
    private String login;
    private String password;

    @Override
    public void bind( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.login = rs.getString( "login" );
        this.password = rs.getString( "password" );
    }

}
