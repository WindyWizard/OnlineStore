package com.myproject.onlinestore.service.bankcard;

import com.myproject.onlinestore.repository.bankcard.BankcardRepository;
import com.myproject.onlinestore.entity.bankcard.BankcardEntity;
import com.myproject.onlinestore.exception.bankcard.BankcardNotFoundException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotCreatedException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotDeletedException;
import com.myproject.onlinestore.model.bankcard.Bankcard;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.myproject.onlinestore.entity.customer.CustomerEntity;

@Service
public class BankcardService {

	private BankcardRepository bankcardRepository;

	@Autowired
	public BankcardService(BankcardRepository bankcardRepository) {
		this.bankcardRepository = bankcardRepository;
	}

	public BankcardEntity getBankcards(CustomerEntity customer) throws BankcardNotFoundException {
		try {
			return bankcardRepository.findByCustomer(customer);
		} catch (Exception e) {
			throw new BankcardNotFoundException(String.format("Bankcard not found. Cause: %s", 
				e.toString()));	
		}
	}

	public void addBankcard(Bankcard bankcard) throws BankcardNotCreatedException {
		try {
			BankcardEntity bankcardEntity = new BankcardEntity();
			bankcardEntity.setBankcardNumber(bankcard.getBankcardNumber());
			bankcardEntity.setCustomer(bankcard.getCustomer());

			bankcardRepository.save(bankcardEntity);
		} catch (Exception e) {
			throw new BankcardNotCreatedException(String.format("Bankcard not created. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void deleteBankcard(Bankcard bankcard) throws BankcardNotDeletedException {
		try {
			bankcardRepository.deleteByBankcardNumber(bankcard.getBankcardNumber());
		} catch (Exception e) {
			throw new BankcardNotDeletedException(String.format("Bankcard not deleted. Cause: %s", 
				e.toString()));	
		}
	}
}