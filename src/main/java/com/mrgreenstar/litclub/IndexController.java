package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("redirect:/1");
        return model;
    }

    @GetMapping(path="/{id}")
    public ModelAndView certainIndex(@PathVariable Long id,
                                     ModelAndView model) {
        model.setViewName("index");
        return model;
    }
}
