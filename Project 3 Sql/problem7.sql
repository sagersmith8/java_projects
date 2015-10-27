declare
  cursor calculation_query is
  select * 
  from coverable_items 
  where owned is null;
  calculated calculation_query%ROWTYPE;
  
  cost number;
begin
	for calculated in calculation_query loop
		dbms_output.put_line('item id: ' || calculated.item_id || ' description ' || calculated.description);
	end loop;
end; 

--item id: 2 description yourself