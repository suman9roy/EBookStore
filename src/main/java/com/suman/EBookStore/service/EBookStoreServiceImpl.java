package com.suman.EBookStore.service;

import com.suman.EBookStore.Dto.BooksDto;
import com.suman.EBookStore.Entity.Books;
import com.suman.EBookStore.Repo.EBookstoreRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private ModelMapper modelMapper;
    @Autowired
    public EBookStoreServiceImpl(EBookstoreRepo eBookstoreRepo,ModelMapper modelMapper) {

        this.eBookstoreRepo = eBookstoreRepo;
        this.modelMapper=modelMapper;
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
    public ResponseEntity<?> getBookById(int id) {
        Optional<Books> books=eBookstoreRepo.findById(id);
        if(books.isEmpty()){
            return new ResponseEntity<>("Books not found with the Id :"+id,HttpStatus.NOT_FOUND);
        }
        else{
            BooksDto responseBookDtoById=modelMapper.map(books.get(), BooksDto.class);
            return new ResponseEntity<>(responseBookDtoById,HttpStatus.FOUND);
        }

    }


    @Override
    public ResponseEntity<Page<BooksDto>> getAllBooks(int page, int size, String sortBy, boolean ascending) {
        Sort sort= ascending ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pagable=PageRequest.of(page, size,sort);
        Page<Books> pageOfBooks=eBookstoreRepo.findAll(pagable);
        Page<BooksDto> pageOfBooksDto=pageOfBooks.map(x-> {return new BooksDto(x.getBookId(),
                x.getBookName(),
                x.getPrice());});
        return new ResponseEntity<>(pageOfBooksDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBook(BooksDto booksDto) {
        if(eBookstoreRepo.findByBookName(booksDto.getBookName()).isEmpty()) {
            Books temp = new Books();
            temp.setBookName(booksDto.getBookName());
            temp.setPrice(booksDto.getPrice());
            temp=eBookstoreRepo.save(temp);
            BooksDto responseSavedBooksdto=modelMapper.map(temp, BooksDto.class);
            return new ResponseEntity<>(responseSavedBooksdto,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Book name already exsist",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateBook(int id, BooksDto booksDto) {

        Optional<Books> optionalBook=eBookstoreRepo.findById(id);
        if(optionalBook.isEmpty()){
            return new ResponseEntity<>("The Book which you trying to update does not exist with Id "+id,HttpStatus.BAD_REQUEST);
        }
        Books booksNeedToUpdated=optionalBook.get();
        booksNeedToUpdated.setBookName(booksDto.getBookName());
        booksNeedToUpdated.setPrice(booksDto.getPrice());
        booksNeedToUpdated=eBookstoreRepo.save(booksNeedToUpdated);
        BooksDto responseBookDto=modelMapper.map(booksNeedToUpdated, BooksDto.class);
        return new ResponseEntity<>(responseBookDto,HttpStatus.OK);

    }

}
