CREATE table products (
	id serial primary key,
	name varchar(255),
	price integer,
	archived boolean
);
insert into products (name,price,archived) values ('desk',1000,false);

update products set price=999;
delete from products;
select * from products;
