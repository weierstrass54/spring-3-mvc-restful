package ru.weierstrass.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Freighter;

import javax.sql.DataSource;
import java.util.List;

@Service
public class FreighterService extends ORMDatabaseService<Freighter> {

    @Autowired
    public FreighterService( DataSource db ) {
        super( db );
    }

    @Cacheable( "freighters" )
    public List<Freighter> loadList() {
        return loadList( Freighter.class, "SELECT * FROM public_api_v01.delivery_get_transports()" );
    }

}
