CREATE TABLE owners(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);
CREATE TABLE serial_number(
	id SERIAL PRIMARY KEY,
	serial_number VARCHAR(255) unique,
	product_number 	VARCHAR(255),
	id_owners int REFERENCES owners(id)
);