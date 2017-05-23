package ru.weierstrass.services.catalog;

import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.catalog.Brand;

@Service
public class BrandService extends ORMDatabaseService<Brand> {

    private static final Logger _log = LoggerFactory.getLogger(BrandService.class);

    @Autowired
    public BrandService(DataSource db) {
        super(db);
    }

    public Brand get(int id) {
        return loadList().get(id);
    }

    @Cacheable(value = "brand")
    public List<Brand> loadList() {
        return loadList(Brand.class, "SELECT * FROM public_api_v01.catalog_get_brands()");
    }

}
