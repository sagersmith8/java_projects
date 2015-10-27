declare	
	cursor customer_cursor is
		select item.item_id, inv_price
		from item, inventory
		where item.item_id = inventory.item_id
		order by item.item_id;
	customer_row customer_cursor%ROWTYPE;
	
	e_not_found exception;
begin
	loop 
		fetch customer_cursor into customer_row;
		if customer_cursor%NOTFOUND
			raise e_not_found;
		end if;
			exit when customer_cursor%NOTFOUND;
		dbms_output.put_line(' Item: ' || customer_row.item_id || ' Price: ' || customer_row.inv_price);
	end loop;
	
	exception when e_not_found;
		then dbms_output.put_line('not found');
end;
/


