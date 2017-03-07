package ru.weierstrass.services.catalog;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.catalog.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends DbService<Category> {

    public Category get( int id ) {
        return null;
    }

    public Category getTree() throws Exception {
        Map<Integer, Category> map = new HashMap<>();
        loadList().forEach( category -> map.put( category.getId(), category ) );
        return Category.buildTree( map );
    }

    private List<Category> loadList() throws Exception {
        return loadList( Category.class, "SELECT * FROM public_api_v01.catalog_get_categories()" );
    }

}
