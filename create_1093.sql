create table ROLES (
	ID serial primary key,
	NAME varchar(255) 
);
create table RULES (
	ID serial primary key,
	NAME varchar(255) 
);
create table CATEGORY(
	ID serial primary key,
	NAME varchar(255));
	
create table STATES(
	ID serial primary key,
	NAME varchar(255));
	
create table USERS(
	ID serial primary key,
	NAME varchar(255),
	ROLES_ID int references ROLES(ID)
);

create table ROLES_RULES(
	ID serial primary key,
	ROLES_ID int references ROLES(ID),
	RULES_ID int references RULES(ID)
);

create table ITEMS (
	ID serial primary key,
	NAME varchar(255),
	USERS_ID int references USERS(ID),
	CATEGORY_ID int references CATEGORY(ID),
	STATES_ID int references STATES(ID)
);

create table COMENTS(
	ID serial primary key,
	NAME varchar(255),
	ITEMS_ID int references ITEMS(ID)
);

create table ATTACHS(
	ID serial primary key,
	NAME varchar(255),
	ITEMS_ID int references ITEMS(ID));
	

