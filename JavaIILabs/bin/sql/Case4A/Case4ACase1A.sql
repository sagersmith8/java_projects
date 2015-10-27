declare
curr_customer_first varchar2(20) := 'Neal';
curr_customer_last varchar2(20) := 'Graham';
curr_order_number number := 1;
curr_order_date date := '29/MAY/2006';
curr_customer_name varchar2(30);

begin
	curr_customer_name := initcap(curr_customer_first || ' ' || curr_customer_last);
	DBMS_OUTPUT.PUT_LINE(curr_customer_name);
end; 

/

