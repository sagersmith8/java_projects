declare
curr_phone varchar2(30) := '9045551897';
curr_areacode varchar2(30);
curr_number varchar2(30);

begin
curr_areacode := substr(curr_phone, 1, 3);
dbms_output.put_line('The area code is ' || curr_areacode);
curr_number := substr(curr_phone, 4, 9);
dbms_output.put_line('The phone number is: ' || curr_number);
end;

/
