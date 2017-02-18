package ru.weierstrass.services.address;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.address.Area;

import java.util.List;

@Service
public class AreaService extends DbService<Area> {

    public List<Area> loadList( int regionId ) throws Exception {
        return loadList( Area.class, "SELECT * FROM public_api_v01.address_get_areas( ? )", regionId );
    }

}
