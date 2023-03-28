package com.springboot.crud.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.api.model.User;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;

	public Vendor addVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	public Vendor getVendorDetails(String username) {
		return vendorRepository.findVendorDetails(username);
	}

	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}

	public void removeVendor(Long id) {
		vendorRepository.deleteById(id);
	}

	public Vendor getVendorById(Long id) {
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent()) {
			Vendor vendor = optional.get();
			System.out.println(vendor);
			return vendor;
		}
		return null;
	}

	

}
