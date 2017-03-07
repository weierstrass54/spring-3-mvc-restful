package ru.weierstrass.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.Api;

@RestController
public class UserController {

    @RequestMapping( path = "/user", method = RequestMethod.GET )
    public String getHello() {
        return "Hello, " + Api.user().getName() + "! You are authenticated with id " + Api.user().getId();
    }

}
