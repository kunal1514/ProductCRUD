package com.springboot.crud.api.dto;

import org.springframework.stereotype.Component;

@Component
public class AdminDto {
	
	private Long id;
	private String name;
	private String email;
	private Long userId;
	
	public AdminDto() {
		
	}

	public AdminDto(Long id, String name, String email, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AdminDto [id=" + id + ", name=" + name + ", email=" + email + ", userId=" + userId + "]";
	}
	
}
