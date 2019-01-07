package com.mrgreenstar.litclub.Controllers;

import com.mrgreenstar.litclub.Assemblers.UserResourceAssembler;
import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Exceptions.UserNotFoundException;
import com.mrgreenstar.litclub.Repositories.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResourceAssembler assembler;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/users", produces = "application/json; charset=UTF-8")
    public Resources<Resource<User>> findAllUsers() {
        List<Resource<User>> userList = userRepository.findAll().stream()
                .map(user -> assembler.toResource(user))
                .collect(Collectors.toList());
        return new Resources<>(userList,
                linkTo(methodOn(UserController.class).findAllUsers()).withSelfRel());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {
        if (userRepository.findUserByEmailOrLogin(newUser.getEmail(), newUser.getLogin()) != null) {
            log.error("User was not created because email or login is already taken:" + newUser);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Resource<User> resource = assembler.toResource(userRepository.save(newUser));
        log.info("New user has been created:" + newUser);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping(path = "/users/{id}", produces = "application/json; charset=UTF-8")
    public Resource<User> findOneUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toResource(user);
    }

}
