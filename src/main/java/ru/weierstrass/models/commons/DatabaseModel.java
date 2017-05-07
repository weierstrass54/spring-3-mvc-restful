package ru.weierstrass.models.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseModel {

    void mapping( ResultSet rs ) throws SQLException;

}
