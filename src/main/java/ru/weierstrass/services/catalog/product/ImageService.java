package ru.weierstrass.services.catalog.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.catalog.product.Image;

@Service
public class ImageService extends ORMDatabaseService<Image> {

    @Autowired
    public ImageService(DataSource db) {
        super(db);
    }

    public Map<Integer, List<Image>> loadGroups(List<Integer> ids) {
        Map<Integer, List<Image>> map = new HashMap<>();
        loadList(ids).forEach(image -> {
            if (!map.containsKey(image.getReferenceId())) {
                map.put(image.getReferenceId(), new ArrayList<>());
            }
            map.get(image.getReferenceId()).add(image);
        });
        return map;
    }

    @Cacheable(value = "image")
    private List<Image> loadList(List<Integer> ids) {
        return loadList(Image.class, "SELECT * FROM public_api_v01.catalog_get_images( ? )", ids);
    }

}
