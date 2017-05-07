package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.Freighter;
import ru.weierstrass.services.delivery.FreighterService;

import java.util.List;

@RestController
public class DeliveryController {

    private FreighterService _freighterService;

    @Autowired
    public DeliveryController( FreighterService freighterService ) {
        _freighterService = freighterService;
    }

    @RequestMapping( path = "/delivery/freighters", method = RequestMethod.GET )
    public List<Freighter> getFreighters() throws Exception {
        return _freighterService.loadList();
    }

}
