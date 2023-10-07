-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into cliente(nome, cpf, email, senha, nascimento, telefone)
values ('Lucas', '999.999.999-99', 'lucas@gmail.com','123456', '2000-11-21', '(99) 99999-9999');
insert into cliente(nome, cpf, email, senha, nascimento, telefone)
values ('Matheus', '888.888.888-88', 'matheus@gmail.com', '222222', '2003-12-01', '(88) 88888-8888');
insert into cliente(nome, cpf, email, senha, nascimento, telefone)
values ('Calebe', '777.777.777-77', 'calebe@gmail.com', '333333', '2002-02-15', '(77) 77777-7777');