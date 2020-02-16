select *
from test;
insert into test (v1, v2, v3)
values (value1, value2, value3);
insert into test (v1, v2, v3)
values (value1, value2, value3);
insert into test (v1, v2, v3)
values (value1, value2, value3);
insert into test (v1, v2, v3)
values (value1, value2, value3);
delete
from test
where id = 4;
update test
set v2 = 'Maxi'
where id = 6;
update test
set v2 = 3
where id = 6;


insert into test (id, name, amount, maxamount)
values (1, Maxi, 10, 10);
insert into test (id, name, amount, maxamount)
values (2, Isabella, 5, 10);
insert into test (id, name, amount, maxamount)
values (3, Emilia, 16.6, 17.6);
insert into test (id, name, amount, maxamount)
values (4, Emma, 6, 10);