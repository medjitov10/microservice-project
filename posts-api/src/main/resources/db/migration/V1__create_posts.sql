create table posts(
 id serial,
 title varchar(50),
 description varchar(150),
 user_id int,
 foreign key (user_id) references users(id) on delete cascade
);