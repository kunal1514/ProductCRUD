package com.springboot.crud.api.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.springboot.crud.api.dto.AdminDto;
import com.springboot.crud.api.dto.ProductDto;
import com.springboot.crud.api.dto.ResponseDto;
import com.springboot.crud.api.dto.VendorDto;
import com.springboot.crud.api.model.Admin;
import com.springboot.crud.api.model.Product;
import com.springboot.crud.api.model.User;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.repository.UserRepository;
import com.springboot.crud.api.service.AdminService;
import com.springboot.crud.api.service.VendorService;

@RestController
@CrossOrigin(origins = {"http://localhost:4040"})
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ResponseDto repsonseDto;
	
	@Autowired
	private AdminDto adminDto;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add/admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin admin) {
		
		User user = admin.getUser();
		if(user == null) {
			repsonseDto.setMsg("User data is not present");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repsonseDto);
		}
		
		user.setRole("ADMIN");
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		user = userRepository.save(user);
		admin.setUser(user);
		Admin adminDB = adminService.addAmin(admin);
		adminDto.setId(adminDB.getId());
		adminDto.setName(adminDB.getName());
		adminDto.setEmail(adminDB.getUser().getEmail());
		adminDto.setUserId(adminDB.getUser().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(adminDto);
		
	}
	
	@GetMapping("/get/all/vendors")
	public List<VendorDto> getAllVendors() {
		List<Vendor> vendors = vendorService.getAllVendors();
		List<VendorDto> dtoList = new ArrayList<>();
		for(Vendor v: vendors) {
			VendorDto dto = new VendorDto();
			dto.setId(v.getId());
			dto.setName(v.getName());
			dto.setContact_number(v.getContact_number());
			dto.setAddress(v.getAddress());
			dto.setEmail(v.getUser().getEmail());
			dto.setUserId(v.getUser().getId());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@DeleteMapping("/remove/vendor/{id}")
	public ResponseEntity<Object> removeVendor(@PathVariable("id") Long id) {
		vendorService.removeVendor(id);
		repsonseDto.setMsg("Vendor Removed Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(repsonseDto);
	}

}
