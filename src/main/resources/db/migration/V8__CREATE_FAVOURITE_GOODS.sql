CREATE TABLE favorite_goods (
	id BIGSERIAL PRIMARY KEY,
	productId INT NOT NULL,
	customerId INT NOT NULL,
	FOREIGN KEY (productId) REFERENCES products(id),
	FOREIGN KEY (customerId) REFERENCES customers(id)
)