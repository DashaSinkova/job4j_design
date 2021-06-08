select * from owners;
select * from serial_number;
select sn.serial_number as Серийник, sn.product_number as Продуктовый_номер, o.name as Владелец from serial_number sn join owners o on o.id = sn.id_owners;
select * from serial_number sn join owners o on sn.id_owners = o.id;
select sn.serial_number, sn.product_number, o.name from serial_number sn join owners o on o.id = sn.id_owners;