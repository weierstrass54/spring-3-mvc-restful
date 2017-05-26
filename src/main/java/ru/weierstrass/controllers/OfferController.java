package ru.weierstrass.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.offer.Offer;

import java.util.List;

@RestController
public class OfferController {

    /*
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
    */

    //GET offer/list
    //GET offer/view
    //GET offer/comment
    //GET offer/opinion
    //POST offer/comment

    public List<Offer> getList() {
        return null;
    }

    /*
    public DetailOffer getOffer() {
        return null;
    }

    public List<Comment> getComment() {
        return null;
    }

    public List<Opinion> getOpinion() {
        return null;
    }

    public Comment addComment() {
        return null;
    }
    */

}
