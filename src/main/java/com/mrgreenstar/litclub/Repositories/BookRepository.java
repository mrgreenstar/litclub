package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Author;
import com.mrgreenstar.litclub.Entity.Book;
import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByGenre(Genre genre);
    Book findBookByTitleAndAuthors(String title, List<Author> authors);
}
