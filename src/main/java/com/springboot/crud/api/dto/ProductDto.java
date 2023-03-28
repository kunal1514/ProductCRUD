package com.springboot.crud.api.dto;

import org.springframework.stereotype.Component;

@Component
public class ProductDto {
	
	private Long id;
	private String title;
	private String category;
	private Double price;
	private String vendorName;
	
	public ProductDto() {
		
	}

	public ProductDto(Long id, String title, String category, Double price, String vendorName) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.price = price;
		this.vendorName = vendorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", vendorName=" + vendorName + "]";
	}
	
}
