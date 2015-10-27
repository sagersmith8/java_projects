declare
 cursor user_cursor is
 select *
 from users;
 
 user_data user_cursor%ROWTYPE;
begin
	for user_data in user_cursor loop
		dbms_output.put_line(' First Name '|| user_data.first_name|| ' Last Name ' ||user_data.last_name|| ' User Name ' ||user_data.user_name|| ' Password ' ||user_data.password|| ' Coverage Id ' ||user_data.coverage_id);
	end loop;
end; 

/*
First Name Sage Last Name Smith User Name sager8@gmail.com Password ssmith95
Coverage Id 5
First Name Buffy Last Name Smith User Name busmith@helena Password bsmith69
Coverage Id 3
First Name Brian Last Name Smith User Name troutslayer8@q.com Password bsmith67
Coverage Id 1
First Name Kyler Last Name Smith User Name leopard@gmail.com Password ksmith03
Coverage Id 4
*/