package ru.weierstrass.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping( path = "/user", method = RequestMethod.GET )
    public String getHello() {
        return "Hello, world!";
    }

}
