package ru.weierstrass.models.user;

import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements DatabaseModel {

    private int id;
    private String login;
    private String password;

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.login = rs.getString( "login" );
        this.password = rs.getString( "password" );
    }

}
