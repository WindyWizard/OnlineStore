package com.myproject.onlinestore.model.bankcard;

import lombok.*;
import com.myproject.onlinestore.entity.customer.CustomerEntity;

@Data
public class Bankcard {

	private String bankcardNumber;
	private CustomerEntity customer;
}