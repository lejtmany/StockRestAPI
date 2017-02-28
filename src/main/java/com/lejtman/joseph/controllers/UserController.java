package com.lejtman.joseph.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lejtman.joseph.model.domain.User;
import com.lejtman.joseph.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User createUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@RequestMapping("/{userId}")
	public User getUserById(@PathVariable long userId){
		User user = userService.getUserById(userId);
		if(user == null){
			throw new UserNotFoundException(userId);
		}
		return user;
	}

	/**
	 * @param userService the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
