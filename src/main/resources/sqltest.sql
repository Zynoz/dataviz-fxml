insert into benutzer (1, 'Maxi', 10, 10);
insert into benutzer (2, 'Isabella', 5, 10);
insert into benutzer (3, 'Emilia', 16.6, 17.6);
insert into benutzer (4, 'Emma', 688898998989.00, 1087989798789.98);

update benutzer
set maxamount = 100
where id = 1;
update benutzer
set name = 'Manfred'
where id = 4;
update benutzer
set amount = 100000
where id = 2;
