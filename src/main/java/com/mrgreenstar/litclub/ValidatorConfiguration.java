package com.mrgreenstar.litclub;

import com.mrgreenstar.litclub.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class ValidatorConfiguration implements RepositoryRestConfigurer {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeCreate", new UserValidator(userRepository));
    }
}
