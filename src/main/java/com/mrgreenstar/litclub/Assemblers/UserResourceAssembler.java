package com.mrgreenstar.litclub.Assemblers;

import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Controllers.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).findOneUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAllUsers()).withRel("users"));
    }
}
