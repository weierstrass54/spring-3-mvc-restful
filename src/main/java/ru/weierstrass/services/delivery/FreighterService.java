package ru.weierstrass.services.delivery;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.Freighter;

import java.util.List;

@Service
public class FreighterService extends DbService<Freighter> {

    public List<Freighter> loadList() throws Exception {
        return loadList( Freighter.class, "SELECT * FROM public_api_v01.delivery_get_transports()" );
    }

}
