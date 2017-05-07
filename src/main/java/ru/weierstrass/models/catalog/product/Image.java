package ru.weierstrass.models.catalog.product;

import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Image implements DatabaseModel {

    private static final String PREFIX = "http://images.e2e4online.ru";

    private static final String BIG = "o";
    private static final String MEDIUM = "tl";
    private static final String SMALL = "ts";

    protected int id;
    protected int position;
    protected String description;
    protected String extension;
    protected String extensionSmall;
    protected String extensionMedium;
    protected int originalWidth;
    protected int originalHeight;

    protected int referenceId;

    public int getId() {
        return this.id;
    }

    public int getReferenceId() {
        return this.referenceId;
    }

    public int getPosition() {
        return this.position;
    }

    public String getDescription() {
        return this.description;
    }

    public String getOriginal() {
        return PREFIX + "/" + BIG + "/" + this.id + "." + this.extension;
    }

    public String getMedium() {
        return PREFIX + "/" + MEDIUM + "/" + this.id + "." + this.extensionMedium;
    }

    public String getSmall() {
        return PREFIX + "/" + SMALL + "/" + this.id + "." + this.extensionSmall;
    }

    public int getOriginalWidth() {
        return this.originalWidth;
    }

    public int getOriginalHeight() {
        return this.originalHeight;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.referenceId = rs.getInt( "referenceId" );
        this.position = rs.getInt( "position" );
        this.description = rs.getString( "description" );
        this.extension = rs.getString( "exception" );
        this.extensionMedium = rs.getString( "'extensionMedium'" );
        this.extensionSmall = rs.getString( "extensionSmall" );
        this.originalWidth = rs.getInt( "originalWidth" );
        this.originalHeight = rs.getInt( "originalHeight" );
    }
    
}
