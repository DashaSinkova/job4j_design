create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(255),
	id_type int references type(id),
	price float
);