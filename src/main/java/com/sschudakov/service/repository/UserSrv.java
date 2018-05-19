package com.sschudakov.service.repository;

import com.sschudakov.dao.springdata.UserRepository;
import com.sschudakov.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSrv {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserSrv(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        String password = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(password));
        this.userRepository.save(user);
        user.setPassword(password);
        return user;
    }

    public boolean userExistsByName(User user) {
        return this.userRepository.existsUserByName(user.getName());
    }

    public Optional<User> getUserByNameAndPassword(String name, String password) {
        return this.userRepository.existsUserByNameAndPassword(name, password);
    }
}
