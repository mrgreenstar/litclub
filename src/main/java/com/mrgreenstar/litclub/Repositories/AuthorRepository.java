package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
