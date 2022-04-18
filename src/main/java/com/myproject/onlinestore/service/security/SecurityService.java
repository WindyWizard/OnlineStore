package com.myproject.onlinestore.service.security;

import com.myproject.onlinestore.repository.user.UserRepository;
import com.myproject.onlinestore.entity.user.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SecurityService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public SecurityService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		try {
			UserEntity user = userRepository.findByPhone(phone);

			return 
				new org.springframework.security.core.userdetails.User(
				phone, 
				user.getPassword(), 
				getAuthorities(user));

		} catch (Exception e) {
			throw new UsernameNotFoundException(
				String.format("UserNotFoundException. Cause: %s",
					e.toString()));
		}
	}

	public static Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
		return user.getRole()
			.getPermissions()
			.stream()
			.map(
				permission -> new SimpleGrantedAuthority(
					permission.name()))
			.collect(
				Collectors.toList());
	}
}