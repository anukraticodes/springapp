package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

import org.springframework.ui.Model;

@Controller

public class UserController {
	@Autowired(required = true)
	private UserRepo repo;
	
	@GetMapping("/")
	public String login(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "login";
	}
	@PostMapping("/userLogin")
	public String loginUser(@ModelAttribute("user") User user) {
		String userId=user.getUserId();
		Optional<User> userdata=repo.findById(userId);
		if(user.getPassword().equals(userdata.get().getPassword())) {
		return "home";
		}
		else {
			return "error";
		}
	}

}
