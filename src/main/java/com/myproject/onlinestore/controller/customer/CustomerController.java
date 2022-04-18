package com.myproject.onlinestore.controller.customer;

import com.myproject.onlinestore.service.customer.CustomerService;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.entity.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.myproject.onlinestore.exception.customer.CustomerNotFoundException;
import com.myproject.onlinestore.exception.customer.CustomerNotEditedException;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@GetMapping("/customer/info")
	public CustomerEntity getCustomer(Principal principal, HttpServletResponse response) throws IOException {
		try {
			return customerService.getCustomer(principal.getName());
		} catch (CustomerNotFoundException e) {
			response.sendError(400);
			return null;
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/customer/edit")
	public ResponseEntity editCustomer(Principal principal, 
		@RequestBody CustomerEntity customer, UserEntity user) {
		try {
			customerService.editCustomer(principal.getName(), customer, user);
			return ResponseEntity.ok("Edit customer successfully!"); 
		} catch (CustomerNotEditedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}