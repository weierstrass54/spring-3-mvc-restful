package ru.weierstrass.models.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class DbModel {

    abstract public void bind( ResultSet rs ) throws SQLException;

}
