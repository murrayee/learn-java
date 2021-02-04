package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("validator")
public class ValidatorController {
    @GetMapping
    public String showForm(User user){
        return "form";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @PostMapping("/submit")
    public String checkUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        attr.addFlashAttribute("user", user);
        return "redirect:/validator/results";

    }
}
