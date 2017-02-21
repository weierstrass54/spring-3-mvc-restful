package ru.weierstrass.models.catalog;

import ru.weierstrass.models.commons.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand extends DbModel {

    protected int id;
    protected String name;
    protected String url;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public void bind( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
        this.url = rs.getString( "url" );
    }

}
