package com.suman.EBookStore.service;

import com.suman.EBookStore.Entity.Books;
import com.suman.EBookStore.Repo.EBookstoreRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;

@Service
public class EBookStoreServiceImpl implements EBookStoreService {
    private static final Logger logger =LogManager.getLogger(EBookStoreServiceImpl.class);

    private EBookstoreRepo eBookstoreRepo;

    @Autowired
    public EBookStoreServiceImpl(EBookstoreRepo eBookstoreRepo) {
        this.eBookstoreRepo = eBookstoreRepo;
    }

    @Override
    public ResponseEntity<?> deleteBooksById(int id) {
        Optional<Books> optionalBooks=eBookstoreRepo.findById(id);
        if(optionalBooks.isEmpty()){
            return new ResponseEntity<>("the Book which you trying to delete does not exist with Id "+id,
                    HttpStatus.BAD_REQUEST);
        }
        Books deleteBook=optionalBooks.get();
        eBookstoreRepo.delete(deleteBook);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Books>> getAllBooks() {
        logger.info("All the records has been fetched");
        List<Books> booklist=eBookstoreRepo.findAll();
        if(booklist.isEmpty()){
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(booklist);

    }

    @Override
    public ResponseEntity<?> addBook(Books books) {
        if(eBookstoreRepo.findByBookName(books.getBookName()).isEmpty()) {
            Books temp = new Books();
            temp.setBookName(books.getBookName());
            temp.setPrice(books.getPrice());
            return new ResponseEntity<>(eBookstoreRepo.save(temp),HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Book name already exsist",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateBook(int id, Books books) {

        Optional<Books> optionalBook=eBookstoreRepo.findById(id);
        if(optionalBook.isEmpty()){
            return new ResponseEntity<>("The Book which you trying to update does not exist with Id "+id,HttpStatus.BAD_REQUEST);
        }
        Books booksNeedToUpdated=optionalBook.get();
        booksNeedToUpdated.setBookName(books.getBookName());
        booksNeedToUpdated.setPrice(books.getPrice());
        return new ResponseEntity<>(eBookstoreRepo.save(booksNeedToUpdated),HttpStatus.OK);

    }

}
