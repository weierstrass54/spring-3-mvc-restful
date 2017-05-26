package ru.weierstrass.services.promo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.promo.Promo;

import javax.sql.DataSource;
import java.util.List;

@Service
public class PromoService extends ORMDatabaseService<Promo> {

    @Autowired
    public PromoService( DataSource db ) {
        super( db );
    }

    public List<Promo> loadBanners( int cityId ) {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_banners( ? )", cityId );
    }

    public List<Promo> loadNews( int cityId, int offset, int limit ) {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_news( ?, ?, ? )", cityId, offset, limit );
    }

    public List<Promo> loadArticles( int cityId, int offset, int limit ) {
        return loadList(
            Promo.class, "SELECT * FROM public_api_v01.promo_get_articles( ?, ?, ? )", cityId, offset, limit );
    }

    public int loadNewsCount( int cityId ) {
        return loadInt( "SELECT * FROM public_api_v01.promo_get_news_count( ? )", cityId );
    }

    public int loadArticleCount( int cityId ) {
        return loadInt( "SELECT * FROM public_api_v01.promo_get_article_count( ? )", cityId );
    }

}
