package com.lejtman.joseph.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lejtman.joseph.model.domain.User;
import com.lejtman.joseph.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User saveUser(User u) {
		return userRepository.save(u);
	}
	

	/**
	 * @param userRepository the userRepository to set
	 */
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}


	

}
