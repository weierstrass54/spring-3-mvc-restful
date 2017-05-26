package ru.weierstrass.models.catalog.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Eta implements DatabaseModel {

    protected int referenceId;
    protected String eta;

    @JsonIgnore
    @Override
    public int getId() {
        return 0;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public String getEta() {
        return eta;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        referenceId = rs.getInt( "referenceId" );
        eta = rs.getString( "eta" );
    }

}
