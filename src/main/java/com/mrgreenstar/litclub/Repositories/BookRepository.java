package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Book;
import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long> {
    Set<Book> findAll();
    boolean existsByTitleAndGenre(String title, Genre genre);
}
