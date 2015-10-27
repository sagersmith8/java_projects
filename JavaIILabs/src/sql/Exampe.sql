declare
	todays_date Date;
	current_day varchar2(9);
	current_month varchar2(9);
	
begin
	todays_date := SYSDATE;
	current_month := RTRIM(INITCAP(to_char(todays_date, 'MONTH')));
	current_day := to_char(todays_date, 'DAY');
	current_day := RTRIM(INITCAP(current_day));
	
	IF current_day = 'Monday' AND current_month = 'April' THEN
		DBMS_OUTPUT.PUT_LINE('Today is Monday in April!');
	ELSIF current_day = 'Saturday' THEN
		DBMS_OUTPUT.PUT_LINE('Today is Saturday!');
	ELSE
		DBMS_OUTPUT.PUT_LINE('Today is another day!');
	END IF;
end;

/

