package com.example.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public ModelAndView getTest() {
        ModelAndView result = new ModelAndView("test");
        result.addObject("text", "Hello, World");
        return result;
    }
}
