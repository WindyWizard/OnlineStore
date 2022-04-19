package com.myproject.onlinestore.repository.favouriteGood;

import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FavouriteGoodRepository extends CrudRepository<FavouriteGoodEntity, Long> {
	List<FavouriteGoodEntity> findAllByCustomer(CustomerEntity customer);
}