declare
	loop_count binary_integer := 1;
begin
	loop
		dbms_output.put_line(loop_count);
		loop_count := loop_count + 1;
		if loop_count = 6 then
			exit;
		end if;
	end loop;
end;
/

declare
	loop_count binary_integer := 1;
begin		
	while loop_count < 6
	loop
		dbms_output.put_line(loop_count);
		loop_count := loop_count + 1;
	end loop;
end;
/

declare
	cursor customer_cursor is
		select c_first, c_last
		from customer;
	customer_row customer_cursor%ROWTYPE;
begin		
	for customer_row in customer_cursor loop
		dbms_output.put_line(customer_row.c_first || ' ' || customer_row.c_last);
	end loop;
end;
/

