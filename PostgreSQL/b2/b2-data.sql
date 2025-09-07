

CREATE TABLE FILM (
	FILM_ID SERIAL PRIMARY KEY,
	TITLE TEXT NOT NULL,
	RENTAL_RATE NUMERIC(4, 2),
	LENGTH INT
) TABLESPACE nhuwspace;
INSERT INTO FILM (TITLE, RENTAL_RATE, LENGTH)
VALUES ('Film 1', 2.99, 95),
	('Film 2', 3.99, 110),
	('Film 3', 1.99, 85),
	('Film 4', 4.99, 125),
	('Film 5', 2.49, 105),
	('Film 6', 3.49, 100),
	('Film 7', 4.49, 115),
	('Film 8', 5.99, 130),
	('Film 9', 2.79, 90),
	('Film 10', 3.29, 98),
	('Film 11', 1.49, 80),
	('Film 12', 2.59, 120),
	('Film 13', 3.89, 108),
	('Film 14', 4.19, 112),
	('Film 15', 5.29, 140),
	('Film 16', 1.99, 75),
	('Film 17', 2.19, 88),
	('Film 18', 3.19, 118),
	('Film 19', 4.29, 132),
	('Film 20', 5.49, 150);
CREATE TABLE RENTAL (
	RENTAL_ID SERIAL PRIMARY KEY,
	RENTAL_DATE TIMESTAMP NOT NULL,
	FILM_ID INT REFERENCES FILM (FILM_ID)
) TABLESPACE nhuwspace;
INSERT INTO RENTAL (RENTAL_DATE, FILM_ID)
VALUES ('2023-01-01 10:00', 1),
	('2023-01-02 11:00', 2),
	('2023-01-03 12:00', 3),
	('2023-01-04 13:00', 4),
	('2023-01-05 14:00', 5),
	('2023-01-06 15:00', 6),
	('2023-01-07 16:00', 7),
	('2023-01-08 17:00', 8),
	('2023-01-09 18:00', 9),
	('2023-01-10 19:00', 10),
	('2023-01-11 20:00', 11),
	('2023-01-12 21:00', 12),
	('2023-01-13 22:00', 13),
	('2023-01-14 23:00', 14),
	('2023-01-15 09:00', 15),
	('2023-01-16 08:00', 16),
	('2023-01-17 07:00', 17),
	('2023-01-18 06:00', 18),
	('2023-01-19 05:00', 19),
	('2023-01-20 04:00', 20);
