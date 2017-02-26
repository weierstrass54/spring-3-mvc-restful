package ru.weierstrass.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.offer.Offer;
import ru.weierstrass.services.catalog.OfferService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OfferController {

    private static final ObjectMapper json = new ObjectMapper();

    @Autowired
    private OfferService _offerService;

    public List<Offer> getList() throws Exception {
        return _offerService.loadList();
    }

    public Offer get() {
        return _offerService.load();
    }

}
