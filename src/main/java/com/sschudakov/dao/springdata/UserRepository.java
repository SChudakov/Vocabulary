package com.sschudakov.dao.springdata;

import com.sschudakov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User saveAndFlush(User user);

    boolean existsUserByName(String name);

    Optional<User> existsUserByNameAndPassword(String name, String password);
}
