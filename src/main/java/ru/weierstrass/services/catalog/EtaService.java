package ru.weierstrass.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.catalog.offer.Eta;

import javax.sql.DataSource;
import java.util.List;

@Service
public class EtaService extends ORMDatabaseService<Eta> {

    @Autowired
    EtaService( DataSource db ) {
        super( db );
    }

    public List<Eta> loadList( List<Integer> ids, int zoneId ) {
        return loadList(
            Eta.class, "SELECT * FROM public_api_v07.catalog_get_product_eta( " + array( ids ) + ", ? )", zoneId );
    }

}
