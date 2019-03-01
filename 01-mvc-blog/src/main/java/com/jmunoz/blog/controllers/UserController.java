package com.jmunoz.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmunoz.blog.forms.LoginForm;
import com.jmunoz.blog.services.NotificationService;
import com.jmunoz.blog.services.UserService;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;
    
    public String listUsers(Model model) {
    	return "";
    }

    @RequestMapping("/users/login")
    public String login(LoginForm loginForm) {
        return "users/login";
    }

    @PostMapping("/users/login")
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
             notifyService.addErrorMessage("Please fill the form correctly!");
             return "users/login";
        }

        if (!userService.authenticate(

             loginForm.getUsername(), loginForm.getPassword())) {
             notifyService.addErrorMessage("Invalid login!");
             return "users/login";
        }

        notifyService.addInfoMessage("Login successful");
        return "redirect:/";
    }

}
