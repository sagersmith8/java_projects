declare
inventory_id number := 5;
inventory_color varchar2(20) := 'Sky Blue';
inventory_price number := 259.99;
inventory_qoh number := 23;

begin
DBMS_OUTPUT.PUT_LINE('Inventory ID:'||inventory_id);
DBMS_OUTPUT.PUT_LINE('Color:'||inventory_color);
DBMS_OUTPUT.PUT_LINE('Price:'||inventory_price);
DBMS_OUTPUT.PUT_LINE('Quantity on Hand:'||inventory_qoh);

end;

/

