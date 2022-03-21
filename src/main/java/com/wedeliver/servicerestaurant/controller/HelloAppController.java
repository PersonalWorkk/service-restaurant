package com.wedeliver.servicerestaurant.controller;

import com.wedeliver.servicerestaurant.domain.Greeting;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAppController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting hello() {
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello");
        greeting.setApp("service-account");
        return greeting;
    }
}