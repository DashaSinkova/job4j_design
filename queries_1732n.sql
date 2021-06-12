select p.name as Название_продукта, t.name as Тип_продукта from product p join type t on t.id = p.id_type
where t.name = 'СЫР';

select * 
from product 
where name like '%мороженное%'; 

select * 
from product
where current_date>expired_date;

select name, price
from product
order by price desc limit 1;

select t.name, count(p.id_type) 
from product p join type t on t.id = p.id_type
group by t.name;

select p.name as Название_продукта, t.name as Тип_продукта from product p join type t on t.id = p.id_type
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.id_type)
from product p join type t on t.id = p.id_type
group by t.name
having count(p.id_type) >= 3;

select p.name, t.name, p.expired_date, p.price 
from product p join type t on t.id = p.id_type
Order by price ASC;
