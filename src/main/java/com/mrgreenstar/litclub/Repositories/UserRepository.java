package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findUserByEmailOrLogin(String email, String login);
}
