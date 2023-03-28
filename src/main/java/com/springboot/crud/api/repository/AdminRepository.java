package com.springboot.crud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.crud.api.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query("select a from Admin a where a.user.email=?1")
	Admin findAdminDetails(String username);

}
