package com.mrgreenstar.litclub.Controllers;

import com.mrgreenstar.litclub.Assemblers.BookResourceAssembler;
import com.mrgreenstar.litclub.Entity.Book;
import com.mrgreenstar.litclub.Exceptions.BookNotFoundException;
import com.mrgreenstar.litclub.Repositories.BookRepository;
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
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookResourceAssembler assembler;

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping(path = "/books", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Book>> findAllBooks() {
        List<Resource<Book>> bookList = bookRepository.findAll().stream()
                .map(book -> assembler.toResource(book))
                .collect(Collectors.toList());
        return new Resources<>(bookList,
                linkTo(methodOn(BookController.class).findAllBooks()).withSelfRel());
    }

    @PostMapping(path = "/books")
    public ResponseEntity<?> newBook(@RequestBody Book newBook) throws URISyntaxException {
        if (bookRepository.findBookByTitleAndAuthors(newBook.getTitle(), newBook.getAuthors()) != null) {
            log.error("Book was not created because already exists:" + newBook);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Resource<Book> resource = assembler.toResource(bookRepository.save(newBook));
        log.info("New book has been added:" + newBook);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping(path = "/books/{id}", produces = "application/json; charset=UTF-8")
    public Resource<?> findOneBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toResource(book);
    }
}
