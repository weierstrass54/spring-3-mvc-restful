package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.Brand;
import ru.weierstrass.services.catalog.BrandService;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandService _service;

    @RequestMapping( path = "/brand", method = RequestMethod.GET )
    public List<Brand> getList() throws Exception {
        return _service.loadList();
    }

}
