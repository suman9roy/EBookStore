package com.suman.EBookStore.controller;

import com.suman.EBookStore.Entity.Books;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hola !! welcome to the Book Store.", HttpStatus.OK);
    }
    @GetMapping("/api/getAllBooks")
    public ResponseEntity<List<Books>> getAllBooks(){
        if(booklist.isEmpty()){
            ResponseEntity.notFound();
        }
    return ResponseEntity.ok(booklist);

    }

    @PostMapping("/api/addBook")
    public ResponseEntity<?> addBook(@RequestBody Books books){
        if(booklist.stream().anyMatch(x -> x.getBookId() == books.getBookId()))
        {
            return new ResponseEntity<>("Book is already exsit with id"+books.getBookId(),HttpStatus.CONFLICT);
        }
        booklist.add(books);
        System.out.println(booklist);
        return new ResponseEntity<>(books,HttpStatus.CREATED);
    }
    @PutMapping("/api/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id,@RequestBody Books books){
       Optional<Books> optionalBooks= booklist.stream().filter(x->x.getBookId()==id).findFirst();
       if(optionalBooks.isEmpty()){
           return new ResponseEntity<>("The Book which you trying to update does not exsist with Id "+id,HttpStatus.BAD_REQUEST);
       }
        Books booksNeedToUpdated=optionalBooks.get();
       booksNeedToUpdated.setBookName(books.getBookName());
       booksNeedToUpdated.setPrice(books.getPrice());
       return new ResponseEntity<>(booksNeedToUpdated,HttpStatus.OK);
    }
    @DeleteMapping("/api/deleteBook")
    public ResponseEntity<?> deleteBookById(@RequestParam int id){
        Optional<Books> optionalBooks=booklist.stream().filter(x->x.getBookId()==id).findFirst();
        if(optionalBooks.isEmpty()){
            return new ResponseEntity<>("the Book which you trying to delete does not exsist with Id "+id,
                    HttpStatus.BAD_REQUEST);
        }
        Books deleteBook=optionalBooks.get();
        booklist.remove(deleteBook);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
