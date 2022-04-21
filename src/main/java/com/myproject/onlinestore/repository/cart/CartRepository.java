package com.myproject.onlinestore.repository.cart;

import com.myproject.onlinestore.entity.cart.CartEntity;
import com.myproject.onlinestore.entity.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CartRepository extends CrudRepository<CartEntity, Long> {
	CartEntity findByCustomerPhone(String customerPhone);
	void deleteByProduct(ProductEntity product);
}