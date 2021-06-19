create table car_body(
	id serial primary key,
	name varchar(100)
);

create table car_engine(
	id serial primary key,
	name varchar(100)
);

create table car_transmission(
	id serial primary key,
	name varchar(100)
);

create table car(
	id serial primary key,
	name varchar(100),
	car_body_id int references car_body(id),
	car_engine_id int references car_engine(id),
	car_transmission_id int references car_transmission(id)
);

