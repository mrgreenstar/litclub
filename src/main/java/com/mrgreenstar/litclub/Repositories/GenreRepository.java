package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
