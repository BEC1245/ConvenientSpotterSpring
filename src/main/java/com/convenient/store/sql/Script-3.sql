create table product (
	`pid` bigInt primary key auto_increment,
	`pname`	varchar(100) NOT NULL,
	`price`	int	NOT NULL,
	`content` varchar(1000) DEFAULT "해당 상품은 설명이 없습니다",
	`state`	varchar(50)	not null,
    `sname` varchar(100) NOT NULL,
    `img` varchar(300) not null,
    `delflag` boolean default 0,
    `regDate` datetime default now(),
    `modDate` datetime default now()
);

drop table product;

alter table product add column `delflag` tinyint default 0; 

ALTER TABLE product RENAME COLUMN `product_id` to id;

alter table product drop column mod_date;

#insert
insert into product (pname, price, `desc`, state, sname) value ('예시', 2000, '맛있다', '1+1', 'cu');

#select
select * from product where pname like '%정통)티본스테이크540G(냉동)%';

select * from product where state = 'NONE';

select * from product p where id = 229;

select count(*) from product p ;

#update
update product set state = 'NONE' where state != 'NONE';

update product set delflag = false where id = 234;


