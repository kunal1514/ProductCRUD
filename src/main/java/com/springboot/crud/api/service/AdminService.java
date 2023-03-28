package com.springboot.crud.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.api.model.Admin;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin addAmin(Admin admin) {
		return adminRepository.save(admin);
	}

}
