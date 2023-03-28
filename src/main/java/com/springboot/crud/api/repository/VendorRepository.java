package com.springboot.crud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.crud.api.model.User;
import com.springboot.crud.api.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	@Query("select v from Vendor v where v.user.email=?1")
	Vendor findVendorDetails(String username);
	
	

}
