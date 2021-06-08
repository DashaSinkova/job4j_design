insert into owners(name) values ('Дарья Синькова');
insert into owners(name) values ('Вера Петрова');
insert into owners(name) values ('Николаев Игорь');
select * from owners;
insert into serial_number(serial_number, product_number, id_owners) values ('aaaaaaaa', '2a', 16);
insert into serial_number(serial_number, product_number, id_owners) values ('bbbbbbbb', '2а', 17);
insert into serial_number(serial_number, product_number, id_owners) values ('cccccccc', '2а', 18);
select sn.serial_number as Серийник, sn.product_number as Продуктовый_номер, o.name as Владелец from serial_number sn join owners o on o.id = sn.id_owners;