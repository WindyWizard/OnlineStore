package com.myproject.onlinestore.repository.product;

import com.myproject.onlinestore.entity.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	ProductEntity findByName(String name);
	void deleteByName(String name);
}