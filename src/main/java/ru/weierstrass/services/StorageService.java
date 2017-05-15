package ru.weierstrass.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Storage;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageService extends ORMDatabaseService<Storage> {

    @Autowired
    public StorageService( DataSource db ) {
        super( db );
    }

    public List<Storage> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Storage.class, "SELECT * FROM public_api_v01.storage_get()" );
    }

    public Map<Integer, List<Storage>> loadGroupByCity() throws SQLException, InstantiationException, IllegalAccessException {
        Map<Integer, List<Storage>> groups = new HashMap<>();
        for( Storage storage : loadList() ) {
            if( !groups.containsKey( storage.getCityId() ) ) {
                groups.put( storage.getCityId(), new ArrayList<>() );
            }
            groups.get( storage.getCityId() ).add( storage );
        }
        return groups;
    }

}
