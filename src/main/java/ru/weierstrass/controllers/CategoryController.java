package ru.weierstrass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.models.catalog.Category;
import ru.weierstrass.services.catalog.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService _categoryService;

    @RequestMapping( path = "/category/list", method = RequestMethod.GET )
    public Category getTree() {
        return _categoryService.getTree();
    }

    @RequestMapping( path = "/category/item", method = RequestMethod.GET )
    public Category getCategory( @RequestParam( name = "id", required = true ) int id ) {
        return _categoryService.get( id );
    }

}
