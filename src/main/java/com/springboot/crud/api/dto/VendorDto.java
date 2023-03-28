package com.springboot.crud.api.dto;

import org.springframework.stereotype.Component;

@Component
public class VendorDto {
	
	private Long id;
	private String name;
	private Long contact_number;
	private String email;
	private String address;
	private Long userId;
	
	public VendorDto() {
		
	}

	public VendorDto(Long id, String name, Long contact_number, String email, String address, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.contact_number = contact_number;
		this.email = email;
		this.address = address;
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "VendorDto [id=" + id + ", name=" + name + ", contact_number=" + contact_number + ", email=" + email
				+ ", address=" + address + ", userId=" + userId + "]";
	}
	
}
