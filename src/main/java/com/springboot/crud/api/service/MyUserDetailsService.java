package com.springboot.crud.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.crud.api.model.User;
import com.springboot.crud.api.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("Invalid credentials");
		
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(sga);
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				list);
	}

}
