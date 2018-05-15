package com.sschudakov.web.controller;

import com.sschudakov.entity.User;
import com.sschudakov.service.repository.RoleSrv;
import com.sschudakov.service.repository.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class RegistrationController {

    private final UserSrv userSrv;
    private final RoleSrv roleSrv;
    private final ApplicationContext applicationContext;

    @Autowired
    public RegistrationController(UserSrv userSrv, RoleSrv roleSrv, ApplicationContext applicationContext) {
        this.userSrv = userSrv;
        this.roleSrv = roleSrv;
        this.applicationContext = applicationContext;
    }


    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView getLoginPage(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("/registration/loginPage");
        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public ModelAndView loginUser(
            ModelAndView modelAndView,
            @ModelAttribute("user") User user,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            modelAndView.setViewName("registration/loginPage");
            modelAndView.addObject("user", user);
        } else {
            System.out.println("USER NAME: " + user.getName());
            System.out.println("USER PASSWORD: " + user.getPassword());

            Optional<User> optionalUser = this.userSrv.getUserByNameAndPassword(
                    user.getName(),
                    user.getPassword()
            );

            if (optionalUser.isPresent()) {
                modelAndView.addObject("confirmLoginMessage", "User logged in successfully");
            } else {
                result.rejectValue("noSuchUser", "There is no such user!");
                modelAndView.addObject("loginErrorMessage", "Username or login is incorrect");
            }

            modelAndView.setViewName("registration/registration");
        }
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
                result.rejectValue("userName", "message.userNameError");
                modelAndView.addObject("nameErrorMessage", "Please check choose another name");
            } else {
                modelAndView.addObject("confirmationMessage", "User has been registered successfully");
            }

            modelAndView.setViewName("registration/registration");
        }
        return modelAndView;
    }
}
