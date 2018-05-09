package com.sschudakov.service;

import com.sschudakov.dao.springdata.UserRepository;
import com.sschudakov.dto.UserDTO;
import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUserAccount(UserDTO accountDto) {
        return null;
    }

    public VerificationToken getVerificationToken(String token) {
        return null;
    }

    public void saveRegisteredUser(User user) {

    }
}
