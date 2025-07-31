package com.suman.EBookStore.controller;

import com.suman.EBookStore.Entity.Books;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EBookController {

    @GetMapping("/api/home")
    public String hello(){
        return "Hola !! welcome to the Book Store.";
    }
    @GetMapping("/api/getAllBooks")
    public List<Books> getAllBooks(){
        List<Books> booklist=new ArrayList<>();
        booklist.add(new Books(1,"abc",450.0));
        booklist.add(new Books(1,"abc",450.0));
        booklist.add(new Books(1,"abc",450.0));
        return  booklist;
    }
}
