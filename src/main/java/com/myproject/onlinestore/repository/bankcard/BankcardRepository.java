package com.myproject.onlinestore.repository.bankcard;

import com.myproject.onlinestore.entity.bankcard.BankcardEntity;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface BankcardRepository extends CrudRepository<BankcardEntity, Long> {
	BankcardEntity findByCustomer(CustomerEntity customer);
	void deleteByBankcardNumber(String bankcardNumber);
}