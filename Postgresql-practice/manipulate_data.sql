--- Update the price and stock quantity of the book with the title 
UPDATE Books
	SET price=5, stock_quantity=25
	WHERE title= 'oop';

--- Delete the customer with the email 
DELETE FROM Customers
	WHERE email='anna@gmail.com';

--- Delete the order with the order_id 
DELETE FROM Orders
	WHERE order_id= 3;

--- Select all books with the genre 
SELECT * FROM Books where genre='drama';

--- Select all orders with the total amount greater than 50
SELECT * FROM Orders where total_amount>50;

--- Select all customers with the last name
SELECT * FROM Customers where last_name='mwiza';