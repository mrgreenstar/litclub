package com.mrgreenstar.litclub;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Couldn't find user " + id);
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Author not found")
class AuthorNotFoundException extends RuntimeException {
    AuthorNotFoundException(Long id) {
        super("Couldn't find author " + id);
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book not found")
class BookNotFoundException extends RuntimeException {
    BookNotFoundException(Long id) {
        super("Couldn't find book " + id);
    }
}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Genre not found")
class GenreNotFoundException extends RuntimeException {
    GenreNotFoundException(Long id) {
        super("Couldn't find genre " + id);
    }
}