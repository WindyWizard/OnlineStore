CREATE TABLE purchases (
	id BIGSERIAL PRIMARY KEY,
	purchase_time TIMESTAMP NOT NULL,
	productId INT NOT NULL,
	customerId INT NOT NULL,
	FOREIGN KEY (productId) REFERENCES products(id),
	FOREIGN KEY (customerId) REFERENCES customers(id)
)