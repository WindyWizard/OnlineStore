package com.myproject.onlinestore.repository.favouriteGood;

import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import com.myproject.onlinestore.entity.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FavouriteGoodRepository extends CrudRepository<FavouriteGoodEntity, Long> {
	List<FavouriteGoodEntity> findAllByCustomerPhone(String customerPhone);
	void deleteByProduct(ProductEntity product);
}