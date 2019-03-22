package com.jmunoz.blog.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmunoz.blog.dto.UserDto;
import com.jmunoz.blog.forms.LoginForm;
import com.jmunoz.blog.models.User;
import com.jmunoz.blog.services.NotificationService;
import com.jmunoz.blog.services.UserService;
import com.jmunoz.blog.validation.UsernameExistsException;

@Controller
//@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;
    
	private Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping("/users/login")
    public String login() {
        return "users/login";
    }
    
    
    @GetMapping("/users")
    public String listUsers(Model theModel) {
    	
    	// get users from the service
    	List<User> theUsers = userService.findAll();
    					
		// add the users to the model
		theModel.addAttribute("users", theUsers);
		
    	return "users/index";
    }
    
    
    @GetMapping("/users/register")
    public String showUserRegistrationForm(Model theModel) {
    	
    	theModel.addAttribute("user", new UserDto());
    	return "users/register";
    }
    
    
    @PostMapping("/users/register")
    public String processUserRegistrationForm(
    			@Valid @ModelAttribute("user") UserDto theUserDto,
    			BindingResult theBindingResult,
    			Model theModel) {
    	
    	String userName = theUserDto.getUsername();		
		logger.info("Processing registration form for: " + userName);
    	
    	// form validation
    	if (theBindingResult.hasErrors()) {
    		notifyService.addErrorMessage("User name/password can not be empty.");    		
    		return "users/register";
    	}
    	
    	User registered = null;
    	try {
            registered = userService.create(theUserDto);

		} catch (UsernameExistsException e) {
			logger.warning("Username already exists.");
			theModel.addAttribute("userDto", new UserDto());
			theBindingResult.rejectValue("username", "message.regError", e.getMessage());
		}
        if (theBindingResult.hasErrors()) {
            return "users/register";
        } 
        else {
        	logger.info("Successfully created user: " + userName);
            return "redirect:/";
        }
   	
    }

}
