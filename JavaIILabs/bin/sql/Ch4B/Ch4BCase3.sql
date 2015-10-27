declare
	cursor customer_cursor is
		select orders.o_id , item_desc
		from orders, order_line, inventory, item
		where item.item_id = inventory.item_id
		and orders.o_id = order_line.o_id
		and order_line.inv_id = inventory.inv_id
		group by orders.o_id, item_desc
		order by orders.o_id;
	customer_row customer_cursor%ROWTYPE;
begin
	
	for customer_row in customer_cursor loop
		dbms_output.put_line(' Order Id: ' || customer_row.o_id|| ' Item: ' || customer_row.item_desc);
	end loop;
end;
/


