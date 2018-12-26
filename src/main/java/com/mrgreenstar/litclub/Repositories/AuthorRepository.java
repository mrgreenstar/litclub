package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
