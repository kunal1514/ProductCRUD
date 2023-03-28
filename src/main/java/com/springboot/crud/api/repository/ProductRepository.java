package com.springboot.crud.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.crud.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p where p.vendor.user.email=?1")
	List<Product> getAllProductsByVendor(String username);
	
	@Query("select p from Product p where p.vendor.id=?1")
	List<Product> getProductsByVendorId(Long id);

}
