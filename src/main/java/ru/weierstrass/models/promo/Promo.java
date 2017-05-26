package ru.weierstrass.models.promo;

import ru.weierstrass.components.cache.LinoIdentifiable;
import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Promo implements DatabaseModel, LinoIdentifiable {

    private static final String IMAGE_SRC = "https://novosibirsk.e2e4online.ru";

    protected int id;
    protected String title;
    protected String image;

    @Override
    public String getKey() {
        return String.valueOf( getId() );
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return IMAGE_SRC + image;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        id = rs.getInt( "id" );
        title = rs.getString( "title" );
        image = rs.getString( "image" );
    }


}
