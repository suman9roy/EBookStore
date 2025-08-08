package com.suman.EBookStore.service;

import com.suman.EBookStore.Dto.BooksDto;
import com.suman.EBookStore.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EBookStoreService {

    public ResponseEntity<Page<BooksDto>> getAllBooks(int page, int size, String sortBy, boolean ascending );

    ResponseEntity<?> addBook(BooksDto booksDto);

    ResponseEntity<?> updateBook(int id, BooksDto booksDto);
    ResponseEntity<?> deleteBooksById(int id);

    ResponseEntity<?> getBookById(int id);
}
