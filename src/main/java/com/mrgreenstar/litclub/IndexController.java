package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/user/{id}")
    @ResponseBody
    public User certainIndex(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        User certainUser = userRepository.findUserById(id);
        if (certainUser == null) {
            throw new UserNotFoundException();
        }
        return certainUser;
    }
}

