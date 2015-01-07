create database Salesforce;
select * from info;
use Salesforce;

DROP table info;

create table info (
id varchar (20) not null,
confidencial varchar(250),
primary key (id)
);


insert into info (id,confidencial)
values (123456, 'esto es información personal');
