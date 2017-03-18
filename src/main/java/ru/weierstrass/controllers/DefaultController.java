package ru.weierstrass.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class DefaultController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping( path = "/", method = RequestMethod.GET )
    public String getGreetings() {
        return "Welcome to Java API. Remember that it is my will that guided you here.";
    }

    @RequestMapping( path = ERROR_PATH, method = RequestMethod.GET )
    public void error() {
        throw new NoSuchElementException( "Resource not found." );
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
