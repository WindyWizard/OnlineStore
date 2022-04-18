CREATE TABLE bank_cards (
	id BIGSERIAL PRIMARY KEY,
	bankcard_number VARCHAR(255) NOT NULL UNIQUE,
	customerId INT NOT NULL,
	FOREIGN KEY (customerId) REFERENCES customers(id)
)