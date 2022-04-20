package com.myproject.onlinestore.service.favouriteGood;

import com.myproject.onlinestore.model.customer.Customer;
import com.myproject.onlinestore.repository.favouriteGood.FavouriteGoodRepository;
import com.myproject.onlinestore.entity.favouriteGood.FavouriteGoodEntity;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotFoundException;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotCreatedException;
import com.myproject.onlinestore.exception.favouriteGood.FavouriteGoodNotDeletedException;
import com.myproject.onlinestore.model.favouriteGood.FavouriteGood;
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
public class FavouriteGoodService {

	private FavouriteGoodRepository favouriteGoodRepository;
	private ProductRepository productRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public FavouriteGoodService(FavouriteGoodRepository favouriteGoodRepository, 
		ProductRepository productRepository, CustomerRepository customerRepository) {

		this.favouriteGoodRepository = favouriteGoodRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	public List<FavouriteGood> getFavouriteGoods(String customerPhone) throws FavouriteGoodNotFoundException {
		try {
			List<FavouriteGoodEntity> entities = new ArrayList<>();

			(favouriteGoodRepository.findAllByCustomerPhone(customerPhone))
				.forEach(product -> entities.add(product));

			List<FavouriteGood> models = new ArrayList<>();
			entities.forEach(product -> models.add(FavouriteGood.getModel(product)));

			return models;

		} catch (Exception e) {
			throw new FavouriteGoodNotFoundException(String.format("Product not found. Cause: %s", 
				e.toString()));	
		}
	}

	public void addFavouriteGood(String customer, String product) throws FavouriteGoodNotCreatedException {
		try {
			FavouriteGoodEntity entity = new FavouriteGoodEntity();
			entity.setCustomerPhone(customerRepository.findByPhone(customer).getPhone());
			entity.setProduct(productRepository.findByName(product));

			favouriteGoodRepository.save(entity);
		} catch (Exception e) {
			throw new FavouriteGoodNotCreatedException(String.format("Product not added to favourites. Cause: %s", 
				e.toString()));	
		}
	}

	@Transactional
	public void deleteFavouriteGood(String product, String customerPhone) throws FavouriteGoodNotDeletedException {
		try {
			List<FavouriteGoodEntity> entities = new ArrayList<>();

			(favouriteGoodRepository.findAllByCustomerPhone(customerPhone))
				.forEach(entity -> entities.add(entity));

			for (FavouriteGoodEntity entity : entities) {
				if (entity.getProduct().getName().equals(product)) {
					favouriteGoodRepository.deleteByProduct(entity.getProduct());
				}
			}
		} catch (Exception e) {
			throw new FavouriteGoodNotDeletedException(String.format("Product not deleted from favourites. Cause: %s", 
				e.toString()));	
		}
	}
}