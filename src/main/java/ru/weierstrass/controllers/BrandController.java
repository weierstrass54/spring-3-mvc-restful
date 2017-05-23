package ru.weierstrass.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.Brand;
import ru.weierstrass.services.catalog.BrandService;

@RestController
public class BrandController {

    private BrandService _service;

    @Autowired
    public BrandController(BrandService brandService) {
        _service = brandService;
    }

    @RequestMapping(path = "/brand", method = RequestMethod.GET)
    public List<Brand> getList() throws Exception {
        return _service.loadList();
    }

}
