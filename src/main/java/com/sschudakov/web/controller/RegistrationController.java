package com.sschudakov.web.controller;

import com.sschudakov.web.registration.repository.RoleRepository;
import com.sschudakov.web.registration.repository.UserRepository;
import com.sschudakov.web.dto.UserDto;
import com.sschudakov.entity.Role;
import com.sschudakov.entity.User;
import com.sschudakov.entity.VerificationToken;
import com.sschudakov.web.registration.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
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
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MessageSource messages;

    @RequestMapping(value = "/regUser")
    public ModelAndView regUser() {
        return new ModelAndView("registration", "user", new UserDto());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user")
            @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }

        User registered = userRepository.registerNewUserAccount(accountDto);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception e) {

            System.out.println("Email registration was failed with " + accountDto.getEmail());
            return new ModelAndView("login", "customMessage", "Email registration was failed with " + accountDto.getEmail());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clogin");
        modelAndView.addObject("customMessage", "Please check your email " + accountDto.getEmail() + " to confirm registration!");

        return modelAndView;
    }


    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration
            (WebRequest request, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userRepository.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            return new ModelAndView("clogin", "customMessage", message);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            return new ModelAndView("clogin", "customMessage", messageValue);
        }

        user.setEnabled(true);

        Role role = roleRepository.findByRoleName("ROLE_USER");

        user.addToRole(role);
        userRepository.saveRegisteredUser(user);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clogin");
        modelAndView.addObject("User was registered");

        return modelAndView;
    }
}
