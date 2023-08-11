
alter table review drop column users_email;

drop table review_imgs ;

drop table review ;

ALTER TABLE store.review drop foreign KEY FKm6mojpwskod0qsp04wuwdgau6;

#select 
select * from review r inner join review_imgs ri ON r.id = ri.review_id;

select * from review_imgs ri ;

select count(r.score), avg(r.score) from review r where r.product_id  = 1 group by r.score;

select count(r.score), avg(r.score) from review r where r.product_id = 1;
      
#delete
delete from review where 1=1;

delete from review_imgs where 1=1;


