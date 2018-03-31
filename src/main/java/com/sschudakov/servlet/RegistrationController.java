package com.sschudakov.servlet;

import com.sschudakov.servlet.forvalidators.dao.UserDao;
import com.sschudakov.servlet.forvalidators.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  23.04.2017 for thirdSpringApp project.
 */
@Controller
// it puts into session an User object automatically
// like a Map with the key registeredUser and the value : user
@SessionAttributes(types = User.class)
public class RegistrationController {

    @Autowired
    UserDao userDao;
    @Autowired
    HttpSession session;

    @Autowired
    FormValidator formValidator;
    @Autowired
    RegistrationValidator registrationValidator;
    @Autowired
    LoginValidator loginValidator;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("registeredUser", user);
        return "registration";
    }

    @RequestMapping(value = "/userregistration", method = RequestMethod.POST)
    public ModelAndView doRegistration(
            //@ModelAttribute : it is the same modelAttribute as in jsp (registration.jsp)
            // data from the form should be moved to User u directly
            // so names of inputs should be the same as fields in User class
            @ModelAttribute("registeredUser")
            // to be validated by Validator
            @Validated
                    User u
            // results of form validation
            , BindingResult result) {
        ModelAndView mod = new ModelAndView();
        if (result.hasErrors()) {
            mod.setViewName("registration");
        } else {
            mod.setViewName("confirmation");
        }
        return mod;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String getConfirmation(SessionStatus status) {
        userDao.create((User) session.getAttribute("registeredUser"));
        status.setComplete(); //обнуление сессии (delete and recreate new)
        // we have to clear data after registrations and provide new one to login
        return "login";
    }

    @InitBinder
    protected void initValidator(WebDataBinder binder) {
        // bind validator to controller
        binder.setValidator(this.formValidator);
    }

    @InitBinder("registerationUser")
    protected void regValidator(WebDataBinder binder) {
        binder.setValidator(this.formValidator);
    }

    @InitBinder("loginUser")
    protected void loginValidator(WebDataBinder binder) {
        binder.setValidator(this.formValidator);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin(SessionStatus status) {
        return new ModelAndView( "login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(SessionStatus status) {
        status.setComplete(); //обнуление сессии (delete and recreate new)
        return "logout";
    }
}
