package ru.weierstrass.services.address;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.address.Area;

@Service
public class AreaService extends ORMDatabaseService<Area> {

    @Autowired
    public AreaService(DataSource db) {
        super(db);
    }

    public List<Area> loadList(int regionId)
        throws SQLException, InstantiationException, IllegalAccessException {
        return loadList(Area.class, "SELECT * FROM public_api_v01.address_get_areas( ? )",
            regionId);
    }

}
