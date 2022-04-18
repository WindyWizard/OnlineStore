package com.myproject.onlinestore.config.security;

import com.myproject.onlinestore.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DaoAuthenticationProviderConfig {

	private SecurityService securityService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public DaoAuthenticationProviderConfig(SecurityService securityService, 
		PasswordEncoder passwordEncoder) {

		this.securityService = securityService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider =
			new DaoAuthenticationProvider();

		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(securityService);

		return daoAuthenticationProvider;
	}
}