declare
cursor cursor_total is
select sum(cost_per_month) 
as summedCost 
from coverages;

calculated cursor_total%ROWTYPE;

begin
	for calculated in cursor_total loop
		dbms_output.put_line('Total: ' || calculated.summedCost);
	end loop;
end; 
--Total: 675