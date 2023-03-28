package com.springboot.crud.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.api.dto.ProductDto;
import com.springboot.crud.api.dto.ResponseDto;
import com.springboot.crud.api.model.Product;
import com.springboot.crud.api.model.Vendor;
import com.springboot.crud.api.service.ProductService;
import com.springboot.crud.api.service.VendorService;

@RestController
@CrossOrigin(origins = {"http://localhost:4040"})
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private ProductDto productDto;
	
	@PostMapping("/add/product")
	public ResponseEntity<Object> addProduct(@RequestBody Product product, Principal principal) {
		String username = principal.getName();
		Vendor vendor = vendorService.getVendorDetails(username);
		product.setVendor(vendor);
		productService.addProduct(product);
		responseDto.setMsg("Product added successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	@GetMapping("/get/products/vendor")
	public List<ProductDto> getAllProductsByVendor(Principal principal) {
		String username = principal.getName();
		List<Product> products = productService.getAllProductsByVendor(username);
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product p: products) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setCategory(p.getCategory());
			dto.setPrice(p.getPrice());
			dto.setVendorName(p.getVendor().getName());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@GetMapping("/get/product/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		if(product == null) {
			responseDto.setMsg("Product ID is Invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		productDto.setId(product.getId());
		productDto.setTitle(product.getTitle());
		productDto.setCategory(product.getCategory());
		productDto.setPrice(product.getPrice());
		productDto.setVendorName(product.getVendor().getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(productDto);
	}
	
	@PutMapping("/edit/product/{id}")
	public ResponseEntity<Object> editProduct(@RequestBody Product newProduct, @PathVariable("id") Long id) {
		Product productDB = productService.getProductById(id);
		if(productDB == null) {
			responseDto.setMsg("Product ID is Invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		productDB.setTitle(newProduct.getTitle());
		productDB.setCategory(newProduct.getCategory());
		productDB.setPrice(newProduct.getPrice());
		productService.addProduct(productDB);
		
		responseDto.setMsg("Product updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		responseDto.setMsg("Product deleted successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	@GetMapping("/get/products/vendor/{id}")
	public List<ProductDto> getProductsByVendorId(@PathVariable("id") Long id) {
		List<Product> products = productService.getProductsByVendorId(id);
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product p: products) {
			ProductDto dto = new ProductDto();
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setCategory(p.getCategory());
			dto.setPrice(p.getPrice());
			dto.setVendorName(p.getVendor().getName());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@DeleteMapping("/remove/all/products/vendor/{id}")
	public ResponseEntity<Object> removeAllProductsbyVendorid(@PathVariable("id") Long id) {
		List<Product> products = productService.getProductsByVendorId(id);
		productService.removeAllProductsbyVendor(products);
		responseDto.setMsg("Products deleted successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

}
