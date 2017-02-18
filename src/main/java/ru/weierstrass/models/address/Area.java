package ru.weierstrass.models.address;

import ru.weierstrass.models.commons.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Area extends DbModel {

    protected int id;
    protected String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.getName() + " район";
    }

    @Override
    public void bind( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
    }

}
