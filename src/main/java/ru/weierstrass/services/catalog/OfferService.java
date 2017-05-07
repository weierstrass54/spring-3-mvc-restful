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

    public List<Offer> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        List<Integer> ids = new ArrayList<>();
        List<Offer> list = loadList( Offer.class, "SELECT 123" );
        aggregate( list );
        return list;
    }

    public Offer load() {
        return null;
    }

}
