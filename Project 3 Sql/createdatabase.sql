--This database holds information for an insurance company. It tracks information
--needed for insurance policy purchases, insurance policy, and coverable items for
--insurance
drop table users cascade constraints;
drop table coverages cascade constraints;
drop table coverable_items cascade constraints;
drop sequence ci_id_seq;


create table coverable_items(item_id number(3), constraint items_pk primary key(item_id), description varchar(20) not null, owned varchar(20));
-- Name                                      Null?    Type
 ----------------------------------------- -------- ---------------

-- ITEM_ID                                   NOT NULL NUMBER(3)
-- DESCRIPTION                               NOT NULL VARCHAR2(20)
-- OWNED


create sequence ci_id_seq increment by 1 start with 0 minvalue -1;
insert into coverable_items values (ci_id_seq.nextval,'car', 'yes');
insert into coverable_items values (ci_id_seq.nextval,'motorcycle', 'yes');
insert into coverable_items values (ci_id_seq.nextval,'yourself', null);

commit;

select * from coverable_items;

-- ITEM_ID DESCRIPTION          OWNED
-------- -------------------- -----
--       0 car                  yes
--       1 motorcycle           yes
--       2 yourself


create table coverages(coverage_id number(3), constraint coverage_pk primary key(coverage_id), description varchar(20) not null, cost_per_month number(20) not null, item_id number(3), constraint item_fk foreign key(item_id) references coverable_items(item_id), valid_through date not null);
-- Name                                      Null?    Type
 ----------------------------------------- -------- --------------

-- COVERAGE_ID                               NOT NULL NUMBER(3)
 --DESCRIPTION                               NOT NULL VARCHAR2(20)
 --COST_PER_MONTH                            NOT NULL NUMBER(20)
 --ITEM_ID                                            NUMBER(3)
 --VALID_THROUGH                             NOT NULL DATE
 
insert into coverages values (1, 'basic', 50, 0, '1/JAN/2020');
insert into coverages values (2, 'liability', 100, 0, '1/JAN/2017');
insert into coverages values (3, 'comprehensive', 150, 0, '1/JAN/2015');
insert into coverages values (4, 'motorcycle', 175, 1, '1/JAN/2030');
insert into coverages values (5, 'life', 200, 2, '1/JAN/2060');


--COVERAGE_ID DESCRIPTION          COST_PER_MONTH    ITEM_ID VALID_THR
----------- -------------------- -------------- ---------- ---------
--          1 basic                            50          0 01-JAN-20
--          2 liability                       100          0 01-JAN-17
--          3 comprehensive                   150          0 01-JAN-15
--          4 motorcycle                      175          1 01-JAN-30
--          5 life                            200          2 01-JAN-60


create table users(first_name varchar(20) not null, last_name varchar(20) not null, user_name varchar(20), constraint user_pk primary key(user_name), password varchar(20) not null, coverage_id number(3), constraint coverage_fk foreign key(coverage_id) references coverages(coverage_id));
-- Name                                      Null?    Type
 ----------------------------------------- -------- --------------

-- FIRST_NAME                                NOT NULL VARCHAR2(20)
-- LAST_NAME                                 NOT NULL VARCHAR2(20)
-- USER_NAME                                 NOT NULL VARCHAR2(20)
-- PASSWORD                                  NOT NULL VARCHAR2(20)
-- COVERAGE_ID                                        NUMBER(3)

insert into users values('Sage','Smith','sager8@gmail.com', 'ssmith95', 5);
insert into users values('Buffy','Smith','busmith@helena', 'bsmith69', 3);
insert into users values('Brian','Smith','troutslayer8@q.com', 'bsmith67', 1);
insert into users values('Tyzer','Smith','tyzersmith@gmail.com', 'tsmith97', 0);
insert into users values('Kyler','Smith','leopard@gmail.com', 'ksmith03', 4);

--NAME                                     USER_NAME
---------------------------------------- --------------------
--PASSWORD             COVERAGE_ID
-------------------- -----------
--BrianSmith                               troutslayer8@q.com
--bsmith67                       1

--TyzerSmith                               tyzersmith@gmail.com
--tsmith97                       0

--KylerSmith                               leopard@gmail.com
--ksmith03                       4


--NAME                                     USER_NAME
---------------------------------------- --------------------
--PASSWORD             COVERAGE_ID
-------------------- -----------
--SageSmith                                sager8@gmail.com
--ssmith95                       5

--BuffySmith                               busmith@helena
--bsmith69                       3


SQL> select * from users;

--FIRST_NAME           LAST_NAME            USER_NAME             PASSWORD       COVERAGE_ID
-------------------- -------------------- -------------------- ---------------- --------------

--Brian                Smith                troutslayer8@q.com     bsmith67          1                     

--Tyzer                Smith                tyzersmith@gmail.com   tsmith97          0

--Kyler                Smith                leopard@gmail.com      ksmith03          4

--Sage                 Smith                sager8@gmail.com       ssmith95          5

--Buffy                Smith                busmith@helena         bsmith69          3





--1)


--2)
select * from users;
/*
 * FIRST_NAME           LAST_NAME            USER_NAME
-------------------- -------------------- -------------------
PASSWORD             COVERAGE_ID
-------------------- -----------
Sage                 Smith                sager8@gmail.com
ssmith95                       5

Buffy                Smith                busmith@helena
bsmith69                       3

Brian                Smith                troutslayer8@q.com
bsmith67                       1


FIRST_NAME           LAST_NAME            USER_NAME
-------------------- -------------------- -------------------
PASSWORD             COVERAGE_ID
-------------------- -----------
Kyler                Smith                leopard@gmail.com
ksmith03                       4
 */





