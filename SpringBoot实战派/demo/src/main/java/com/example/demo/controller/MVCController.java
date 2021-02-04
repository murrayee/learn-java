package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mvc")
public class MVCController {
    @GetMapping
    public ModelAndView index() {
        Student user = Student.builder().name("jiaxin").id(123).build();
        ModelAndView modelAndView = new ModelAndView("mvc");

        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
