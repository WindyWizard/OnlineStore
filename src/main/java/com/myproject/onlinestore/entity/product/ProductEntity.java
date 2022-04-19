package com.myproject.onlinestore.entity.product;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private int price;

	@Column(name = "in_stock")
	private int inStock;

	@Column(name = "category")
	private String category;

	@Column(name = "evaluation")
	private double evaluation;
}