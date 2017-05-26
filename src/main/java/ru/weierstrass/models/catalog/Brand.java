package ru.weierstrass.models.catalog;

import ru.weierstrass.components.cache.LinoIdentifiable;
import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand implements DatabaseModel, LinoIdentifiable {

    protected int id;
    protected String name;
    protected String url;

    @Override
    public String getKey() {
        return String.valueOf( getId() );
    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
        this.url = rs.getString( "url" );
    }

}
