package com.suman.EBookStore.Repo;

import com.suman.EBookStore.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EBookstoreRepo extends JpaRepository<Books,Integer> {
   Optional<Books> findByBookName(String bookName);
}
