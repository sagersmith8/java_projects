declare
  cursor calculation_query is
  select count(user_name) as calc 
  from users;
  calculated calculation_query%ROWTYPE;
begin
	for calculated in calculation_query loop
		dbms_output.put_line('Number of users '|| calculated.calc);
	end loop;
end; 

--Number of users 4