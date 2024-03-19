package com.example.kledo.productkledo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.kledo.productkledo.model.UserForm;
import com.example.kledo.productkledo.service.UserService;

import jakarta.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    
    @GetMapping("/new/user")
    public String registrationForm(Model model) {
        model.addAttribute("registerAction", "/users/new/user");
        model.addAttribute("user", new UserForm());
        return "user/CreateUser";
    }
    
    @PostMapping("/new/user")
    public String registerCustomer(@Valid @ModelAttribute("user") UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            model.addAttribute("user", userForm);
            model.addAttribute("registerAction", "/users/new/user");
            model.addAttribute("registrationError", null);
            return "user/CreateUser";
        }
        userService.saveNewUser(userForm);
        model.addAttribute("createdUserName", userForm.getUserName());
        System.out.println(userForm.getUserName());
        return "user/CreateUser";
    }
}
