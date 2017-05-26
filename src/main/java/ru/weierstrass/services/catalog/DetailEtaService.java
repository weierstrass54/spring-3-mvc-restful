package ru.weierstrass.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Storage;
import ru.weierstrass.models.catalog.offer.DetailEta;
import ru.weierstrass.services.StorageService;

import javax.sql.DataSource;
import java.util.List;

public class DetailEtaService extends ORMDatabaseService<DetailEta> {

    private StorageService _storageService;

    @Autowired
    public DetailEtaService( DataSource db, StorageService storageService ) {
        super( db );
        _storageService = storageService;
    }

    public List<DetailEta> loadList( List<Integer> ids, int zoneId ) {
        List<DetailEta> etas = loadList( DetailEta.class, "SELECT", zoneId );
        List<Storage> storages = _storageService.loadList();
        etas.forEach( detailEta -> {
            //map required
            detailEta.setStorage( relation( storages.get( 0 ) ) );
        } );
        return etas;
    }

}
