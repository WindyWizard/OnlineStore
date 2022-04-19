package com.myproject.onlinestore.service.product;

import com.myproject.onlinestore.repository.product.ProductRepository;
import com.myproject.onlinestore.entity.product.ProductEntity;
import com.myproject.onlinestore.model.product.Product;
import com.myproject.onlinestore.exception.product.ProductNotFoundException;
import com.myproject.onlinestore.exception.product.ProductNotCreatedException;
import com.myproject.onlinestore.exception.product.ProductNotDeletedException;
import com.myproject.onlinestore.exception.product.ProductNotEditedException;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
public class ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product getProduct(String name) throws ProductNotFoundException {
		try {
			return Product.getModel(productRepository.findByName(name));
		} catch (Exception e) {
			throw new ProductNotFoundException(String.format("Product not found. Cause: %s", 
				e.toString()));	
		}
	}

	public void addProduct(ProductEntity product) throws ProductNotCreatedException {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			throw new ProductNotCreatedException(String.format("Product not created. Cause: %s", 
				e.toString()));	
		}
	}

	public void editProduct(String name, ProductEntity fromRequest) throws ProductNotEditedException {
		try {
			ProductEntity fromDB = productRepository.findByName(name);

			if (fromRequest.getName() != null) {
				fromDB.setName(fromRequest.getName());
			}

			if (fromRequest.getName() != null) {
				fromDB.setDescription(fromRequest.getDescription());
			}

			if (fromRequest.getPrice() != 0) {
				fromDB.setPrice(fromRequest.getPrice());
			}

			if (fromRequest.getInStock() != 0) {
				fromDB.setInStock(fromRequest.getInStock());
			}

			if (fromRequest.getCategory() != null) {
				fromDB.setCategory(fromRequest.getCategory());
			}

			if (fromRequest.getEvaluation() != 0.0) {
				fromDB.setEvaluation(fromRequest.getEvaluation());
			}

			productRepository.save(fromDB);

		} catch (Exception e) {
			throw new ProductNotEditedException(String.format("Product not edited. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void deleteProduct(String productName) throws ProductNotDeletedException {
		try {
			
			productRepository.deleteByName(productName);
		} catch (Exception e) {
			throw new ProductNotDeletedException(String.format("Product not deleted. Cause: %s", 
				e.toString()));	
		}
	}
}