package com.myproject.onlinestore.model.cart;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import com.myproject.onlinestore.entity.product.ProductEntity;
import java.util.List;

@Data
public class Cart {

	private List<ProductEntity> products;

	public static Cart getModel(CartEntity cartEntity) {
		Cart cart = new Cart();

		cart.setProducts(cartEntity.getProducts());

		return cart;
	}
}