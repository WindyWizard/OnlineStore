package com.myproject.onlinestore.service.customer;

import com.myproject.onlinestore.repository.customer.CustomerRepository;
import com.myproject.onlinestore.repository.user.UserRepository;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.entity.user.UserEntity;
import com.myproject.onlinestore.exception.customer.CustomerNotFoundException;
import com.myproject.onlinestore.exception.customer.CustomerNotEditedException;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.myproject.onlinestore.service.security.SecurityService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private UserRepository userRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
	}

	public CustomerEntity getCustomer(String phone) throws CustomerNotFoundException {
		try {
			return customerRepository.findByPhone(phone);
		} catch (Exception e) {
			throw new CustomerNotFoundException(String.format("Customer not found. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void editCustomer(String phone, CustomerEntity fromRequest, UserEntity user) throws CustomerNotEditedException {
		try {
			String newPhone = fromRequest.getPhone();
			String password = userRepository.findByPhone(phone).getPassword();

			user.setId(userRepository.findByPhone(phone).getId());
			user.setPhone(newPhone);
			user.setUsername(fromRequest.getName());
			user.setPassword(password);
			userRepository.save(user);

			CustomerEntity fromDB = customerRepository.findByPhone(phone);
			fromDB.setName(fromRequest.getName());
			fromDB.setPhone(newPhone);
			fromDB.setEmail(fromRequest.getEmail());
			customerRepository.save(fromDB);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
				newPhone, password, SecurityService.getAuthorities(user));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			throw new CustomerNotEditedException(String.format("Customer not edited. Cause: %s", 
				e.toString()));	
		}
	}
}