package com.mrgreenstar.litclub.Controllers;

import com.mrgreenstar.litclub.Assemblers.AuthorResourceAssembler;
import com.mrgreenstar.litclub.Entity.Author;
import com.mrgreenstar.litclub.Exceptions.AuthorNotFoundException;
import com.mrgreenstar.litclub.Repositories.AuthorRepository;
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
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorResourceAssembler assembler;

    private final Logger log = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping(path = "/authors", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Author>> findAllAuthors() {
        List<Resource<Author>> AuthorList = authorRepository.findAll().stream()
                .map((Author) -> assembler.toResource(Author))
                .collect(Collectors.toList());
        return new Resources<>(AuthorList,
                linkTo(methodOn(AuthorController.class).findAllAuthors()).withSelfRel());
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<?> newAuthor(@RequestBody Author newAuthor) throws URISyntaxException {
        if (authorRepository.findAuthorByFirstNameAndLastName(newAuthor.getFirstName(), newAuthor.getLastName()) != null) {
            log.error("Author already exists:" + newAuthor);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Resource<Author> resource = assembler.toResource(authorRepository.save(newAuthor));
        log.info("New author has been created:" + newAuthor);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping(path = "/authors/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Author> findOneAuthor(@PathVariable("id") Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        return assembler.toResource(author);
    }
}
