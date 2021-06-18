/*2. Выполнить запросы с left, rigth, full, cross соединениями*/
select * from employes e left join departments d on e.department_id = d.id;
select * from departments d right join employes e on e.department_id = d.id;
select * from departments d full join employes e on e.department_id = d.id;
select * from departments cross join employes;

/*3. Используя left join найти департаменты, у которых нет работников*/
select d.id, d.name from departments d left join employes e on e.department_id = d.id
where e.department_id is null;

/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат. */
select * from employes e left join departments d on e.department_id = d.id;
select * from departments d right join employes e on e.department_id = d.id;

/*Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары*/
select t1.name as Мужчина, t2.name as Женщина from teens t1 cross join teens t2
where t1.gender = 'м' and t2.gender = 'ж';


