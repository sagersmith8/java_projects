declare
EXAMPLE_DATE date := '31/JAN/2006';

begin
DBMS_OUTPUT.PUT_LINE(to_char(EXAMPLE_DATE, 'Month dd, yyyy'));	
end;

/
