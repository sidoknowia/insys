package com.insys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insys.dto.UserDto;
import com.insys.exceptions.UserAlreadyExistException;
import com.insys.model.User;

import com.insys.service.UserService;


@EnableAutoConfiguration
@Controller
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/register")
	public @ResponseBody String addNewUser(@RequestBody UserDto userDto) {
		try {
			userService.registerNewUserAccount(userDto);
		} catch (UserAlreadyExistException e) {
			return e.getMessage();
		}
		return "Saved";
	}
	
	@GetMapping(path = "/getUserByEmail")
	public @ResponseBody User getAllUsers(String email) {
		return userService.findUserByEmail(email);
	}
	
	@GetMapping(path = "/index")
	public @ResponseBody String index(){
		return "Secured Hello World!";
	}
}
