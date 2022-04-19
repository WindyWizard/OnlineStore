CREATE TABLE bank_cards (
	id BIGSERIAL PRIMARY KEY,
	bankcard_number VARCHAR(255) NOT NULL UNIQUE,
	customer_id INT NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customers(id)
)