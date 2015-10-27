declare
curr_customer_first varchar2(20) := 'Neal';
curr_customer_last varchar2(20) := 'Graham';
curr_address varchar2(50) := '9815 Circle Dr.';
curr_city varchar2(20) := 'Tallahassee';
curr_state varchar2(2) := 'FL';
curr_zip number := 32308;

begin
DBMS_OUTPUT.PUT_LINE(initcap(curr_customer_first) || ' ' || initcap(curr_customer_last));
DBMS_OUTPUT.PUT_LINE(initcap(curr_address));
DBMS_OUTPUT.PUT_LINE(initcap(curr_city || ' ' || curr_state || ', ' || curr_zip));
end;

/

