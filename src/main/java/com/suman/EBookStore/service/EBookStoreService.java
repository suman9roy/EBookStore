package com.suman.EBookStore.service;

import com.suman.EBookStore.Dto.BooksDto;
import com.suman.EBookStore.Entity.Books;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EBookStoreService {

    public ResponseEntity<List<BooksDto>> getAllBooks();

    ResponseEntity<?> addBook(BooksDto booksDto);

    ResponseEntity<?> updateBook(int id, BooksDto booksDto);
    ResponseEntity<?> deleteBooksById(int id);

    ResponseEntity<?> getBookById(int id);
}
