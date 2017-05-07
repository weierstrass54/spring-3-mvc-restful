package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.offer.Offer;
import ru.weierstrass.services.catalog.OfferService;

import java.util.List;

@RestController
public class OfferController {

    private OfferService _offerService;

    @Autowired
    public OfferController( OfferService offerService ) {
        _offerService = offerService;
    }

    public List<Offer> getList() throws Exception {
        return _offerService.loadList();
    }

    public Offer get() {
        return _offerService.load();
    }

}
