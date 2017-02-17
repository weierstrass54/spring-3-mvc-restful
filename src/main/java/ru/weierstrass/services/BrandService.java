package ru.weierstrass.services;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.Brand;

import java.util.List;

@Service
public class BrandService extends DbService<Brand> {

    public List<Brand> loadList() throws Exception {
        return loadList( Brand.class, "SELECT * FROM public_api_v01.catalog_get_brands()" );
    }

}
