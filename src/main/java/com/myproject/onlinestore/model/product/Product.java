package com.myproject.onlinestore.model.product;

import lombok.*;
import com.myproject.onlinestore.entity.product.ProductEntity;

@Data
public class Product {

	private String name;
	private String description;
	private int price;
	private int inStock;
	private String category;
	private double evaluation;

	public static Product getModel(ProductEntity productEntity) {
		Product product = new Product();

		product.setName(productEntity.getName());
		product.setDescription(productEntity.getDescription());
		product.setPrice(productEntity.getPrice());
		product.setInStock(productEntity.getInStock());
		product.setCategory(productEntity.getCategory());
		product.setEvaluation(productEntity.getEvaluation());

		return product;
	}
}