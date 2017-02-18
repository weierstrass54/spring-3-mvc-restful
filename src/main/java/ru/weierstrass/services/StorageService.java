package ru.weierstrass.services;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.Storage;

import java.util.List;

@Service
public class StorageService extends DbService<Storage> {

    public List<Storage> loadList() throws Exception {
        return loadList( Storage.class, "SELECT * FROM public_api_v01.storage_get()" );
    }

}
