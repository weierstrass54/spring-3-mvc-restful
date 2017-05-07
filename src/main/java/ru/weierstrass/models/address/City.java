package ru.weierstrass.models.address;

import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.Storage;
import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City implements DatabaseModel {

    protected int id;
    protected String name;
    protected List<Storage> shops;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Storage> getShops() {
        return this.shops;
    }

    public void setRelatedShops( DatabaseService<City>.Relation<List<Storage>> shops ) {
        this.shops = shops.get();
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.name = rs.getString( "name" );
        this.shops = new ArrayList<>();
    }

}
