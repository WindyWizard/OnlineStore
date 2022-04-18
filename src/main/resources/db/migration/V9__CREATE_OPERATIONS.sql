CREATE TABLE operations (
	id BIGSERIAL PRIMARY KEY,
	content TEXT NOT NULL,
	customerId INT NOT NULL,
	FOREIGN KEY (customerId) REFERENCES customers(id)
)