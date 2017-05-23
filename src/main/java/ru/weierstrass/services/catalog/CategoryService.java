package ru.weierstrass.services.catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.catalog.Category;

@Service
public class CategoryService extends ORMDatabaseService<Category> {

    @Autowired
    public CategoryService(DataSource db) {
        super(db);
    }

    public Category get(int id) {
        return loadList().get(id);
    }

    public Category getTree() throws Exception {
        Map<Integer, Category> map = new HashMap<>();
        loadList().forEach(category -> map.put(category.getId(), category));
        return Category.buildTree(map);
    }

    @Cacheable(value = "category")
    private List<Category> loadList() {
        return loadList(Category.class, "SELECT * FROM public_api_v01.catalog_get_categories()");
    }

}
