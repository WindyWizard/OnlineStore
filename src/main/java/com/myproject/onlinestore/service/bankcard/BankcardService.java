package com.myproject.onlinestore.service.bankcard;

import com.myproject.onlinestore.model.customer.Customer;
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
import java.util.List;
import java.util.ArrayList;

@Service
public class BankcardService {

	private BankcardRepository bankcardRepository;

	@Autowired
	public BankcardService(BankcardRepository bankcardRepository) {
		this.bankcardRepository = bankcardRepository;
	}

	public List<Bankcard> getBankcards(CustomerEntity customer) throws BankcardNotFoundException {
		try {
			List<BankcardEntity> entities = new ArrayList<>();

			(bankcardRepository.findAllByCustomer(customer))
				.forEach(bankcard -> entities.add(bankcard));

			List<Bankcard> models = new ArrayList<>();
			entities.forEach(bankcard -> models.add(Bankcard.getModel(bankcard)));

			return models;

		} catch (Exception e) {
			throw new BankcardNotFoundException(String.format("Bankcard not found. Cause: %s", 
				e.toString()));	
		}
	}

	public void addBankcard(BankcardEntity bankcard) throws BankcardNotCreatedException {
		try {
			bankcardRepository.save(bankcard);
		} catch (Exception e) {
			throw new BankcardNotCreatedException(String.format("Bankcard not created. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void deleteBankcard(BankcardEntity bankcard) throws BankcardNotDeletedException {
		try {
			
			bankcardRepository.deleteByBankcardNumber(bankcard.getBankcardNumber());
		} catch (Exception e) {
			throw new BankcardNotDeletedException(String.format("Bankcard not deleted. Cause: %s", 
				e.toString()));	
		}
	}
}