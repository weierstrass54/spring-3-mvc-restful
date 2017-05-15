package ru.weierstrass.models.catalog.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.weierstrass.models.catalog.Brand;
import ru.weierstrass.models.catalog.Category;
import ru.weierstrass.models.commons.DatabaseModel;
import ru.weierstrass.services.catalog.product.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class Product implements DatabaseModel {

    protected int id;
    protected String fullName;
    protected String shortName;
    protected Category category;
    protected Brand brand;
    protected int warranty;

    protected List<Image> images;

    private int categoryId;
    private int brandId;

    @Override
    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public int getWarranty() {
        return this.warranty;
    }

    public Category getCategory() {
        return this.category;
    }

    public Brand getBrand() {
        return this.brand;
    }

    @JsonIgnore
    public int getCategoryId() {
        return this.categoryId;
    }

    @JsonIgnore
    public int getBrandId() {
        return this.brandId;
    }

    public void setCategory( ProductService<? extends Product>.Relation<Category> category ) {
        this.category = category.get();
    }

    public void setBrand( ProductService<? extends Product>.Relation<Brand> brand ) {
        this.brand = brand.get();
    }

    public void setImages( ProductService<? extends Product>.Relation<List<Image>> images ) {
        this.images = images.get();
    }

    public Product() {
        this.images = new ArrayList<>();
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        this.fullName = rs.getString( "fullName" );
        this.shortName = rs.getString( "shortName" );
        this.categoryId = rs.getInt( "categoryId" );
        this.brandId = rs.getInt( "brandId" );
        this.warranty = rs.getInt( "warranty" );
    }

}
