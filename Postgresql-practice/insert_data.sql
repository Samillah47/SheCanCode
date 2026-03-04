---Books table
INSERT INTO Books(
	book_id, title, author, genre, price, stock_quantity)
	VALUES 
	(1, 'Mankind', 'chaisse', 'drama', 700, 1000),
	(2, 'OOP', 'samillah', 'education', 400, 2000),
	(3, 'Java', 'keke', 'education', 300, 3000),
	(4, 'Spring boot', 'mwiza', 'education', 200, 4000),
	(5, 'JDBC', 'keza', 'education', 100, 5000);

---Customers table
INSERT INTO Customers(
	customer_id, first_name, last_name, email, phone)
	VALUES 
	(1, 'samillah', 'mutoni', 'samillah@gmail.com', 0788469171),
	(2, 'chanise', 'uwase', 'uwase@gmail.com', 0789765432),
	(3, 'keza', 'anna', 'anna@gmail.com', 0789654321),
	(4, 'mwiza', 'faith', 'faith@gmail.com', 098765432),
	(5, 'clesence', 'mwiza', 'mwiza@gmail.com', 0712345677);
---Orders table
INSERT INTO Orders(
	order_id, customer_id, order_date, total_amount)
	VALUES 
	(1, 2, '2026-02-15', 900),
	(2, 3, '2026-03-16', 200),
	(3, 1, '2026-04-14', 800),
	(4, 5, '2026-05-13', 700),
	(5, 4, '2026-07-12', 100);
