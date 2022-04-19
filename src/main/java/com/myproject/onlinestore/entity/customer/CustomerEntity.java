package com.myproject.onlinestore.entity.customer;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.model.customer.Customer;

@Entity
@Data
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	public static CustomerEntity getEntity(Customer customer) {
		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setName(customer.getName());
		customerEntity.setPhone(customer.getPhone());
		customerEntity.setEmail(customer.getEmail());

		return customerEntity;
	}
}