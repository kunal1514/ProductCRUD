package com.springboot.crud.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.api.dto.AdminDto;
import com.springboot.crud.api.dto.ResponseDto;
import com.springboot.crud.api.dto.VendorDto;
import com.springboot.crud.api.model.Admin;
import com.springboot.crud.api.model.User;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.repository.AdminRepository;
import com.springboot.crud.api.repository.VendorRepository;
import com.springboot.crud.api.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4040"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@GetMapping("/login")
	public Object userLogin(Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		if(user == null) {
			responseDto.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
		}
		user.setPassword("");
		return user;
	}
	
	@GetMapping("/get/user/details")
	public Object getUserDetails(Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		if(user == null) {
			responseDto.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
		}
		if(user.getRole().equalsIgnoreCase("ADMIN")) {
			Admin admin = adminRepository.findAdminDetails(username);
			AdminDto adminDto = new AdminDto(admin.getId(),
											 admin.getName(),
											 username,
											 admin.getUser().getId());
			return adminDto;
		}
		
		if(user.getRole().equalsIgnoreCase("VENDOR")) {
			Vendor vendor = vendorRepository.findVendorDetails(username);
			VendorDto vendorDto = new VendorDto(vendor.getId(),
												vendor.getName(),
												vendor.getContact_number(),
												username,
												vendor.getAddress(),
												vendor.getUser().getId());
			return vendorDto;
		}
		return null;
	}

}
