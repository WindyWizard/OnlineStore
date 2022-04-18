package com.myproject.onlinestore.repository.customer;

import com.myproject.onlinestore.entity.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
	CustomerEntity findByPhone(String phone);
	CustomerEntity findByName(String name);
}