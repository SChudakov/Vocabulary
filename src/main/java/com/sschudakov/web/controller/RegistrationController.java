package com.sschudakov.web.controller;

import com.sschudakov.entity.User;
import com.sschudakov.service.RoleService;
import com.sschudakov.service.UserService;
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

    private final UserService userService;
    private final RoleService roleService;
    private final ApplicationContext applicationContext;

    @Autowired
    public RegistrationController(
            UserService userService,
            RoleService roleService,
            ApplicationContext applicationContext
    ) {
        this.userService = userService;
        this.roleService = roleService;
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView getLoginPage(
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("/registration/courses/loginPage");
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
            modelAndView.setViewName("registration/courses/loginPage");
            modelAndView.addObject("user", user);
        } else {
            System.out.println("USER NAME: " + user.getName());
            System.out.println("USER PASSWORD: " + user.getPassword());

            Optional<User> optionalUser = this.userService.getUserByNameAndPassword(
                    user.getName(),
                    user.getPassword()
            );

            if (optionalUser.isPresent()) {
                modelAndView.addObject("confirmLoginMessage", "User logged successfully");
            } else {
                result.rejectValue("notSuchUser", "message.loginErr");
                modelAndView.addObject("loginErrorMessage", "Username or login is incorrect");
            }

            modelAndView.setViewName("registration/courses/registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(
            ModelAndView modelAndView

    ) {
        modelAndView.setViewName("registration/courses/registration");
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
            modelAndView.setViewName("registration/courses/registration");
            modelAndView.addObject("user", user);
        } else {

            boolean userExists = this.userService.userExistsByName(user);

            if (userExists) {
                result.rejectValue("userName", "message.userNameError");
                modelAndView.addObject("nameErrorMessage", "Please check choose another name");
            } else {
                modelAndView.addObject("confirmationMessage", "User has been registered successfully");
            }

            modelAndView.setViewName("registration/courses/registration");
        }
        return modelAndView;
    }
}
