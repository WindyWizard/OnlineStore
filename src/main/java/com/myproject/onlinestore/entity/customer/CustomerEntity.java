package com.myproject.onlinestore.entity.customer;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;
}