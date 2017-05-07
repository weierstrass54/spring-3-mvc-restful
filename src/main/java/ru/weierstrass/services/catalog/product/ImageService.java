package ru.weierstrass.services.catalog.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.catalog.product.Image;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageService extends DatabaseService<Image> {

    @Autowired
    public ImageService( DataSource db ) {
        super( db );
    }

    public Map<Integer, List<Image>> loadGroups( List<Integer> ids ) throws SQLException, InstantiationException, IllegalAccessException {
        Map<Integer, List<Image>> map = new HashMap<>();
        loadList( ids ).forEach( image -> {
            if( !map.containsKey( image.getReferenceId() ) ) {
                map.put( image.getReferenceId(), new ArrayList<>() );
            }
            map.get( image.getReferenceId() ).add( image );
        } );
        return map;
    }

    private List<Image> loadList( List<Integer> ids ) throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Image.class, "SELECT * FROM public_api_v01.catalog_get_images( ? )", ids );
    }

}