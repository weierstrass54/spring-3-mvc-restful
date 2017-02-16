package ru.weierstrass.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping( "/greeting" )
    public String greeting() {
        return "Hello, world!";
    }

}
