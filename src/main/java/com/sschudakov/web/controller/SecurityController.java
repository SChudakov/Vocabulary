package com.sschudakov.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class SecurityController {

    @RequestMapping(value = "/newlogin", method = RequestMethod.GET)
    public ModelAndView getLogin() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        return modelAndView;
    }

    @RequestMapping(value = {"/user**"}, method = {RequestMethod.GET})
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security - user page");
        model.addObject("message", "User Page !");
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/protected**", method = RequestMethod.GET)
    public ModelAndView protectedPage(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("title", "Spring Security - Admin page");
        model.addAttribute("message", "This is protected page - Only for Admin Users!");

        modelAndView.setViewName("protected");
        return modelAndView;

    }

    @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
    public ModelAndView confidentialPage(Model model) {

        model.addAttribute("title", "Spring Security - SuperAdmin page");
        model.addAttribute("message", "This is confidential page - Need Super Admin Role!");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confidential");
        return modelAndView;

    }


    // for 403 access denied page
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(
            Principal user,
            Model model) {

        if (user != null) {

            model.addAttribute("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
        } else {

            model.addAttribute("msg", "You do not have permission to access this page!");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }
}
