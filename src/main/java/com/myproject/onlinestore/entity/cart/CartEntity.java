package com.myproject.onlinestore.entity.cart;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.model.cart.Cart;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class CartEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "product_id", fetch = FetchType.EAGER)
	private List<ProductEntity> products;

	@Column(name = "customer_phone")
	private String customerPhone;
}