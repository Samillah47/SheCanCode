# PostgreSQL Practice - Bookstore Inventory Management System

This project contains a set of SQL scripts for managing a simple bookstore database with three main tables: Books, Customers, and Orders.

## Database Models

### 1. Books Table
Stores information about books available in the bookstore.

| Column | Type | Description |
|--------|------|-------------|
| book_id | int | Primary key - unique identifier for each book |
| title | varchar(50) | Title of the book |
| author | varchar(50) | Author of the book |
| genre | varchar(50) | Genre/category of the book |
| price | double precision | Price of the book |
| stock_quantity | int | Number of copies in stock |

### 2. Customers Table
Stores customer information for the bookstore.

| Column | Type | Description |
|--------|------|-------------|
| customer_id | int | Primary key - unique identifier for each customer |
| first_name | varchar(50) | Customer's first name |
| last_name | varchar(50) | Customer's last name |
| email | varchar(50) | Customer's email address |
| phone | varchar(20) | Customer's phone number |

### 3. Orders Table
Stores order information placed by customers.

| Column | Type | Description |
|--------|------|-------------|
| order_id | int | Primary key - unique identifier for each order |
| customer_id | int | wrote the customer_id  |
| order_date | date | Date when the order was placed |
| total_amount | double precision | Total amount of the order |

---

## Purpose of Each SQL File

### 1. create_tables.sql
Creates the database schema by defining the three tables: Books, Customers, and Orders. This file establishes the structure and relationships of the database.

### 2. insert_data.sql
Populates the three tables with initial sample data:
- 5 books with various genres (drama, education)
- 5 customers with their contact information
- 5 orders with different dates and amounts

### 3. manipulate_data.sql
Demonstrates common SQL operations:
- **UPDATE**: Modifies the price and stock quantity of the book titled "OOP"
- **DELETE**: Removes a customer with email 'anna@gmail.com'
- **DELETE**: Removes an order with order_id = 3
- **SELECT queries**: Retrieves books by genre, orders by amount, and customers by last name

---

## Assumptions and Default Values

1. **No DEFAULT values**: All columns are defined as NOT NULL without default values, meaning every column must have an explicit value when inserting data.

2. **No foreign key constraints**:The Orders table has no foreign key relationship with the Customers table via customer_id.

4. **Date format**: Dates are stored in ISO 8601 format (YYYY-MM-DD) as string literals in the insert_data.sql file.

5. **Primary key values**: Primary keys (book_id, customer_id, order_id) are manually assigned rather than using auto-increment/serial functionality.

6. **Data types**:
   - `double precision` is used for monetary values (price, total_amount)
   - `varchar` with fixed length (50 or 20) is used for string fields
