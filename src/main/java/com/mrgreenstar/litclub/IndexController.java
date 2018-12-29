package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class IndexController {
    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/user/{id}")
    @ResponseBody
    public ResponseEntity<?> certainIndex(@PathVariable Long id) {
        Optional<User> certainUser = userRepository.findById(id);
        return certainUser.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path="/addUser")
    @ResponseBody
    public User addUser(@RequestBody User newUser) {
        log.info(newUser.toString());
        return userRepository.save(newUser);
    }
}

