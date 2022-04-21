CREATE TABLE carts (
	id BIGSERIAL PRIMARY KEY,
	product_id INT,
	customer_phone VARCHAR(255) NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products(id)
)