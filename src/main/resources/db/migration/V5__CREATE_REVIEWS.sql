CREATE TABLE reviews (
	id BIGSERIAL PRIMARY KEY,
	content TEXT NOT NULL,
	productId INT NOT NULL,
	FOREIGN KEY (productId) REFERENCES products(id)
)