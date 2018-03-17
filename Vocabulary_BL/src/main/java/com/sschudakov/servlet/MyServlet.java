package com.sschudakov.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Semen Chudakov
 */


@Controller
public class MyServlet {

    // 1st part
    @RequestMapping(value = "myServlet", method = RequestMethod.GET)
    public String manageName(@RequestParam("name") String param, Model model) {
        // Model visibility - all application context
        model.addAttribute("name", param);

        return "user";
    }

    // end of 1st part


    // 2nd part
    @RequestMapping(value = "sessionServlet", method = RequestMethod.POST)
    public String manageFirstName(@RequestParam("firstname") String param,
                                  HttpSession session, Model model) {
        session.setAttribute("firstname", param);
        return "lastname";
    }

    @RequestMapping(value = "lastname", method = RequestMethod.GET)
    public String manageLastName(@RequestParam("lastname") String param,
                                 HttpSession session, Model model) {
        session.setAttribute("lastname", param);
        return "job";
    }

    @RequestMapping(value = "job", method = RequestMethod.GET)
    public ModelAndView manageJob(@RequestParam("job") String param,
                                  HttpSession session, Model model) {
        // common use this variant
        // we set a page and an object to send
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("job", param);
        modelAndView.setViewName("all");
        return modelAndView;
    }


}
