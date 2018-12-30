package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailOrLogin(String email, String login);
}
