package com.sschudakov.dao.springdata;

import com.sschudakov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User saveAndFlush(User user);

    boolean existsUserByName(String name);

    Optional<User> existsUserByNameAndPassword(String name, String password);
}
