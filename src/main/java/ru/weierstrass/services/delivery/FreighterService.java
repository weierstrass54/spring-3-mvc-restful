package ru.weierstrass.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DatabaseService;
import ru.weierstrass.models.Freighter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class FreighterService extends DatabaseService<Freighter> {

    @Autowired
    public FreighterService( DataSource db ) {
        super( db );
    }

    public List<Freighter> loadList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Freighter.class, "SELECT * FROM public_api_v01.delivery_get_transports()" );
    }

}
