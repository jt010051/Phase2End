Create Database gcEmployees;
use gcEmployees;

create table gcEmployees(id int (3)NOT NULL AUTO_INCREMENT Primary Key, fullName Varchar(50) NOT NULL,  
Email Varchar(50)not null,  userName Varchar(50) NOT NULL, password VarChar(20) not null);
insert into gcEmployees values (1, 'Jon-Thomas Smith', 'jt010051@gmail.com','jt0100','opensesame');
drop table gcEmployees;
select * from gcEmployees;