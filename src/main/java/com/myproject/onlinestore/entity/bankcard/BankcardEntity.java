package com.myproject.onlinestore.entity.bankcard;

import lombok.*;
import javax.persistence.*;
import com.myproject.onlinestore.entity.customer.CustomerEntity;

@Entity
@Data
@Table(name = "bank_cards")
public class BankcardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "bankcard_number")
	private String bankcardNumber;

	@OneToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
}