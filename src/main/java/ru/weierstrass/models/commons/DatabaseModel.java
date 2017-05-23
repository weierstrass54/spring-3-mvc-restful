package ru.weierstrass.models.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseModel {

    int getId();

    void mapping(ResultSet rs) throws SQLException;

}
