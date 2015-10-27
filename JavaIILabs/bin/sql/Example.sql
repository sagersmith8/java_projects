declare
	cursor item_cursor is
		select item_desc, sum(item_id) total 
		from item
		group by item_desc;
		
	current_item item_cursor%rowtype;
	
begin
	open item_cursor;
	loop
		fetch item_cursor into current_item;
		exit when item_cursor%notfound;
		dbms_output.put_line(' Item Description = ' || current_item.item_desc || ' Total = '
		|| current_item.total);
	end loop;
	close item_cursor;
end;

/
