package com.springboot.crud.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.api.model.User;
import com.springboot.crud.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
