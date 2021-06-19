/*1) Вывести список всех машин и все привязанные к ним детали.*/
select c.name as Марка, cb.name as Кузов, ce.name as Двигатель, ct.name as Коробка_Передач 
from car_body cb join car c on c.car_body_id = cb.id 
join car_engine ce on ce.id = c.car_engine_id 
join car_transmission ct on ct.id = c.car_transmission_id;

/*2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине.*/
select cb.name as Кузов from car c right join car_body cb on c.car_body_id = cb.id
where c.car_body_id is null;

select ce.name as Двигатель from car c right join car_engine ce on c.car_engine_id = ce.id
where c.car_engine_id is null;

select ct.name as Коробка_Передач from car c right join car_transmission ct on c.car_transmission_id = ct.id
where c.car_transmission_id is null;



