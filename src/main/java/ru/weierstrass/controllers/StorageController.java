package ru.weierstrass.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.Storage;
import ru.weierstrass.services.StorageService;

@RestController
public class StorageController {

    private StorageService _service;

    @Autowired
    public StorageController(StorageService storageService) {
        _service = storageService;
    }

    @RequestMapping(path = "/storage", method = RequestMethod.GET)
    public List<Storage> getList() throws Exception {
        return _service.loadList();
    }

}
