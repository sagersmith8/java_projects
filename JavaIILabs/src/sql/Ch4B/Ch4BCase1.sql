declare
	cursor customer_cursor is
		select c_first, c_last, c_address, c_dphone
		from customer;
	customer_row customer_cursor%ROWTYPE;
begin
	dbms_output.put_line('Clearwater Traders Mailing List...');
	
	for customer_row in customer_cursor loop
		dbms_output.put_line(' Customer: ' || customer_row.c_first || ' ' || customer_row.c_last || ' Address: ' || customer_row.c_address || ' Phone: ' || customer_row.c_dphone);
	end loop;
end;
/


