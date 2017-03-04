package ru.weierstrass.services.catalog;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.catalog.Brand;

import java.util.List;

@Service
public class BrandService extends DbService<Brand> {

    public Brand get( int id ) {
        return null;
    }

    public List<Brand> loadList() throws Exception {
        return loadList( Brand.class, "SELECT * FROM public_api_v01.catalog_get_brands()" );
    }

}
