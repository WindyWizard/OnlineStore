package com.myproject.onlinestore.service.auth;

import com.myproject.onlinestore.entity.user.UserEntity;
import com.myproject.onlinestore.repository.user.UserRepository;
import com.myproject.onlinestore.repository.customer.CustomerRepository;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.myproject.onlinestore.service.security.SecurityService;

@Service
public class AuthorizationService {

	private UserRepository userRepository;
	private CustomerRepository customerRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	public AuthorizationService(UserRepository userRepository, CustomerRepository customerRepository) {
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	public void authorization(UserEntity user) throws AuthorizationException {
		try {
			String phone = user.getPhone();
			String password = passwordEncoder.encode(user.getPassword());

			user.setPassword(password);
			userRepository.save(user);

			CustomerEntity customer = new CustomerEntity();
			customer.setName(user.getUsername());
			customer.setPhone(phone);

			customerRepository.save(customer);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
				phone, password, SecurityService.getAuthorities(user));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			throw new AuthorizationException(String.format("Authorization failed. Cause: %s",
				e.toString()));
		}
	}
}