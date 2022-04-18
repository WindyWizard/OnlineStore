CREATE TABLE customers (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255),
	phone VARCHAR(255) NOT NULL UNIQUE,
	email VARCHAR(255) UNIQUE
)