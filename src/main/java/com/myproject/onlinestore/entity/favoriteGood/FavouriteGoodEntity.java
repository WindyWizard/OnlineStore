package com.myproject.onlinestore.entity.favouriteGood;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.entity.product.ProductEntity;

@Entity
@Data
@Table(name = "favorite_goods")
public class FavouriteGoodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@OneToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
}