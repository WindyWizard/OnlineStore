package com.myproject.onlinestore.model.favouriteGood;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import com.myproject.onlinestore.entity.product.ProductEntity;

@Data
public class FavouriteGood {

	private ProductEntity product;

	public static FavouriteGood getModel(FavouriteGoodEntity favouriteGoodEntity) {
		FavouriteGood favouriteGood = new FavouriteGood();

		favouriteGood.setProduct(favouriteGoodEntity.getProduct());

		return favouriteGood;
	}
}