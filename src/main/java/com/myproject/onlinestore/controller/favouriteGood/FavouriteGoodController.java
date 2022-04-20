package com.myproject.onlinestore.controller.bankcard;

import com.myproject.onlinestore.service.favouriteGood.FavouriteGoodService;
import com.myproject.onlinestore.repository.favouriteGood.FavouriteGoodRepository;
import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotFoundException;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotCreatedException;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotDeletedException;
import com.myproject.onlinestore.model.favouriteGood.FavouriteGood;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import com.myproject.onlinestore.exception.customer.CustomerNotFoundException;
import com.myproject.onlinestore.service.customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class FavouriteGoodController {

	private FavouriteGoodService favouriteGoodService;
	private CustomerService customerService;

	@Autowired
	public FavouriteGoodController(FavouriteGoodService favouriteGoodService, CustomerService customerService) {
		this.favouriteGoodService = favouriteGoodService;
		this.customerService = customerService;
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@GetMapping("/favourites/info")
	public List<FavouriteGood> getFavouriteGoods(Principal principal, HttpServletResponse response) throws IOException {
		try {
			return favouriteGoodService.getFavouriteGoods(principal.getName());
		} catch (FavouriteGoodNotFoundException e) {
			return null;
		}
	}

	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/favourites/add/{product}")
	public ResponseEntity addFavouriteGood(@PathVariable("product") String product, Principal principal) {
		try {
			favouriteGoodService.addFavouriteGood(principal.getName(), product);
			return ResponseEntity.ok("Product added to favourites successfully!"); 
		} catch (FavouriteGoodNotCreatedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('STANDART_PERMISSION')")
	@PostMapping("/favourites/delete/{product}")
	public ResponseEntity deleteFavouriteGood(@PathVariable("product") String product, Principal principal) {
		try {
			favouriteGoodService.deleteFavouriteGood(product, principal.getName());
			return ResponseEntity.ok("Product deleted from favourites successfully!"); 
		} catch (FavouriteGoodNotDeletedException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}