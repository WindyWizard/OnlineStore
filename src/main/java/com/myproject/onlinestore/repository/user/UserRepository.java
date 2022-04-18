package com.myproject.onlinestore.repository.user;

import com.myproject.onlinestore.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
	UserEntity findByPhone(String phone);
	void deleteByPhone(String Phone);
}