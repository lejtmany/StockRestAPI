package com.lejtman.joseph.services;

import com.lejtman.joseph.model.domain.User;

public interface UserService {
	
	Iterable<User> getAllUsers();
	User saveUser(User u);
	User getUserById(long id);
}
