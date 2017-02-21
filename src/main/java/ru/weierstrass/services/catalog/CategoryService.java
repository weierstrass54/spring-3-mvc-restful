package ru.weierstrass.services.catalog;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.catalog.Category;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends DbService<Category> {

    private static Map<Integer, Category> _map = new HashMap<>();
    private static Category _tree;

    public Category getTree() {
        return _tree;
    }

    public Category get( int id ) {
        return _map.get( id );
    }

    @PostConstruct
    private void init() throws Exception {
        for( Category category : loadList() ) {
            _map.put( category.getId(), category );
        }
        _tree = Category.buildTree( _map );
    }

    private List<Category> loadList() throws Exception {
        return loadList( Category.class, "SELECT * FROM public_api_v01.catalog_get_categories()" );
    }

}
