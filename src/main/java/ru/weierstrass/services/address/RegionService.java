package ru.weierstrass.services.address;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.address.Region;

import java.util.List;

@Service
public class RegionService extends DbService<Region> {

    public List<Region> loadList() throws Exception {
        return loadList( Region.class, "SELECT * FROM public_api_v01.address_get_regions()" );
    }

}
