package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Entity.Author;
import com.mrgreenstar.litclub.Entity.Book;
import com.mrgreenstar.litclub.Entity.Genre;
import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Used HTTP codes:
    201 - created
    404 - not found
    409 - conflict

    To-do:
        add method for certain book's reviews
        add all links between entities
        add PUT requests for all entities
*/

@RestController
public class ApiController {
    private Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping(path="/users")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/users")
    public ResponseEntity addUser(@RequestBody User newUser) {
        if (userRepository.findUserByEmailOrLogin(newUser.getEmail(), newUser.getLogin()) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        log.info(newUser.toString());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/users/{id}")
    public User certainIndex(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping(path="/authors")
    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping(path="/authors")
    public ResponseEntity addAuthor(@RequestBody Author newAuthor) {
        if (authorRepository.findAuthorByFirstNameAndLastName(
                newAuthor.getFirstName(), newAuthor.getLastName()) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        log.info(newAuthor.toString());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/authors/{id}")
    public Author certainAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @GetMapping(path="/books")
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @PostMapping(path="/books")
    public ResponseEntity addBook(@RequestBody Book newBook) {
        if (bookRepository.findBookByTitleAndAuthors(
                newBook.getTitle(), newBook.getAuthors()) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        log.info(newBook.toString());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/books/{id}")
    public Book certainBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @GetMapping(path="/genres")
    public List<Genre> allGenres() {
        return genreRepository.findAll();
    }

    @PostMapping(path="/genres")
    public ResponseEntity addGenre(@RequestBody Genre newGenre) {
        if (genreRepository.findGenreByGenreName(newGenre.getGenreName()) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        log.info(newGenre.toString());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/genres/{id}")
    public Genre certainGenre(@PathVariable Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

}

