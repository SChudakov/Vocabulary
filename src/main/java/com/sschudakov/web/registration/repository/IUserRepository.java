package com.sschudakov.web.registration.repository;

import com.sschudakov.web.dto.UserDto;
import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 */
public interface IUserRepository {
    User registerNewUserAccount(UserDto accountDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

}
