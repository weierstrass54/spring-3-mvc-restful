package ru.weierstrass.services.catalog.product;

import org.springframework.beans.factory.annotation.Autowired;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.catalog.product.Image;
import ru.weierstrass.models.catalog.product.Product;
import ru.weierstrass.services.catalog.BrandService;
import ru.weierstrass.services.catalog.CategoryService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class ProductService<E extends Product> extends ORMDatabaseService<E> {

    private ImageService _imageService;
    private BrandService _brandService;
    private CategoryService _categoryService;

    @Autowired
    protected ProductService(
        DataSource db,
        ImageService imageService,
        BrandService brandService,
        CategoryService categoryService
    ) {
        super( db );
        _imageService = imageService;
        _brandService = brandService;
        _categoryService = categoryService;
    }

    protected void aggregate( List<E> list ) {
        List<Integer> ids = new ArrayList<>();
        list.forEach( product -> ids.add( product.getId() ) );
        Map<Integer, List<Image>> images = _imageService.loadGroups( ids );
        list.forEach( product -> {
            if( images.containsKey( product.getId() ) ) {
                product.setImages( relation( images.get( product.getId() ) ) );
            }
            product.setBrand( relation( _brandService.get( product.getId() ) ) );
            product.setCategory( relation( _categoryService.get( product.getId() ) ) );
        } );
    }

    protected List<Integer> loadIds( String query, Object... params ) {
        return loadColumn( Integer.class, query, params );
    }

}
