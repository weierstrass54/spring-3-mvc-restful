package ru.weierstrass.models.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import ru.weierstrass.models.commons.DatabaseModel;

public class Region implements DatabaseModel {

    protected int id;
    protected int type;
    protected String name;

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        switch (this.type) {
            case 2:
                return this.getName() + " автономная область";
            case 3:
                return "Республика " + this.getName();
            case 5:
                return this.getName() + " край";
            case 6:
                return this.getName() + " область";
            case 8:
                return this.getName() + " автономный округ";
            case 30:
                return "Город федерального значения " + this.getName();
            default:
                return this.getName();
        }
    }

    @Override
    public void mapping(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.type = rs.getInt("type");
        this.name = rs.getString("name");
    }

}
