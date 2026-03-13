---Books table
create table Books (    
    book_id int primary key,
    title varchar(50),
    author varchar(50),
    genre varchar(50),
    price double precision,
    stock_quantity int
);
---Customers table
create table Customers (
    customer_id int primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    phone varchar(20)
);
---Orders table
create table Orders (
	order_id int primary key,
	customer_id int,
	order_date date,
	total_amount double precision
);

