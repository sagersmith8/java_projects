declare
  cursor calculation_query is
  select user_name ,cost_per_month * MONTHS_BETWEEN(valid_through, sysdate) AS calc 
  from users, coverages 
  where users.coverage_id = coverages.coverage_id;
  calculated calculation_query%ROWTYPE;
  
  cost number;
begin
	for calculated in calculation_query loop
		dbms_output.put_line(calculated.user_name || ' owes ' || calculated.calc);
	end loop;
end; 

/*
troutslayer8@q.com owes 3391.045101553166069295101553166069295102
busmith@helena owes 1173.135304659498207885304659498207885305
leopard@gmail.com owes 32868.6578554360812425328554360812425329
sager8@gmail.com owes 109564.1804062126642771804062126642771804
 */
