package ru.weierstrass.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.catalog.Category;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends DatabaseService<Category> {

    @Autowired
    public CategoryService( DataSource db ) {
        super( db );
    }

    public Category get( int id ) {
        return null;
    }

    public Category getTree() throws Exception {
        Map<Integer, Category> map = new HashMap<>();
        loadList().forEach( category -> map.put( category.getId(), category ) );
        return Category.buildTree( map );
    }

    private List<Category> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Category.class, "SELECT * FROM public_api_v01.catalog_get_categories()" );
    }

}
