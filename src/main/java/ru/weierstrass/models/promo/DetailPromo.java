package ru.weierstrass.models.promo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailPromo extends Promo {

    protected String created;
    protected String contentShort;
    protected String contentFull;

    public String getCreated() {
        return created;
    }

    public String getContentShort() {
        return contentShort;
    }

    public String getContentFull() {
        return contentFull;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        super.mapping( rs );
        created = rs.getString( "created" );
        contentShort = rs.getString( "content_short" );
        contentFull = rs.getString( "content_full" );
    }
}
