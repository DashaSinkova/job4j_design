insert into ROLES(NAME) values('Администратор');
insert into ROLES(NAME) values('Программист');

insert into RULES(NAME) values('Запись');
insert into RULES(NAME) values('Чтение');
insert into RULES(NAME) values('Изменение кода');

insert into ROLES_RULES(ROLES_ID, RULES_ID) values (1, 1);
insert into ROLES_RULES(ROLES_ID, RULES_ID) values (1, 2);
insert into ROLES_RULES(ROLES_ID, RULES_ID) values (2, 3);

insert into CATEGORY(NAME) values('На ремонт');
insert into CATEGORY(NAME) values('На замену картриджа');
insert into CATEGORY(NAME) values('Инсталляция нового устройства');

insert into STATES(NAME) values('Новая');
insert into STATES(NAME) values('В работе');
insert into STATES(NAME) values('Выполнена');

insert into USERS(NAME, ROLES_ID) values('Даша', 2);

insert into ITEMS(NAME, USERS_ID,CATEGORY_ID, STATES_ID) values ('1231', 1, 1, 1);
insert into ITEMS(NAME, USERS_ID,CATEGORY_ID, STATES_ID) values ('1231kf', 1, 1, 1);

insert into COMENTS(NAME, ITEMS_ID) values ('заявку нужно перенести', 1);
insert into COMENTS(NAME, ITEMS_ID) values ('приоритет низкий', 1);

insert into ATTACHS(NAME, ITEMS_ID) values ('1.jpg', 1);