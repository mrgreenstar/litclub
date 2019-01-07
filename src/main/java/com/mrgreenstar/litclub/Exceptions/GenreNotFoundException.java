package com.mrgreenstar.litclub.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Genre not found")
public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Couldn't find genre " + id);
    }
}