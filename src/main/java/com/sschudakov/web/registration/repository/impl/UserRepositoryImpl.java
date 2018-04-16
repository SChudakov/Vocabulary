package com.sschudakov.web.registration.repository.impl;

import com.sschudakov.web.registration.repository.IUserRepository;
import com.sschudakov.web.registration.repository.RoleRepository;
import com.sschudakov.web.registration.repository.UserRepository;
import com.sschudakov.web.registration.repository.VerificationTokenRepository;
import com.sschudakov.web.dto.UserDto;
import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerNewUserAccount(UserDto accountDto) {

        if (emailExist(accountDto.getEmail())) {
            System.out.println(
                    "There is an account with that email address: "
                            + accountDto.getEmail());
            return null;
        } else {
            User user = new User();
            user.setUserName(accountDto.getUserName());
            user.setUserPassword(passwordEncoder.encode(accountDto.getUserPassword()));
            user.setUserEmail(accountDto.getEmail());
            user.addToRole(roleRepository.getOne(1));

            return userRepository.save(user);
        }
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByUserEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String verificationToken) {
        User user = verificationTokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

}
