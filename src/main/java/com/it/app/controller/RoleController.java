package com.it.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView chooseAdminOrUser() {
        return new ModelAndView("role", "message", "Choose your role!");
    }


    @RequestMapping(value = "/modelAttribute", method = RequestMethod.GET)
    public String modelAttribute(@ModelAttribute("input") String input, ModelMap model) {
        model.addAttribute("message", "Your role is:" + input);
        return "main";
    }
}