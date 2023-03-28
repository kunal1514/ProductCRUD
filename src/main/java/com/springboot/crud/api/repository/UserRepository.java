package com.springboot.crud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.crud.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email=?1")
	User findByUsername(String username);
	
	@Query("select u from User u where u.email=?1")
	User getUserByusername(String username);
	

}
