package com.sschudakov.service;

import com.sschudakov.dao.springdata.UserRepository;
import com.sschudakov.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public boolean userExistsByName(User user) {
        return this.userRepository.existsUserByName(user.getName());
    }

    public Optional<User> getUserByNameAndPassword(String name, String password) {
        return this.userRepository.existsUserByNameAndPassword(name, password);
    }
}