-- 2. Bảng customer
CREATE UNLOGGED TABLE customer (
    customer_id      SERIAL PRIMARY KEY,
    customer_name    TEXT        NOT NULL,
    phone            VARCHAR(20) NOT NULL,
    email            TEXT        NOT NULL,
    gender           CHAR(1)     NOT NULL CHECK (gender IN ('M','F','O')),
    birth_date       DATE,
    address_line1    TEXT,
    city             TEXT,
    country          TEXT,
    vip_tier         TEXT        NOT NULL CHECK (vip_tier IN ('NONE','SILVER','GOLD','PLATINUM')),
    marketing_opt_in BOOLEAN     NOT NULL DEFAULT FALSE,
    signup_source    TEXT        NOT NULL CHECK (signup_source IN ('web','mobile','store','partner')),
    loyalty_points   INT         NOT NULL DEFAULT 0,
    is_active        BOOLEAN     NOT NULL DEFAULT TRUE,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_customer_email UNIQUE(email),
    CONSTRAINT uq_customer_phone UNIQUE(phone)
) TABLESPACE nhuwspace;
-- 2.1. Bảng orders
CREATE UNLOGGED TABLE orders (
    id               SERIAL PRIMARY KEY,
    order_number     TEXT        NOT NULL,
    customer_id      INT         NOT NULL,
    order_date       DATE        NOT NULL,
    status           TEXT        NOT NULL CHECK (status IN ('PENDING','PAID','SHIPPED','DELIVERED','CANCELLED','RETURNED','REFUNDED')),
    payment_method   TEXT        NOT NULL CHECK (payment_method IN ('CARD','CASH','EWALLET','BANK_TRANSFER')),
    shipping_method  TEXT        NOT NULL CHECK (shipping_method IN ('STANDARD','EXPRESS','PICKUP')),
    channel          TEXT        NOT NULL CHECK (channel IN ('web','mobile','store','partner')),
    currency         CHAR(3)     NOT NULL DEFAULT 'USD',
    coupon_code      TEXT,
    subtotal_amount  NUMERIC(10,2) NOT NULL,
    discount_amount  NUMERIC(10,2) NOT NULL DEFAULT 0,
    tax_amount       NUMERIC(10,2) NOT NULL DEFAULT 0,
    shipping_fee     NUMERIC(10,2) NOT NULL DEFAULT 0,
    total_amount     NUMERIC(10,2) NOT NULL CHECK (total_amount >= 0),
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    CONSTRAINT uq_orders_order_number UNIQUE(order_number)
) TABLESPACE nhuwspace;
-- 3. Bảng product (ví dụ subquery)
CREATE TABLE PRODUCT (
	PRODUCT_ID SERIAL PRIMARY KEY,
	NAME TEXT NOT NULL,
	PRICE NUMERIC(10, 2)
) TABLESPACE nhuwspace;
INSERT INTO PRODUCT (NAME, PRICE)
VALUES ('Product 1', 5.00),
	('Product 2', 10.00),
	('Product 3', 15.00),
	('Product 4', 20.00),
	('Product 5', 25.00),
	('Product 6', 30.00),
	('Product 7', 35.00),
	('Product 8', 40.00),
	('Product 9', 45.00),
	('Product 10', 50.00),
	('Product 11', 55.00),
	('Product 12', 60.00),
	('Product 13', 65.00),
	('Product 14', 70.00),
	('Product 15', 75.00),
	('Product 16', 80.00),
	('Product 17', 85.00),
	('Product 18', 90.00),
	('Product 19', 95.00),
	('Product 20', 100.00);
-- 4. Bảng book & borrow (ví dụ library)
CREATE TABLE BOOK (
	BOOK_ID SERIAL PRIMARY KEY,
	TITLE TEXT NOT NULL,
	AUTHOR TEXT NOT NULL,
	PUBLISHED DATE
) TABLESPACE nhuwspace;
INSERT INTO BOOK (TITLE, AUTHOR, PUBLISHED)
VALUES ('Book 1', 'Author A', '2020-01-01'),
	('Book 2', 'Author B', '2020-02-01'),
	('Book 3', 'Author C', '2020-03-01'),
	('Book 4', 'Author D', '2020-04-01'),
	('Book 5', 'Author E', '2020-05-01'),
	('Book 6', 'Author F', '2020-06-01'),
	('Book 7', 'Author G', '2020-07-01'),
	('Book 8', 'Author H', '2020-08-01'),
	('Book 9', 'Author I', '2020-09-01'),
	('Book 10', 'Author J', '2020-10-01'),
	('Book 11', 'Author K', '2020-11-01'),
	('Book 12', 'Author L', '2020-12-01'),
	('Book 13', 'Author M', '2021-01-01'),
	('Book 14', 'Author N', '2021-02-01'),
	('Book 15', 'Author O', '2021-03-01'),
	('Book 16', 'Author P', '2021-04-01'),
	('Book 17', 'Author Q', '2021-05-01'),
	('Book 18', 'Author R', '2021-06-01'),
	('Book 19', 'Author S', '2021-07-01'),
	('Book 20', 'Author T', '2021-08-01');
