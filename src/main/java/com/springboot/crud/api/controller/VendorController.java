package com.springboot.crud.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.api.dto.ReqVendorDto;
import com.springboot.crud.api.dto.ResponseDto;
import com.springboot.crud.api.dto.VendorDto;
import com.springboot.crud.api.model.User;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.repository.UserRepository;
import com.springboot.crud.api.service.VendorService;

@RestController
@CrossOrigin(origins = {"http://localhost:4040"})
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ResponseDto repsonseDto;
	
	
	@PostMapping("/add/vendor")
	public ResponseEntity<Object> addVendor(@RequestBody ReqVendorDto reqVendorDto) {
		User user = new User();
		
		user.setRole("VENDOR");
		String encryptedPassword = passwordEncoder.encode(reqVendorDto.getPassword());
		user.setPassword(encryptedPassword);
		user.setEmail(reqVendorDto.getEmail());
		user = userRepository.save(user);
		Vendor vendor = new Vendor();
		vendor.setName(reqVendorDto.getName());
		vendor.setContact_number(reqVendorDto.getContact_number());
		vendor.setAddress(reqVendorDto.getAddress());
		vendor.setUser(user);
		vendorService.addVendor(vendor);
		
		repsonseDto.setMsg("Vendor Signup Successful");
		return ResponseEntity.status(HttpStatus.OK).body(repsonseDto);
	}
	
	@GetMapping("/get/vendor/{id}")
	public Vendor getVendorById(@PathVariable("id") Long id) {
		Vendor vendor = vendorService.getVendorById(id);
		if(vendor == null) {
			throw new RuntimeException("Vendor ID is invalid or null");
		}
		return vendor;
	}
	
	@DeleteMapping("/remove/user/vendor/{id}")
	public void removeVendorUser(@PathVariable("id") Long id) {
		Vendor vendor = getVendorById(id);
		User user = vendor.getUser();
		userRepository.delete(user);
		repsonseDto.setMsg("User of Vendor removed successfully");
	}

}
