CREATE TABLE returns (
	id BIGSERIAL PRIMARY KEY,
	return_time TIMESTAMP NOT NULL,
	productId INT NOT NULL,
	customerId INT NOT NULL,
	FOREIGN KEY (productId) REFERENCES products(id),
	FOREIGN KEY (customerId) REFERENCES customers(id)
)