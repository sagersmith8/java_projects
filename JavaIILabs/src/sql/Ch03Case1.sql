DROP TABLE order_line CASCADE CONSTRAINTS;
DROP TABLE shipment_line CASCADE CONSTRAINTS;
DROP TABLE shipment CASCADE CONSTRAINTS;
DROP TABLE inventory CASCADE CONSTRAINTS;
DROP TABLE color CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;2
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE order_source CASCADE CONSTRAINTS;
DROP TABLE customer CASCADE CONSTRAINTS;

CREATE TABLE customer
(c_id NUMBER(5), 
c_last VARCHAR2(30),
c_first VARCHAR2(30),
c_mi CHAR(1),
c_birthdate DATE,
c_address VARCHAR2(30),
c_city VARCHAR2(30),
c_state CHAR(2),
c_zip VARCHAR2(10),
c_dphone VARCHAR2(10),
c_ephone VARCHAR2(10),
c_userid VARCHAR2(50),
c_password VARCHAR2(15),
CONSTRAINT customer_c_id_pk PRIMARY KEY (c_id));

CREATE TABLE order_source
(os_id NUMBER(3),
os_desc VARCHAR2(30),
CONSTRAINT order_source_os_id_pk PRIMARY KEY(os_id));

CREATE TABLE orders
(o_id NUMBER(8), 
o_date DATE,
o_methpmt VARCHAR2(10),
c_id NUMBER(5),
os_id NUMBER(3),
CONSTRAINT orders_o_id_pk PRIMARY KEY (o_id),
CONSTRAINT orders_c_id_fk FOREIGN KEY (c_id) REFERENCES customer(c_id),
CONSTRAINT orders_os_id_fk FOREIGN KEY (os_id) REFERENCES order_source(os_id));

CREATE TABLE category
(cat_id NUMBER(2),
cat_desc VARCHAR2(20),
CONSTRAINT category_cat_id_pk PRIMARY KEY (cat_id));

CREATE TABLE item
(item_id NUMBER(8),
item_desc VARCHAR2(30),
cat_id NUMBER(2),
item_image BLOB,
CONSTRAINT item_item_id_pk PRIMARY KEY (item_id),
CONSTRAINT item_cat_id_fk FOREIGN KEY (cat_id) REFERENCES category(cat_id));

CREATE TABLE color
(color VARCHAR2(20),
CONSTRAINT color_color_pk PRIMARY KEY (color));

CREATE TABLE inventory
(inv_id NUMBER(10),
item_id NUMBER(8),
color VARCHAR2(20),
inv_size VARCHAR2(10),
inv_price NUMBER(6,2),
inv_qoh NUMBER(4),
CONSTRAINT inventory_inv_id_pk PRIMARY KEY (inv_id),
CONSTRAINT inventory_item_id_fk FOREIGN KEY (item_id) REFERENCES item(item_id),
CONSTRAINT inventory_color_fk FOREIGN KEY (color) REFERENCES color(color));

CREATE TABLE shipment
(ship_id NUMBER(10),
ship_date_expected DATE,
CONSTRAINT shipment_ship_id_pk PRIMARY KEY (ship_id));

CREATE TABLE shipment_line
(ship_id NUMBER(10), 
inv_id NUMBER(10),
sl_quantity NUMBER(4),
sl_date_received DATE, 
CONSTRAINT shipment_line_ship_id_fk FOREIGN KEY (ship_id) REFERENCES shipment(ship_id),
CONSTRAINT shipment_line_inv_id_fk FOREIGN KEY (inv_id) REFERENCES inventory(inv_id),
CONSTRAINT shipment_line_shipid_invid_pk PRIMARY KEY(ship_id, inv_id));

CREATE TABLE order_line 
(o_id NUMBER(8), 
inv_id NUMBER(10), 
ol_quantity NUMBER(4) NOT NULL,  
CONSTRAINT order_line_o_id_fk FOREIGN KEY (o_id) REFERENCES orders(o_id),
CONSTRAINT order_line_inv_id_fk FOREIGN KEY (inv_id) REFERENCES inventory(inv_id),
CONSTRAINT order_line_oid_invid_pk PRIMARY KEY (o_id, inv_id));

DESC CUSTOMER;
DESC ORDER_SOURCE;
DESC ORDERS;

------------Customer-------------------

INSERT INTO CUSTOMER VALUES
(1, 'Graham', 'Neal', 'R', to_date('12/10/1967', 'mm/dd/yyyy'), '9815 Circle Dr.', 'Tallahassee', 'FL', '32308', '9045551897', '904558599', 'grahamn', 'barbiecar');

