package ru.weierstrass.services.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.address.Region;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class RegionService extends ORMDatabaseService<Region> {

    @Autowired
    public RegionService( DataSource db ) {
        super( db );
    }

    public List<Region> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Region.class, "SELECT * FROM public_api_v01.address_get_regions()" );
    }

}
