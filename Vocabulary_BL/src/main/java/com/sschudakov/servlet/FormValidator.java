package com.sschudakov.servlet;


import com.sschudakov.servlet.dao.UserDAO;
import com.sschudakov.servlet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  23.04.2017 for thirdSpringApp project.
 */
@Component
public class FormValidator implements Validator {

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean supports(Class<?> type) {
        // this validator can be applied only to User class
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // the main logic is here

        // actually these emptiness checks should be done in frontend: html5 and/or JS/Angular
        ValidationUtils.rejectIfEmpty(errors
                // name of field must be equal to class(User) field name
                , "name"
                // this value is from properties file
                , "error.enterYourName");
        ValidationUtils.rejectIfEmpty(errors, "login", "error.enterYourLogin");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.enterYourPassword");

        // check in DB that there is no such user and then permit validation
        boolean isUserExists = userDAO.isLoginExists(((User) o).getLogin());
        if (isUserExists) {
            errors.rejectValue("login", "error.loginExists");
        }

    }

}
