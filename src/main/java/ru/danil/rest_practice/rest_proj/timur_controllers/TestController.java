package ru.danil.rest_practice.rest_proj.timur_controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public String sayHello(){
        return "Hello World";
    }
}
