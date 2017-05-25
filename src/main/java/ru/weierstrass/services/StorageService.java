package ru.weierstrass.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Storage;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StorageService extends ORMDatabaseService<Storage> {

    @Autowired
    public StorageService(DataSource db) {
        super(db);
    }

    @Cacheable("storage")
    public List<Storage> loadList() {
        return loadList(Storage.class, "SELECT * FROM public_api_v01.storage_get()");
    }

    public Map<Integer, List<Storage>> loadGroupByCity() {
        return loadList().stream().collect(Collectors.groupingBy(Storage::getCityId));

    }

}
