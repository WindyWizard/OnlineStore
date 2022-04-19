package com.myproject.onlinestore.model.customer;

import lombok.*;
import com.myproject.onlinestore.entity.customer.CustomerEntity;

@Data
public class Customer {

	private String name;
	private String phone;
	private String email;

	public static Customer getModel(CustomerEntity customerEntity) {
		Customer customer = new Customer();

		customer.setName(customerEntity.getName());
		customer.setPhone(customerEntity.getPhone());
		customer.setEmail(customerEntity.getEmail());

		return customer;
	}
}