package com.tripmanager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tripmanager.model.User;
import com.tripmanager.service.UserService;

@Controller
public class UserController {
	
	/* TODO:
	 * create user not found exception
	 * Make sure the confirm password works
	 * Password should be stored encrypted
	 * Username should be unique
	 */
	
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		
		return "login.html";
	}
	
	@RequestMapping("/createAccount")
	public String createAccount(Model model) {
		model.addAttribute("user", new User());
		return "signup.html";
	}
	
	@PostMapping("/createAccount")
	public String addTrip(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		try { 
			userService.addUser(user);
		} catch(Exception e ) {
			System.out.println("Exception was found when saving");
		}
		return "redirect:/login";
	}
	

}
