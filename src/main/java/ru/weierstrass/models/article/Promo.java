package ru.weierstrass.models.article;

import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Promo implements DatabaseModel {

    protected int id;
    protected String title;
    protected String image;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return "https://novosibirsk.e2e4online.ru" + this.image;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.title = rs.getString( "title" );
        this.image = rs.getString( "image" );
    }
}
