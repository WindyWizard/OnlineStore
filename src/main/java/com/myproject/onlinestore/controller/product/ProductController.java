package com.myproject.onlinestore.controller.product;

import com.myproject.onlinestore.service.product.ProductService;
import com.myproject.onlinestore.repository.product.ProductRepository;
import com.myproject.onlinestore.entity.product.ProductEntity;
import com.myproject.onlinestore.model.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.myproject.onlinestore.exception.product.ProductNotFoundException;
import com.myproject.onlinestore.exception.product.ProductNotCreatedException;
import com.myproject.onlinestore.exception.product.ProductNotDeletedException;
import com.myproject.onlinestore.exception.product.ProductNotEditedException;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@GetMapping("/product/{name}")
	public Product getProduct(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		try {
			return productService.getProduct(name);
		} catch (ProductNotFoundException e) {
			response.sendError(400);
			return null;
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/product/edit/{name}")
	public ResponseEntity editProduct(@PathVariable("name") String name, 
		@RequestBody ProductEntity product) {
		try {
			productService.editProduct(name, product);
			return ResponseEntity.ok("Edit product successfully!"); 
		} catch (ProductNotEditedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/product/add")
	public ResponseEntity addProduct(@RequestBody ProductEntity product) {
		try {
			productService.addProduct(product);
			return ResponseEntity.ok("Product added successfully!"); 
		} catch (ProductNotCreatedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/product/delete/{name}")
	public ResponseEntity deleteProduct(@PathVariable("name") String name) {
		try {
			productService.deleteProduct(name);
			return ResponseEntity.ok("Product deleted successfully!"); 
		} catch (ProductNotDeletedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}