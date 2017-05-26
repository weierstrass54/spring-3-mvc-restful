package ru.weierstrass.services.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.address.Area;

import javax.sql.DataSource;
import java.util.List;

@Service
public class AreaService extends ORMDatabaseService<Area> {

    @Autowired
    public AreaService( DataSource db ) {
        super( db );
    }

    @Cacheable( "area" )
    public List<Area> loadList( int regionId ) {
        return loadList( Area.class, "SELECT * FROM public_api_v01.address_get_areas( ? )", regionId );
    }

}
