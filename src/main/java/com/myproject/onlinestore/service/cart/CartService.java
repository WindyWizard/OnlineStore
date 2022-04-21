package com.myproject.onlinestore.service.cart;

import com.myproject.onlinestore.model.customer.Customer;
import com.myproject.onlinestore.repository.cart.CartRepository;
import com.myproject.onlinestore.entity.cart.CartEntity;
import com.myproject.onlinestore.model.cart.Cart;
import com.myproject.onlinestore.entity.product.ProductEntity;
import com.myproject.onlinestore.repository.product.ProductRepository;
import com.myproject.onlinestore.repository.customer.CustomerRepository;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.myproject.onlinestore.entity.customer.CustomerEntity;
import java.util.List;
import java.util.ArrayList;

@Service
public class CartService {

	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public CartService(CartRepository cartRepository, 
		ProductRepository productRepository, CustomerRepository customerRepository) {

		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	public Cart getCart(String customerPhone) {
		return cartRepository.findByCustomerPhone(customerPhone);
	}

	public void addProductToCart(String customer, String productName) throws ProductNotAddedToCartException {
		try {
			CartEntity cart = cartRepository.findByCustomerPhone(customerPhone);
			ProductEntity product = productRepository.findByName(productName);
			cart.getProducts().add(product);

			cartRepository.save(cart);
		} catch (Exception e) {
			throw new ProductNotAddedToCartException(String.format("Product not added to cart. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void deleteProductFromCart(String product, String customerPhone) throws  {
		try {
			CartEntity cart = cartRepository.findByCustomerPhone(customerPhone);
			List<ProductEntity> products = cart.getProducts();

			for (int i = 0; i < products.size(); i++) ( : products) {
				ProductEntity product = products.get(i);

				if (product.getName().equals(product)) {
					products.remove(product);
				}
			}

		} catch (Exception e) {
			throw new ProductNotDeletedFromCartException(String.format("Product not deleted from cart. Cause: %s", 
				e.toString()));	
		}
	}
}