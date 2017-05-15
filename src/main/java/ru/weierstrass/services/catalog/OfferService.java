package ru.weierstrass.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.models.catalog.offer.Offer;
import ru.weierstrass.services.catalog.product.ImageService;
import ru.weierstrass.services.catalog.product.ProductService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService extends ProductService<Offer> {

    @Autowired
    public OfferService(
        DataSource db,
        ImageService imageService,
        BrandService brandService,
        CategoryService categoryService
    ) {
        super( db, imageService, brandService, categoryService );
    }

    public List<Offer> loadList() {
        List<Integer> ids = loadIds( "SELECT * FROM public_api_v07.catalog_get_products_by_search( 0, 5, 1, 7, null, null, null, null, '[{\"type\": \"price\", \"direction\": \"asc\"}]'::json )" );
        List<Offer> list = loadList( Offer.class, "SELECT * FROM public_api_v01.catalog_get_product_by_ids( <ids> )" );
        aggregate( list );
        return list;
    }

    public Offer load() {
        return null;
    }

}
