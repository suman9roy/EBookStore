package com.suman.EBookStore.controller;

import com.suman.EBookStore.Entity.Books;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EBookController {
     List<Books> booklist;

    public EBookController(List<Books> booklist) {
        booklist.add(new Books(1,"abc",450.0));
        booklist.add(new Books(3,"abd",450.0));
        booklist.add(new Books(4,"abn",450.0));
        this.booklist = booklist;
    }

    @GetMapping("/api/home")
    public String hello(){
        return "Hola !! welcome to the Book Store.";
    }
    @GetMapping("/api/getAllBooks")
    public List<Books> getAllBooks(){
    return booklist;

    }

    @PostMapping("/api/addBook")
    public Books addBook(@RequestBody Books books){
        booklist.add(books);
        System.out.println(booklist);
        return books;
    }
    @PutMapping("/api/updateBook/{id}")
    public Books updateBook(@PathVariable int id,@RequestBody Books books){
       Books booksNeedToUpdated= booklist.stream().filter(x->x.getBookId()==id).findFirst().get();
       booksNeedToUpdated.setBookName(books.getBookName());
       booksNeedToUpdated.setPrice(books.getPrice());
       return booksNeedToUpdated;
    }
    @DeleteMapping("/api/deleteBook")
    public String deleteBookById(@RequestParam int id){
        Books deleteBook=booklist.stream().filter(x->x.getBookId()==id).findFirst().get();
        booklist.remove(deleteBook);
        return "Book is deleted";
    }
}
