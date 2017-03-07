package ru.weierstrass.services.catalog.product;

import org.springframework.beans.factory.annotation.Autowired;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.catalog.product.Image;
import ru.weierstrass.models.catalog.product.Product;
import ru.weierstrass.services.catalog.BrandService;
import ru.weierstrass.services.catalog.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class ProductService<E extends Product> extends DbService<E> {

    @Autowired
    private ImageService _imageService;
    @Autowired
    private BrandService _brandService;
    @Autowired
    private CategoryService _categoryService;

    protected void aggregate( List<E> list ) throws Exception {
        List<Integer> ids = new ArrayList<>();
        list.forEach( product -> ids.add( product.getId() ) );
        Map<Integer, List<Image>> images = _imageService.loadGroups( ids );
        list.forEach( product -> {
            if( images.containsKey( product.getId() ) ) {
                product.setRelatedImages( asRelated( images.get( product.getId() ) ) );
            }
            product.setRelatedBrand( asRelated( _brandService.get( product.getId() ) ) );
            product.setRelatedCategory( asRelated( _categoryService.get( product.getId() ) ) );
        } );
    }

}