CREATE TABLE BORROW (
	BORROW_ID SERIAL PRIMARY KEY,
	BOOK_ID INT REFERENCES BOOK (BOOK_ID),
	USER_ID INT,
	BORROWED_AT DATE,
	RETURNED_AT DATE
) TABLESPACE nhuwspace;
INSERT INTO BORROW (BOOK_ID, USER_ID, BORROWED_AT, RETURNED_AT)
VALUES (1, 1, '2022-01-01', '2022-01-08'),
	(2, 2, '2022-01-02', '2022-01-09'),
	(3, 3, '2022-01-03', '2022-01-10'),
	(4, 4, '2022-01-04', '2022-01-11'),
	(5, 5, '2022-01-05', '2022-01-12'),
	(6, 6, '2022-01-06', '2022-01-13'),
	(7, 7, '2022-01-07', '2022-01-14'),
	(8, 8, '2022-01-08', '2022-01-15'),
	(9, 9, '2022-01-09', '2022-01-16'),
	(10, 10, '2022-01-10', '2022-01-17'),
	(11, 1, '2022-01-11', '2022-01-18'),
	(12, 2, '2022-01-12', '2022-01-19'),
	(13, 3, '2022-01-13', '2022-01-20'),
	(14, 4, '2022-01-14', '2022-01-21'),
	(15, 5, '2022-01-15', '2022-01-22'),
	(16, 6, '2022-01-16', '2022-01-23'),
	(17, 7, '2022-01-17', '2022-01-24'),
	(18, 8, '2022-01-18', '2022-01-25'),
	(19, 9, '2022-01-19', '2022-01-26'),
	(20, 10, '2022-01-20', '2022-01-27');
-- 5. Bảng department, employee, project (ví dụ RDB)
CREATE TABLE DEPARTMENT (DEPT_ID SERIAL PRIMARY KEY, NAME TEXT NOT NULL) TABLESPACE nhuwspace;
INSERT INTO DEPARTMENT (NAME)
VALUES ('Dept 1'),
	('Dept 2'),
	('Dept 3'),
	('Dept 4'),
	('Dept 5'),
	('Dept 6'),
	('Dept 7'),
	('Dept 8'),
	('Dept 9'),
	('Dept 10'),
	('Dept 11'),
	('Dept 12'),
	('Dept 13'),
	('Dept 14'),
	('Dept 15'),
	('Dept 16'),
	('Dept 17'),
	('Dept 18'),
	('Dept 19'),
	('Dept 20');
CREATE TABLE EMPLOYEE (
	EMP_ID SERIAL PRIMARY KEY,
	NAME TEXT NOT NULL,
	DEPT_ID INT REFERENCES DEPARTMENT (DEPT_ID)
) TABLESPACE nhuwspace;
INSERT INTO EMPLOYEE (NAME, DEPT_ID)
VALUES ('Employee 1', 1),
	('Employee 2', 2),
	('Employee 3', 3),
	('Employee 4', 4),
	('Employee 5', 5),
	('Employee 6', 6),
	('Employee 7', 7),
	('Employee 8', 8),
	('Employee 9', 9),
	('Employee 10', 10),
	('Employee 11', 11),
	('Employee 12', 12),
	('Employee 13', 13),
	('Employee 14', 14),
	('Employee 15', 15),
	('Employee 16', 16),
	('Employee 17', 17),
	('Employee 18', 18),
	('Employee 19', 19),
	('Employee 20', 20);
CREATE TABLE PROJECT (
	PROJ_ID SERIAL PRIMARY KEY,
	NAME TEXT NOT NULL,
	MANAGER INT REFERENCES EMPLOYEE (EMP_ID)
) TABLESPACE nhuwspace;
INSERT INTO PROJECT (NAME, MANAGER)
VALUES ('Project 1', 1),
	('Project 2', 2),
	('Project 3', 3),
	('Project 4', 4),
	('Project 5', 5),
	('Project 6', 6),
	('Project 7', 7),
	('Project 8', 8),
	('Project 9', 9),
	('Project 10', 10),
	('Project 11', 11),
	('Project 12', 12),
	('Project 13', 13),
	('Project 14', 14),
	('Project 15', 15),
	('Project 16', 16),
	('Project 17', 17),
	('Project 18', 18),
	('Project 19', 19),
	('Project 20', 20);