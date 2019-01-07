package com.mrgreenstar.litclub.Assemblers;

import com.mrgreenstar.litclub.Controllers.GenreController;
import com.mrgreenstar.litclub.Entity.Genre;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class GenreResourceAssembler implements ResourceAssembler<Genre, Resource<Genre>> {

    @Override
    public Resource<Genre> toResource(Genre genre) {
        return new Resource<>(genre,
                linkTo(methodOn(GenreController.class).findAllGenres()).withRel("genres"));
    }
}
