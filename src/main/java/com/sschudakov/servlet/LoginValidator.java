package com.sschudakov.servlet;


import com.sschudakov.servlet.forvalidators.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  23.04.2017 for thirdSpringApp project.
 */
@Component
public class LoginValidator implements Validator {


    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // validate User login process
    }
}
