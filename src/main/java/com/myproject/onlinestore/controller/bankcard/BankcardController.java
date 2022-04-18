package com.myproject.onlinestore.controller.bankcard;

import com.myproject.onlinestore.service.bankcard.BankcardService;
import com.myproject.onlinestore.repository.bankcard.BankcardRepository;
import com.myproject.onlinestore.entity.bankcard.BankcardEntity;
import com.myproject.onlinestore.exception.bankcard.BankcardNotFoundException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotCreatedException;
import com.myproject.onlinestore.exception.bankcard.BankcardNotDeletedException;
import com.myproject.onlinestore.model.bankcard.Bankcard;
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
				customerService.getCustomer(principal.getName()));
		} catch (BankcardNotFoundException e) {
			response.sendError(400);
			return null;
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/bankcard/add")
	public ResponseEntity addBankcard(@RequestBody Bankcard bankcard, Principal principal) {
		try {
			bankcard.setCustomer(
				customerService.getCustomer(principal.getName()));
			bankcardService.addBankcard(bankcard);
			return ResponseEntity.ok("Bankcard added successfully!"); 
		} catch (CustomerNotEditedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/bankcard/add")
	public ResponseEntity deleteBankcard(@RequestBody Bankcard bankcard, Principal principal) {
		try {
			bankcard.setCustomer(
				customerService.getCustomer(principal.getName()));
			bankcardService.deleteBankcard(bankcard);
			return ResponseEntity.ok("Bankcard added successfully!"); 
		} catch (CustomerNotEditedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}