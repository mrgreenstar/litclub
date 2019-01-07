package com.mrgreenstar.litclub.Controllers;

import com.mrgreenstar.litclub.Assemblers.GenreResourceAssembler;
import com.mrgreenstar.litclub.Entity.Genre;
import com.mrgreenstar.litclub.Exceptions.GenreNotFoundException;
import com.mrgreenstar.litclub.Repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreResourceAssembler assembler;

    private final Logger log = LoggerFactory.getLogger(GenreController.class);

    @GetMapping(path = "/genres", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Genre>> findAllGenres() {
        List<Resource<Genre>> genreList = genreRepository.findAll().stream()
            .map((genre) -> assembler.toResource(genre))
            .collect(Collectors.toList());
        return new Resources<>(genreList, 
                linkTo(methodOn(GenreController.class).findAllGenres()).withSelfRel());
    }

    @PostMapping(path = "/genres")
    public ResponseEntity<?> newGenre(@RequestBody Genre newGenre) throws URISyntaxException {
        if (genreRepository.findGenreByGenreName(newGenre.getGenreName()) != null) {
            log.error("Genre already exists:" + newGenre);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Resource<Genre> resource = assembler.toResource(genreRepository.save(newGenre));
        log.info("New genre has been created:" + newGenre);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }
    
    @GetMapping(path = "/genres/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Genre> findOneGenre(@PathVariable("id") Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        return assembler.toResource(genre);
    }
}
