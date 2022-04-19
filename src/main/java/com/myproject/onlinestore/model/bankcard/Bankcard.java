package com.myproject.onlinestore.model.bankcard;

import lombok.*;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.entity.bankcard.BankcardEntity;

@Data
public class Bankcard {

	private String bankcardNumber;

	public static Bankcard getModel(BankcardEntity bankcardEntity) {
		Bankcard bankcard = new Bankcard();

		bankcard.setBankcardNumber(bankcardEntity.getBankcardNumber());

		return bankcard;
	}
}