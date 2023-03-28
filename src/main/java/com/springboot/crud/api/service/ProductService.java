package com.springboot.crud.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.api.model.Product;
import com.springboot.crud.api.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public List<Product> getAllProductsByVendor(String username) {
		return productRepository.getAllProductsByVendor(username);
	}

	public Product getProductById(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		Product product = optional.get();
		return product;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> getProductsByVendorId(Long id) {
		return productRepository.getProductsByVendorId(id);
	}

	public void removeAllProductsbyVendor(List<Product> products) {
		productRepository.deleteAll(products);
	}

}
