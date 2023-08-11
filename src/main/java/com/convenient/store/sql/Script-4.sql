
alter table `user` rename to users;

alter table users drop column is_admin;

drop table users_roles;

drop table users;

#select
select * from users;

select * from users_roles;

#update
update users set pw = '$2a$10$uH6pwEZXUNV9nOnKgZS6curg7OW2YCteXlF/6YEeL3mU82u4Wz4wC' where email = 'user00@notgmail.com';

#insert
insert into users values ('someEmail@gmail.com', false, 'nomalUser', 'someJpg.jpg', 1234);

#delete
delete from users_roles where users_id = 21;

delete from users where users.id = 21;




# $2a$10$uH6pwEZXUNV9nOnKgZS6curg7OW2YCteXlF/6YEeL3mU82u4Wz4wC == 1111

update users  set nick_name='사용자1' where id =1;


