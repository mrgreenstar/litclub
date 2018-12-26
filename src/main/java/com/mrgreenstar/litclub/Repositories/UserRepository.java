package com.mrgreenstar.litclub.Repositories;

import com.mrgreenstar.litclub.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
