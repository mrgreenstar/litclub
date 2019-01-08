package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Set<Author> findAll();
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
