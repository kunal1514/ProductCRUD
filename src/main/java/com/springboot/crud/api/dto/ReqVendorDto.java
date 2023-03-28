package com.springboot.crud.api.dto;

import org.springframework.stereotype.Component;

@Component
public class ReqVendorDto {
	
	private Long id;
	private String name;
	private Long contact_number;
	private String address;
	private String email;
	private String password;
	
	public ReqVendorDto() {
		
	}

	public ReqVendorDto(Long id, String name, Long contact_number, String address, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.contact_number = contact_number;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContact_number() {
		return contact_number;
	}

	public void setContact_number(Long contact_number) {
		this.contact_number = contact_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ReqVendorDto [id=" + id + ", name=" + name + ", contact_number=" + contact_number + ", address="
				+ address + ", email=" + email + ", password=" + password + "]";
	}
	
}
