CREATE TABLE favorite_goods (
	id BIGSERIAL PRIMARY KEY,
	product_id INT NOT NULL,
	customer_id INT NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products(id),
	FOREIGN KEY (customer_id) REFERENCES customers(id)
)