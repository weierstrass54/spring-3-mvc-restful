package ru.weierstrass.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.catalog.Brand;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

@Service
public class BrandService extends DatabaseService<Brand> {

    @Autowired
    public BrandService( DataSource db ) {
        super( db );
    }

    public Brand get( int id ) {
        return null;
    }

    public List<Brand> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Brand.class, "SELECT * FROM public_api_v01.catalog_get_brands()" );
    }

}
