package ru.weierstrass.services.article;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.DbService;
import ru.weierstrass.models.article.Promo;

import java.util.List;

@Service
public class PromoService extends DbService<Promo> {

    public List<Promo> loadBannerList() throws Exception {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_banners()" );
    }

    public List<Promo> loadArticleList( int offset, int limit ) throws Exception {
        return loadList( Promo.class, "SELECT * FROM public_api_v01.promo_get_articles( ?, ? )", offset, limit );
    }

}
