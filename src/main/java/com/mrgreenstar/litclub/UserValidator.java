package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Entity.User;
import com.mrgreenstar.litclub.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if (userRepository.existsByEmail(user.getEmail())) {
            errors.rejectValue("email", "email.taken");
        }
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "login.taken");
        }
    }
}
