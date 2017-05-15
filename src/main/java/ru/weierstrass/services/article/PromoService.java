package ru.weierstrass.services.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.article.Promo;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class PromoService extends ORMDatabaseService<Promo> {

    @Autowired
    public PromoService( DataSource db ) {
        super( db );
    }

    public List<Promo> loadBannerList() throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_banners()" );
    }

    public List<Promo> loadArticleList( int offset, int limit ) throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_articles( ?, ? )", offset, limit );
    }

}
