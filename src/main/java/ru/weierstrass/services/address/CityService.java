package ru.weierstrass.services.address;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.address.City;

import java.util.List;

@Service
public class CityService extends DbService<City> {

    public List<City> loadList( Integer regionId, Integer areaId ) throws Exception {
        return loadList( City.class, "SELECT * FROM public_api_v01.address_get_cities( ?, ? )", regionId, areaId );
    }

     public List<City> loadList() throws Exception {
        return loadList( null,null );
     }

}
