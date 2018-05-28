package com.sschudakov.web.controller;

import com.sschudakov.entity.Role;
import com.sschudakov.entity.User;
import com.sschudakov.service.repository.RoleSrv;
import com.sschudakov.service.repository.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationController {

    private static final String ROLE_USER = "ROLE_USER";

    private final UserSrv userSrv;
    private final RoleSrv roleSrv;

    @Autowired
    public RegistrationController(UserSrv userSrv, RoleSrv roleSrv) {
        this.userSrv = userSrv;
        this.roleSrv = roleSrv;
    }


    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView getLoginPage(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("/registration/login");
        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public ModelAndView loginUser(
            ModelAndView modelAndView,
            @ModelAttribute("user") User user
    ) {
        modelAndView.setViewName("vocabulary/words");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("registration/registration");
        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(
            ModelAndView modelAndView,
            @ModelAttribute("user") User user,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            modelAndView.setViewName("registration/registration");
            modelAndView.addObject("user", user);
        } else {

            boolean userExists = this.userSrv.userExistsByName(user);

            if (userExists) {
                result.rejectValue("name", "user already exists");
                modelAndView.addObject("nameErrorMessage", "User with such name already exists. Please choose another one.");
                modelAndView.setViewName("registration/registration");
            } else {
                Role userRole = this.roleSrv.findByName(ROLE_USER);
                user.addRole(userRole);
                this.userSrv.save(user);
                modelAndView.addObject("confirmationMessage", "User has been registered successfully");
                modelAndView.setViewName("registration/login");
            }
        }
        return modelAndView;
    }
}
