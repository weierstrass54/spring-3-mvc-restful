package ru.weierstrass.services.promo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.promo.AndroidPromo;
import ru.weierstrass.models.promo.DetailPromo;

import javax.sql.DataSource;

@Service
public class DetailPromoService extends ORMDatabaseService<DetailPromo> {

    @Autowired
    public DetailPromoService( DataSource db ) {
        super( db );
    }

    public DetailPromo loadEvent( int id ) {
        return loadObject( DetailPromo.class, "SELECT * FROM public_api_v01.promo_get_event( ? )", id );
    }

    public AndroidPromo loadAndroidEvent( int id ) {
        return new AndroidPromo( loadEvent( id ) );
    }

}
