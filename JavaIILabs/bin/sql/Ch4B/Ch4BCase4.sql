declare
	total number := 0;

	cursor customer_cursor is
		select c_first, c_last, sum(inv_price) total
		from customer, orders, order_line, inventory
		where customer.c_id = orders.c_id
		and orders.o_id = order_line.o_id
		and order_line.inv_id = inventory.inv_id
		group by c_first, c_last;
	customer_row customer_cursor%ROWTYPE;
begin
	
	for customer_row in customer_cursor loop
		total := customer_row.total;
		
		if total > 200 then
			total := total * .8;
		elsif total > 100 then
			total := total *.9;
		end if;
		
		dbms_output.put_line(' Customer: ' || customer_row.c_first || ' ' || customer_row.c_last || ' total: ' || customer_row.total || ' adjusted total: ' || total);
	end loop;
end;
/

