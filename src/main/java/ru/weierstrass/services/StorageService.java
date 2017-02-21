package ru.weierstrass.services;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageService extends DbService<Storage> {

    public List<Storage> loadList() throws Exception {
        return loadList( Storage.class, "SELECT * FROM public_api_v01.storage_get()" );
    }

    public Map<Integer, List<Storage>> loadGroupByCity() throws Exception {
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
