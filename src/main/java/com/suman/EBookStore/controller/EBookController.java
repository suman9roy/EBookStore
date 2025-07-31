package com.suman.EBookStore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EBookController {

    @GetMapping("/home")
    public String hello(){
        return "Hola !! welcome to the Book Store.";
    }
}
