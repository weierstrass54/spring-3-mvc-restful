package ru.weierstrass.models.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Storage;
import ru.weierstrass.models.commons.DatabaseModel;

public class City implements DatabaseModel {

    protected int id;
    protected String name;
    protected List<Storage> shops;

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Storage> getShops() {
        return this.shops;
    }

    public void setRelatedShops(ORMDatabaseService<City>.Relation<List<Storage>> shops) {
        this.shops = shops.get();
    }

    @Override
    public void mapping(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.shops = new ArrayList<>();
    }

}
