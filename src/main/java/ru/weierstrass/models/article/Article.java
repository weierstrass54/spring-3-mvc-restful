package ru.weierstrass.models.article;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Article extends Promo {

    protected String created;
    protected String contentShort;
    protected String contentFull;

    public String getCreated() {
        return this.created;
    }

    public String getContentShort() {
        return this.contentShort;
    }

    public String getContentFull() {
        return this.contentFull;
    }

    @Override
    public void mapping(ResultSet rs) throws SQLException {
        super.mapping(rs);
        this.created = rs.getString("created");
        this.contentShort = rs.getString("content_short");
        this.contentFull = rs.getString("content_full");
    }

}
