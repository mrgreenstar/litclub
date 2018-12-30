package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByGenreName(String genreName);
}
