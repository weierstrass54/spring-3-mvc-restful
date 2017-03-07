package ru.weierstrass.services.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.Storage;
import ru.weierstrass.models.address.City;
import ru.weierstrass.services.StorageService;

import java.util.List;
import java.util.Map;

@Service
public class CityService extends DbService<City> {

    @Autowired
    private StorageService _storageService;

    public List<City> loadList( Integer regionId, Integer areaId ) throws Exception {
        List<City> cities = loadList( City.class, "SELECT * FROM public_api_v01.address_get_cities( ?, ? )", regionId, areaId );
        Map<Integer, List<Storage>> storages = _storageService.loadGroupByCity();
        cities.forEach( city -> {
            if( storages.get( city.getId() ) != null ) {
                city.setRelatedShops( asRelated( storages.get( city.getId() ) ) );
            }
        } );
        return cities;
    }

     public List<City> loadList() throws Exception {
        return loadList( null, null );
     }

}
