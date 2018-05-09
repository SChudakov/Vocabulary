package com.sschudakov.web.controller;

import com.sschudakov.dao.springdata.RoleRepository;
import com.sschudakov.dto.UserDTO;
import com.sschudakov.entity.Role;
import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;
import com.sschudakov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@RestController
public class RegistrationController {

    /*private final ApplicationEventPublisher eventPublisher;*/
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final ApplicationContext applicationContext;
    private final MessageSource messages;

    @Autowired
    public RegistrationController(/*ApplicationEventPublisher eventPublisher,*/
            UserService userService,
            RoleRepository roleRepository,
            ApplicationContext applicationContext,
            MessageSource messages
    ) {

        /*this.eventPublisher = eventPublisher;*/
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.applicationContext = applicationContext;
        this.messages = messages;
    }

    @RequestMapping(value = "/regUser")
    public ModelAndView regUser() {
        return new ModelAndView("registration", "user", new UserDTO());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user")
            @Valid UserDTO accountDto,
            BindingResult result,
            WebRequest request
    ) {
        ModelAndView modelAndView;

        if (result.hasErrors()) {
            modelAndView = new ModelAndView("registration", "user", accountDto);
        } else {

            User registered = this.userService.registerNewUserAccount(accountDto);

            if (registered == null) {
                result.rejectValue("email", "message.regError");
            }

            /*try {

                String appUrl = request.getContextPath();
                this.eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

            } catch (Exception e) {

                System.out.println("Email registration was failed with " + accountDto.getEmail());
                return new ModelAndView("login", "customMessage", "Email registration was failed with " + accountDto.getEmail());

            }*/

            modelAndView = new ModelAndView();
            modelAndView.setViewName("clogin");
            modelAndView.addObject("customMessage", "Please check your email " + accountDto.getEmail() + " to confirm registration!");


        }
        return modelAndView;
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(
            WebRequest request,
            @RequestParam("token") String token
    ) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = this.userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = this.messages.getMessage("auth.message.invalidToken", null, locale);
            return new ModelAndView("clogin", "customMessage", message);
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        /*if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {

            String messageValue = this.messages.getMessage("auth.message.expired", null, locale);
            return new ModelAndView("clogin", "customMessage", messageValue);

        }*/

        /*user.setEnabled(true);*/

        Role role = this.roleRepository.getByName("ROLE_USER");

        user.addRole(role);
        this.userService.saveRegisteredUser(user);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clogin");
        modelAndView.addObject("User was registered");

        return modelAndView;
    }
}
