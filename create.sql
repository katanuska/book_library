create sequence hibernate_sequence start 1 increment 1
create table book_borrows (id int8 not null, book_id int8, borrow_date timestamp, expected_return_date timestamp, return_date timestamp, user_id int8, primary key (id))
create table users (id int8 not null, date_of_birth timestamp, name varchar(255), surname varchar(255), primary key (id))
alter table if exists book_borrows add constraint FK31h550ldpor6xls26lhrd5ufx foreign key (user_id) references users
create sequence hibernate_sequence start 1 increment 1
create table book_borrows (id int8 not null, book_id int8, borrow_date timestamp, expected_return_date timestamp, return_date timestamp, user_id int8 not null, primary key (id))
create table users (id int8 not null, date_of_birth timestamp, name varchar(255), surname varchar(255), primary key (id))
alter table if exists book_borrows add constraint FK31h550ldpor6xls26lhrd5ufx foreign key (user_id) references users
