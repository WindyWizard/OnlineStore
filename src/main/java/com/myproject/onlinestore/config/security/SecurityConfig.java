package com.myproject.onlinestore.config.security;

import com.myproject.onlinestore.entity.user.Permission;
import com.myproject.onlinestore.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;  

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private SecurityService securityService;

	@Autowired
	public SecurityConfig(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()

			.antMatchers(HttpMethod.GET, "/registration")
				.permitAll()
			.antMatchers(HttpMethod.POST, "/registration")
				.permitAll()

			.anyRequest()
				.authenticated()

		.and()

			.formLogin()
				.loginProcessingUrl("/login")
				.usernameParameter("phone")
				.defaultSuccessUrl("/customer/info", true)

		.and()

			.logout()
				.deleteCookies("JSESSIONID");
	}
}