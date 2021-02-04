package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {
    @GetMapping("variable")
    public ModelAndView variable() {
        ModelAndView modelAndView = new ModelAndView("thymeleaf");
        String name = "jia xin";
        Integer age = 18;
        modelAndView.addObject("name", name);
        modelAndView.addObject("age", age);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView index() {
        List<String> list = new ArrayList<String>();

        list.add("数学");
        list.add("语文");
        list.add("化学");
        list.add("物理");

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("list", list);
        return modelAndView;
    }


}
