insert into car_body(name) values('Кроссовер');
insert into car_body(name) values('Пикап');
insert into car_body(name) values('Внедорожник');
insert into car_body(name) values('Седан');
insert into car_body(name) values('Кабриолет');

insert into car_engine(name) values('Бензиновые');
insert into car_engine(name) values('Дизельные');
insert into car_engine(name) values('Газовые');
insert into car_engine(name) values('Газодизельные');
insert into car_engine(name) values('Роторно-поршневые');

insert into car_transmission(name) values('Механическая');
insert into car_transmission(name) values('Автомат');
insert into car_transmission(name) values('Гибридная');

insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Opel', 4, 1, 2);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Volvo', 4, 1, 2);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Nissan', 2, 4, 1);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Jeep', 3, 2, 1);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Mazda', 1, 1, 2);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Skoda', 4, 3, 2);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Land Rover', 4, 4, 1);
insert into car(name, car_body_id, car_engine_id, car_transmission_id) values ('Lexus', 1, 3, 2);

