package com.suman.EBookStore.controller;

import com.suman.EBookStore.Entity.Books;
import com.suman.EBookStore.service.EBookStoreService;
import com.suman.EBookStore.service.EBookStoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EBookController {
     List<Books> booklist;
    private EBookStoreService eBookStoreService;
    @Autowired
    public EBookController(EBookStoreService eBookStoreService) {
       this.eBookStoreService=eBookStoreService;
    }

    @GetMapping("/api/home")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hola !! welcome to the Book Store.", HttpStatus.OK);
    }

    @GetMapping("/api/getAllBooks")
    public ResponseEntity<List<Books>> getAllBooks(){
       return eBookStoreService.getAllBooks();

    }

    @PostMapping("/api/addBook")
    public ResponseEntity<?> addBook(@RequestBody Books books){
        return eBookStoreService.addBook(books);

    }
    @PutMapping("/api/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id,@RequestBody Books books){
        return eBookStoreService.updateBook(id,books);
        }

    @DeleteMapping("/api/deleteBook")
    public ResponseEntity<?> deleteBookById(@RequestParam int id){
        return eBookStoreService.deleteBooksById(id);

    }
}
