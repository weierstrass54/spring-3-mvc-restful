package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.Storage;
import ru.weierstrass.services.StorageService;

import java.util.List;

@RestController
public class StorageController {

    @Autowired
    private StorageService _service;

    @RequestMapping( path = "/storage", method = RequestMethod.GET )
    public List<Storage> getList() throws Exception {
        return _service.loadList();
    }

}
