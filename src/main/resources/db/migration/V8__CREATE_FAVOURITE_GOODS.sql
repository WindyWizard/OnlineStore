CREATE TABLE favorite_goods (
	id BIGSERIAL PRIMARY KEY,
	product_id INT NOT NULL,
	customer_phone VARCHAR(255) NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products(id)
)