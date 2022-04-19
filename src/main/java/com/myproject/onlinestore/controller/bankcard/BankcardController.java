package com.myproject.onlinestore.controller.bankcard;

import com.myproject.onlinestore.service.bankcard.BankcardService;
import com.myproject.onlinestore.repository.bankcard.BankcardRepository;
import com.myproject.onlinestore.entity.bankcard.BankcardEntity;
import com.myproject.onlinestore.exception.bankcard.BankcardNotFoundException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotCreatedException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotDeletedException;
import com.myproject.onlinestore.exception.customer.CustomerNotFoundException;
import com.myproject.onlinestore.model.bankcard.Bankcard;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.service.customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class BankcardController {

	private BankcardService bankcardService;
	private CustomerService customerService;

	@Autowired
	public BankcardController(BankcardService bankcardService, CustomerService customerService) {
		this.bankcardService = bankcardService;
		this.customerService = customerService;
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@GetMapping("/bankcard/info")
	public List<Bankcard> getBankcards(Principal principal, HttpServletResponse response) throws IOException {
		try {
			return bankcardService.getBankcards(
				CustomerEntity.getEntity(
					customerService.getCustomer(principal.getName())));
		} catch (BankcardNotFoundException | CustomerNotFoundException e) {
			response.sendError(400);
			return null;
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/bankcard/add")
	public ResponseEntity addBankcard(@RequestBody BankcardEntity bankcard, Principal principal) {
		try {
			bankcard.setCustomer(
				CustomerEntity.getEntity(
					customerService.getCustomer(principal.getName())));
			bankcardService.addBankcard(bankcard);
			return ResponseEntity.ok("Bankcard added successfully!"); 
		} catch (BankcardNotCreatedException | CustomerNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/bankcard/delete")
	public ResponseEntity deleteBankcard(@RequestBody BankcardEntity bankcard, Principal principal) {
		try {
			bankcardService.deleteBankcard(bankcard);
			return ResponseEntity.ok("Bankcard deleted successfully!"); 
		} catch (BankcardNotDeletedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}