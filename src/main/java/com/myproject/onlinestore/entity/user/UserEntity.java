package com.myproject.onlinestore.entity.user;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	public UserEntity() {
		this.role = Role.ROLE_USER;
	}
}