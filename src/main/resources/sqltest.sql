-- test select statements --
select *
from test;

-- test delete statements --
delete
from benutzer
where id = 4;

-- test update statements --
update benutzer
set maxamount = 'Maxi'
where id = 6;
update benutzer
set name = 3
where maxamount = 6;


-- test insert statements --
insert into benutzer (id, name, amount, maxamount)
values (1, 'Maxi', 10, 10);
insert into benutzer (id, name, amount, maxamount)
values (2, 'Isabella', 5, 10);
insert into benutzer (id, name, amount, maxamount)
values (3, 'Emilia', 16.6, 17.6);
insert into benutzer (id, name, amount, maxamount)
values (4, 'Emma', 688898998989.00, 1087989798789.98);