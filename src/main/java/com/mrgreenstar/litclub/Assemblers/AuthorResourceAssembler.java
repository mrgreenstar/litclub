package com.mrgreenstar.litclub.Assemblers;

import com.mrgreenstar.litclub.Controllers.AuthorController;
import com.mrgreenstar.litclub.Entity.Author;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AuthorResourceAssembler implements ResourceAssembler<Author, Resource<Author>> {

    @Override
    public Resource<Author> toResource(Author Author) {
        return new Resource<>(Author,
                linkTo(methodOn(AuthorController.class).findAllAuthors()).withRel("authors"));
    }
}
