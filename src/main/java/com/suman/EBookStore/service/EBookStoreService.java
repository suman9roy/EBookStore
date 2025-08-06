package com.suman.EBookStore.service;

import com.suman.EBookStore.Entity.Books;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EBookStoreService {

    public ResponseEntity<List<Books>> getAllBooks();

    ResponseEntity<?> addBook(Books books);

    ResponseEntity<?> updateBook(int id, Books books);
    ResponseEntity<?> deleteBooksById(int id);
}
