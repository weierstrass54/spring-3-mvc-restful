package ru.weierstrass.models.catalog;

import ru.weierstrass.models.commons.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Category implements DatabaseModel {

    protected int id;
    protected String alias;
    protected String name;
    protected boolean isSection;
    protected int parentId;

    protected String image;
    protected int countProducts;
    protected List<Category> children;

    public Category() {
        this.isSection = false;
        this.parentId = 0;
        this.countProducts = 0;
        this.children = new ArrayList<>();
    }

    public static Category buildTree( Map<Integer, Category> map ) {
        Category root = new Category( "Catalog", "Каталог" );
        for( Category category : map.values() ) {
            if( category.getParentId() == 0 ) {
                root.append( category );
            }
            else if( map.containsKey( category.getParentId() ) ) {
                map.get( category.getParentId() ).append( category );
            }
        }
        return root;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsSection() {
        return this.isSection;
    }

    public int getParentId() {
        return this.parentId;
    }

    public List<Category> getChildren() {
        return this.children;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.id = rs.getInt( "id" );
        this.alias = rs.getString( "alias" );
        this.name = rs.getString( "name" );
        this.parentId = rs.getInt( "parentId" );
        this.isSection = rs.getBoolean( "isSection" );
    }

    private Category( String alias, String name ) {
        this();
        this.id = 0;
        this.alias = alias;
        this.name = name;
    }

    private void append( Category category ) {
        this.children.add( category );
    }

}
