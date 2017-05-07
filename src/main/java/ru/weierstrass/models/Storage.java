package ru.weierstrass.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Storage implements DatabaseModel {

    protected int id;
    protected String name;
    protected String address;
    protected String fullAddress;
    protected String phone;
    protected String infoEmail;
    protected String deliveryEmail;
    protected String serviceEmail;
    protected int priceZoneId;
    protected int cityId;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getInfoEmail() {
        return this.infoEmail;
    }

    public String getDeliveryEmail() {
        return this.deliveryEmail;
    }

    public String getServiceEmail() {
        return this.serviceEmail;
    }

    public int getPriceZoneId() {
        return this.priceZoneId;
    }

    @JsonIgnore
    public int getCityId() {
        return this.cityId;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
        this.address = rs.getString( "address" );
        this.fullAddress = rs.getString( "fullAddress" );
        this.phone = rs.getString( "phone" );
        this.infoEmail = rs.getString( "infoEmail" );
        this.deliveryEmail = rs.getString( "deliveryEmail" );
        this.serviceEmail = rs.getString( "serviceEmail" );
        this.priceZoneId = rs.getInt( "priceZoneId" );
        this.cityId = rs.getInt( "cityId" );
    }

}
