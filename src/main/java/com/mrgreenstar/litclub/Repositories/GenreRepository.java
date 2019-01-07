package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
    Genre findGenreByGenreName(String genreName);
}
