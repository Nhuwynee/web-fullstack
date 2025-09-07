CREATE TABLE customers2 (
	customer_id SERIAL PRIMARY KEY,
	full_name VARCHAR(100) NOT NULL,
	email VARCHAR(150) UNIQUE,
	phone_number VARCHAR(15) CHECK (phone_number ~ '^[0-9]+$'),
	birth_date DATE,
	is_active BOOLEAN DEFAULT TRUE,
	created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);


-- Tạo kiểu dữ liệu ENUM 
CREATE TYPE order_status AS ENUM ('pending', 'completed', 'canceled');

SELECT * FROM pg_enum;

CREATE TABLE orders2 (
	order_id SERIAL PRIMARY KEY,
	customer_id INT NOT NULL,
	order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	total_amount NUMERIC(12, 2) CHECK (total_amount >= 0),
	status order_status DEFAULT 'pending',
	CONSTRAINT fk_customer2
	FOREIGN KEY (customer_id) 
	REFERENCES customers2 (customer_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

BEGIN;
-- Thêm 3 khách hàng mới
INSERT INTO customers2 (full_name, email, phone_number, birth_date)
VALUES
	('Nguyen Van A', 'a@example.com', '0123456789', '1990-05-10'),
	('Tran Thi B', 'b@example.com', '0987654321', '1985-09-20'),
	('Le Van C', 'c@example.com', '0911222333', '1995-12-01');

-- Thêm đơn hàng cho khách hàng ID = 1, 2, 3
INSERT INTO orders2 (customer_id, total_amount, status)
VALUES
	(1, 200000, 'pending'),
	(2, 500000, 'completed'),
	(3, 1000000, 'canceled');

ALTER TYPE order_status ADD VALUE 'shipping' AFTER 'pending';

INSERT INTO orders2 (customer_id, total_amount, status)
VALUES (1, 250000, 'shipping');

UPDATE customers2
SET email = 'nguyenvana_new@example.com'
WHERE customer_id = 1;

UPDATE customers2
SET full_name = 'Tran Thi B Updated',
	phone_number = '0999888777'
WHERE customer_id = 2;

UPDATE customers2
SET is_active = FALSE
WHERE customer_id IN (2, 3);

UPDATE customers2
SET full_name = CONCAT('[VIP] ', full_name)
WHERE email LIKE '%@example.com';

UPDATE customers2
SET full_name = REPLACE(full_name, '[VIP] ', '')
WHERE full_name LIKE '[VIP]%';

TRUNCATE TABLE customers2 CASCADE;

SELECT * FROM pg_sequences; -- Kiểm tra các biến sequence

INSERT INTO customer (customer_name, phone, EMAIL, gender, BIRTH_DATE, vip_tier, signup_source)
VALUES
    (
        'Nguyen Van A',
        '0123456789',
        'a@example.com',
        'F',
        '1990-05-10',
        'GOLD',
        'web'
    );

SELECT setval('customers2_customer_id_seq', 1, false);
SELECT setval('customer_customer_id_seq', 100001, false);
SELECT setval('orders2_order_id_seq', 1, false);

ROLLBACK;
---

BEGIN;

UPDATE customers2 SET is_active = FALSE WHERE customer_id = 3;

SAVEPOINT before_delete;

DELETE FROM customers2 WHERE customer_id = 3;

-- Phát hiện xóa nhầm → quay về trước khi xóa
ROLLBACK TO before_delete;

SELECT * FROM customers2 WHERE customer_id = 3;

COMMIT; -- Lưu các thay đổi trước Savepoint

CREATE SCHEMA sales;

CREATE TABLE sales.orders (
	order_id SERIAL PRIMARY KEY,
	amount NUMERIC(10,2)
);

SET search_path TO sales, public; -- chỉ trong 1 phiên làm việc (1 query tool)

SHOW search_path; -- Kiểm tra search_path hiện tại

-- 2.5.1
