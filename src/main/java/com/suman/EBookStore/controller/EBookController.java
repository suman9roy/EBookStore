package com.suman.EBookStore.controller;

import com.suman.EBookStore.Entity.Books;
import com.suman.EBookStore.service.EBookStoreService;
import com.suman.EBookStore.service.EBookStoreServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EBookController {
    private static  final Logger logger= LogManager.getLogger(EBookController.class);
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
        logger.info("getAll the books in the store");
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
