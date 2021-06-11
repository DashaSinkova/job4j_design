select name, avg(price) from devices group by name;


select  p.name as Владелец , avg(d.price) as Средняя_стоимость_устройств from devices_people dp join devices d on d.id = dp.devices_id join people p on dp.people_id=p.id 
group by p.name;

select  p.name as Владелец , avg(d.price) as Средняя_стоимость_устройств from devices_people dp join devices d on d.id = dp.devices_id join people p on dp.people_id=p.id
group by p.name
having avg(d.price) < 30000;