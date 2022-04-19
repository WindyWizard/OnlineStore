package com.myproject.onlinestore.repository.bankcard;

import com.myproject.onlinestore.entity.bankcard.BankcardEntity;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BankcardRepository extends CrudRepository<BankcardEntity, Long> {
	List<BankcardEntity> findAllByCustomer(CustomerEntity customer);
	void deleteByBankcardNumber(String bankcardNumber);
}