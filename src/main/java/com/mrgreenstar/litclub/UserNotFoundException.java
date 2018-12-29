package com.mrgreenstar.litclub;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user not found")
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Couldn't find user " + id);
    }
}
