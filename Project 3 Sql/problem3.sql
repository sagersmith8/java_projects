declare
	cursor three_table_data is
	select users.user_name, coverable_items.item_id, coverable_items.description 
	from users, coverable_items, coverages 
	where coverages.coverage_id = users.coverage_id and coverages.item_id = coverable_items.item_id;
	
	three_table_data_row three_table_data%ROWTYPE;
begin
	for three_table_data_row in three_table_data loop
		dbms_output.put_line(' User ID: ' || three_table_data_row.user_name || ' Coverage Id: ' || three_table_data_row.item_id || ' Coverable Items Description: ' || three_table_data_row.description);
	end loop;
end; 

/*
User ID: sager8@gmail.com Coverage Id: 2 Coverable Items Description: yourself
User ID: busmith@helena Coverage Id: 0 Coverable Items Description: car
User ID: troutslayer8@q.com Coverage Id: 0 Coverable Items Description: car
User ID: leopard@gmail.com Coverage Id: 1 Coverable Items Description:
motorcycle
*/