INSERT INTO CUSTOMER VALUES
(2, 'Sanchez', 'Myra', 'T', to_date('08/14/1958', 'mm/dd/yyyy'), '172 Alto Park', 'Seattle', 'WA','42180', '4185551791', '4185556643', 'sanchezmt', 'qwert5');

INSERT INTO CUSTOMER VALUES
(3, 'Smith', 'Lisa', 'M', to_date('04/12/1960', 'mm/dd/yyyy'), '850 East Main', 'Santa Ana', 'CA', '51875', '3075557841', '3075559852', 'smithlm', 'joshua5');

INSERT INTO CUSTOMER VALUES
(4, 'Phelp', 'Paul', NULL, to_date('01/18/1981', 'mm/dd/yyyy'), '994 Kirkman Rd.', 'Northpoint', 'NY', '11795', '4825554788', '4825558219', 'phelpp', 'hold98er');

INSERT INTO CUSTOMER VALUES
(5, 'Lewis', 'Sheila', 'A', to_date('08/30/1978', 'mm/dd/yyyy'), '195 College Blvd.', 'Newton', 'GA', '37812', '3525554972', '3525551811', 'lewissa', '125pass');

INSERT INTO CUSTOMER VALUES
(6, 'James', 'Thomas', 'E', to_date('06/01/1973', 'mm/dd/yyyy'), '348 Rice Lane', 'Radcliff', 'WY', '87195', '7615553485', '7615553319', 'jamest', 'nok$tell');


----------------sequence---------------

CREATE SEQUENCE ORDERS_ID_SEQ INCREMENT BY 1;


----------------orders----------------------

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.CURRVAL, TO_DATE('05/29/2006', 'MM/DD/YYYY'), 'CC', 1, 2);

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.NEXTVAL, TO_DATE('05/29/2006', 'MM/DD/YYYY'), 'CC', 5, 6);

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.NEXTVAL, TO_DATE('05/31/2006', 'MM/DD/YYYY'), 'CHECK', 2, 2);

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.NEXTVAL, TO_DATE('05/31/2006', 'MM/DD/YYYY'), 'CC', 3, 3);

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.NEXTVAL, TO_DATE('06/01/2006', 'MM/DD/YYYY'), 'CC', 4, 6);

INSERT INTO orders VALUES
(ORDERS_ID_SEQ.NEXTVAL, TO_DATE('06/01/2006', 'MM/DD/YYYY'), 'CC', 4, 3);

----------order_source----------------------

INSERT INTO order_source VALUES (1, 'Winter 2005');
INSERT INTO order_source VALUES (2, 'Spring 2006');
INSERT INTO order_source VALUES (3, 'Summer 2006');
INSERT INTO order_source VALUES (4, 'Outdoor 2006');
INSERT INTO order_source VALUES (5, 'Children''s 2006');
INSERT INTO order_source VALUES (6, 'Web Site');

--DELETE FROM CUSTOMER WHERE C_ID like 5;
--update ORDERS set o_methpmt ='MO' where O_ID like 6;
--insert into ORDER_SOURCE (7, 'Winter 2006');

CREATE SEQUENCE CUSTOMER_ID_SEQ INCREMENT BY 10;
DROP TABLE CUSTOMER;
INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.CURRVAL, 'Graham', 'Neal', 'R', to_date('12/10/1967', 'mm/dd/yyyy'), '9815 Circle Dr.', 'Tallahassee', 'FL', '32308', '9045551897', '904558599', 'grahamn', 'barbiecar');

INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.NEXTVAL, 'Sanchez', 'Myra', 'T', to_date('08/14/1958', 'mm/dd/yyyy'), '172 Alto Park', 'Seattle', 'WA','42180', '4185551791', '4185556643', 'sanchezmt', 'qwert5');

INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.NEXTVAL, 'Smith', 'Lisa', 'M', to_date('04/12/1960', 'mm/dd/yyyy'), '850 East Main', 'Santa Ana', 'CA', '51875', '3075557841', '3075559852', 'smithlm', 'joshua5');

INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.NEXTVAL, 'Phelp', 'Paul', NULL, to_date('01/18/1981', 'mm/dd/yyyy'), '994 Kirkman Rd.', 'Northpoint', 'NY', '11795', '4825554788', '4825558219', 'phelpp', 'hold98er');

INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.NEXTVAL, 'Lewis', 'Sheila', 'A', to_date('08/30/1978', 'mm/dd/yyyy'), '195 College Blvd.', 'Newton', 'GA', '37812', '3525554972', '3525551811', 'lewissa', '125pass');

INSERT INTO CUSTOMER VALUES
(CUSTOMER_ID_SEQ.NEXTVAL, 'James', 'Thomas', 'E', to_date('06/01/1973', 'mm/dd/yyyy'), '348 Rice Lane', 'Radcliff', 'WY', '87195', '7615553485', '7615553319', 'jamest', 'nok$tell');
select * from CUSTOMER;

DROP sequence CUSTOMER_ID_SEQ;

ALTER TABLE ORDERS DISABLE orders_c_id_fk;
savepoint SAVEPOINT1;
TRUNCATE table CUSTOMER;
select * from CUSTOMER;
ROLLBACK TO SAVEPOINT1;
select * from CUSTOMER;
TRUNCATE table CUSTOMER;
TRUNCATE table ORDERS;
TRUNCATE table ORDER_SOURCE;
select * from ORDERS;
ROLLBACK TO SAVEPOINT1;
select * from ORDERS;
CREATE USER SCOTT;
GRANT ALL ON CUSTOMER TO SCOTT;
REVOKE ALL ON CUSTOMER FROM SCOTT;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE ORDERS CASCADE CONSTRAINTS;
DROP TABLE ORDER_SOURCE CASCADE CONSTRAINTS;
ROLLBACK TO SAVEPOINT1;

---------------------------------------------------------------------------
      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         6 James                          Thomas                         E
01-JUN-73 348 Rice Lane                  Radcliff                       WY
87195      7615553485 7615553319
jamest                                             nok$tell


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        11 Smith                          Lisa                           M
12-APR-60 850 East Main                  Santa Ana                      CA
51875      3075557841 3075559852
smithlm                                            joshua5


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        21 Phelp                          Paul
18-JAN-81 994 Kirkman Rd.                Northpoint                     NY
11795      4825554788 4825558219
phelpp                                             hold98er


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        31 Lewis                          Sheila                         A
30-AUG-78 195 College Blvd.              Newton                         GA
37812      3525554972 3525551811
lewissa                                            125pass


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        41 James                          Thomas                         E
01-JUN-73 348 Rice Lane                  Radcliff                       WY
87195      7615553485 7615553319
jamest                                             nok$tell

SQL> select * from CUSTOMER;

      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         1 Graham                         Neal                           R
10-DEC-67 9815 Circle Dr.                Tallahassee                    FL
32308      9045551897 904558599
grahamn                                            barbiecar


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         2 Sanchez                        Myra                           T
14-AUG-58 172 Alto Park                  Seattle                        WA
42180      4185551791 4185556643
sanchezmt                                          qwert5


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         3 Smith                          Lisa                           M
12-APR-60 850 East Main                  Santa Ana                      CA
51875      3075557841 3075559852
smithlm                                            joshua5


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         4 Phelp                          Paul
18-JAN-81 994 Kirkman Rd.                Northpoint                     NY
11795      4825554788 4825558219
phelpp                                             hold98er


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         5 Lewis                          Sheila                         A
30-AUG-78 195 College Blvd.              Newton                         GA
37812      3525554972 3525551811
lewissa                                            125pass


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
         6 James                          Thomas                         E
01-JUN-73 348 Rice Lane                  Radcliff                       WY
87195      7615553485 7615553319
jamest                                             nok$tell


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        11 Smith                          Lisa                           M
12-APR-60 850 East Main                  Santa Ana                      CA
51875      3075557841 3075559852
smithlm                                            joshua5


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        21 Phelp                          Paul
18-JAN-81 994 Kirkman Rd.                Northpoint                     NY
11795      4825554788 4825558219
phelpp                                             hold98er


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        31 Lewis                          Sheila                         A
30-AUG-78 195 College Blvd.              Newton                         GA
37812      3525554972 3525551811
lewissa                                            125pass


      C_ID C_LAST                         C_FIRST                        C
---------- ------------------------------ ------------------------------ -
C_BIRTHDA C_ADDRESS                      C_CITY                         C_
--------- ------------------------------ ------------------------------ --
C_ZIP      C_DPHONE   C_EPHONE
---------- ---------- ----------
C_USERID                                           C_PASSWORD
-------------------------------------------------- ---------------
        41 James                          Thomas                         E
01-JUN-73 348 Rice Lane                  Radcliff                       WY
87195      7615553485 7615553319
jamest                                             nok$tell




