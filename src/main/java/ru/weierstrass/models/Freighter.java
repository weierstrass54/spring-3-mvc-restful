package ru.weierstrass.models;

import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Freighter implements DatabaseModel {

    private int id;
    private String name;
    private List<Integer> cityIds;

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Integer> getCityIds() {
        return Collections.unmodifiableList( this.cityIds );
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
        this.cityIds = new ArrayList<>();

        String[] ids = rs.getString( "cityIds" ).split( "," );
        for( int i = 0; i < ids.length; ++i ) {
            this.cityIds.add( Integer.valueOf( ids[i] ) );
        }
    }

}
