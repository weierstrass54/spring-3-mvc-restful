package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.promo.AndroidPromo;
import ru.weierstrass.models.promo.DetailPromo;
import ru.weierstrass.models.promo.Promo;
import ru.weierstrass.services.promo.DetailPromoService;
import ru.weierstrass.services.promo.PromoService;

import java.util.List;

@RestController
public class PromoController {

    private PromoService _promo;
    private DetailPromoService _detailPromo;

    @Autowired
    public PromoController( PromoService promo, DetailPromoService detailPromo ) {
        _promo = promo;
        _detailPromo = detailPromo;
    }

    @RequestMapping( path = "/promo/main", method = RequestMethod.GET )
    public List<Promo> getMain(
        @RequestParam( name = "cityId", defaultValue = "659" ) int cityId
    ) {
        return _promo.loadBanners( cityId );
    }

    @RequestMapping( path = "/promo/news", method = RequestMethod.GET )
    public List<Promo> getNews(
        @RequestParam( name = "cityId", defaultValue = "659" ) int cityId,
        @RequestParam( name = "chunk" ) int chunk,
        @RequestParam( name = "page", required = false, defaultValue = "1" ) int page
    ) {
        //TODO: set headers
        int offset = ( page - 1 ) * chunk;
        return _promo.loadNews( cityId, offset, chunk );
    }

    @RequestMapping( path = "/promo/article", method = RequestMethod.GET )
    public List<Promo> getArticles(
        @RequestParam( name = "cityId", defaultValue = "659" ) int cityId,
        @RequestParam( name = "chunk" ) int chunk,
        @RequestParam( name = "page", required = false, defaultValue = "1" ) int page
    ) {
        //TODO: set headers
        int offset = ( page - 1 ) * chunk;
        return _promo.loadArticles( cityId, offset, chunk );
    }

    @RequestMapping( path = "/promo/event", method = RequestMethod.GET )
    public DetailPromo getEvent(
        @RequestParam( name = "id" ) int id
    ) {
        return _detailPromo.loadEvent( id );
    }

    @RequestMapping( path = "/promo/androidEvent", method = RequestMethod.GET )
    public AndroidPromo getAndroidEvent(
        @RequestParam( name = "id" ) int id
    ) {
        return _detailPromo.loadAndroidEvent( id );
    }

}
