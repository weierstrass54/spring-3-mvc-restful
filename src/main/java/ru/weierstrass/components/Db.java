package ru.weierstrass.components;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.jdbc.PGDriver;

import java.sql.SQLException;
import java.util.Properties;

public class Db {

    private PGDriver _driver;
    private String _host;
    private Properties _credentials;

    public Db( String host, String user, String password ) throws SQLException {
        _driver = new PGDriver();
        _host = "jdbc:pgsql://" + host;
        _credentials = new Properties();
        _credentials.put( "user", user );
        _credentials.put( "password", password );
    }

    public PGConnection getConnection() throws SQLException {
        return _driver.connect( _host, _credentials );
    }

}
