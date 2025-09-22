--- Bài làm
-- câu 1
SELECT *
FROM orders
WHERE status = 'DELIVERED'
ORDER BY order_date DESC, order_number ASC
LIMIT 50
OFFSET 50 * (4 - 1)

-- câu 2
SELECT signup_source,																														
	COUNT(*) AS customers,																														
	SUM(CASE WHEN marketing_opt_in THEN 1 ELSE 0 END) AS opt_in_count,																														
	ROUND(																														
	 100.0 * SUM(CASE WHEN marketing_opt_in THEN 1 ELSE 0 END) / COUNT(*),																														
	 2																														
	) AS opt_in_rate_percent,																														
	BOOL_OR(marketing_opt_in) AS any_opt_in,																														
	BOOL_AND(marketing_opt_in) AS all_opt_in																														
FROM customer																														
GROUP BY signup_source																														
ORDER BY customers DESC																													

-- câu 3
SELECT
	CITY,
	ARRAY_TO_STRING((ARRAY_AGG(CUSTOMER_NAME ORDER BY CREATED_AT DESC)) [1:5], ', ') AS FIVE_CUSTOMER_NAME,
	COUNT(*) AS CUSTOMERS
FROM CUSTOMER
WHERE IS_ACTIVE = TRUE AND VIP_TIER IN ('GOLD', 'PLATINUM')
GROUP BY CITY
ORDER BY CUSTOMERS DESC
LIMIT 10

-- câu 4
SELECT order_date, revenue
FROM (
    SELECT order_date, SUM(total_amount) AS revenue
    FROM orders
    GROUP BY order_date
) total
ORDER BY revenue DESC
LIMIT 1

-- câu 5
SELECT DISTINCT c.customer_id, c.customer_name
FROM customer c
JOIN orders o USING (customer_id)
WHERE o.status = 'CANCELLED'

-- câu 6
-- 1.
CREATE TABLE lab2.customers2 (
	customer_id	 SERIAL PRIMARY KEY,
	full_name	 VARCHAR(100) NOT NULL,
	email		 VARCHAR(150) UNIQUE,
	phone_number VARCHAR(15) CHECK (phone_number ~ '^[0-9]+$'),
	birth_date	 DATE,
	is_active	 BOOLEAN DEFAULT TRUE,
	created_at	 TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
-- 2.
CREATE TYPE ORDER_STATUS AS ENUM('pending','completed','canceled')
CREATE TABLE lab2.orders2 (
	order_id SERIAL PRIMARY KEY,
	customer_id INT NOT NULL,
	order_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
	total_amount NUMERIC(12, 2) CHECK (total_amount >= 0),
	status order_status default 'pending',
	CONSTRAINT fk_customers2 FOREIGN KEY (customer_id) REFERENCES lab2.customers2(customer_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- 3.
INSERT INTO lab2.customers2 (full_name, email, phone_number, birth_date)
VALUES 	('Lưu Ngọc Yến Như', 'ynhu@gmail.com', '0912345670', '2004-10-08'),
		('Lưu Yến Ngọc', 'ngoc@gmail.com', '0912346671', null),
		('Lưu Ngọc', 'luu@gmail.com', '0912346672', '2004-02-08'),
		('Như Như', null, '0912346673', '2004-05-08'),
		('Nguyễn Ngọc', 'nguyenngoc@gmail.com', '0912445674', '2004-01-05')

INSERT INTO lab2.orders2 (customer_id, order_date, total_amount, status)
VALUES
    (1, '2025-09-01 10:15:00', 120000.00, 'completed'),
    (2, '2025-09-02 14:30:00', 250000.00, 'pending'),
    (3, '2025-09-03 09:45:00', 180000.50, 'canceled'),
    (4, '2025-09-04 11:20:00', 99000.00, 'completed'),
    (5, '2025-09-05 16:10:00', 350000.00, 'completed'),
    (1, '2025-09-06 12:00:00', 450000.75, 'pending'),
    (2, '2025-09-07 08:30:00', 200000.00, 'completed'),
    (3, '2025-09-08 19:00:00', 175000.00, 'canceled'),
    (4, '2025-09-09 15:15:00', 600000.00, 'pending'),
    (5, '2025-09-10 10:45:00', 720000.00, 'completed');
-- 4.
UPDATE lab2.customers2 
SET is_active = FALSE 
WHERE birth_date IS NULL
RETURNING *;

-- 5.
DELETE FROM lab2.customers2 
WHERE email IS NULL AND is_active = FALSE
RETURNING *;

-- 6. Thêm một giá trị ENUM mới, ví dụ 'returned', và chèn ít nhất 1 đơn hàng ở trạng thái này
ALTER TYPE order_status ADD VALUE 'returned' AFTER 'pending';

INSERT INTO lab2.orders2 (customer_id, total_amount, status)
VALUES(1, 550000.00, 'returned')

-- 7. Xóa giá trị ENUM 'returned'
SELECT * FROM pg_enum;

-- Tạo enum mới ko chứa 'returned'
CREATE TYPE order_status_new AS ENUM ('pending','completed','canceled');

-- Update những row nào có status là 'returned' sang 'canceled'
UPDATE lab2.orders2
SET status = 'canceled'
WHERE status = 'returned'
RETURNING *;

-- Bỏ default cũ
ALTER TABLE lab2.orders2 ALTER COLUMN status DROP DEFAULT;

-- Đổi type sang ENUM mới
ALTER TABLE lab2.orders2 
  ALTER COLUMN status TYPE order_status_new 
  USING status::text::order_status_new;

-- Set default lại (giữ 'pending')
ALTER TABLE lab2.orders2 ALTER COLUMN status SET DEFAULT 'pending';

-- Xóa enum cũ
DROP TYPE order_status;

-- câu 7
SELECT o.status, COUNT(*) AS order_count
FROM orders o
JOIN customer c USING (customer_id)
WHERE o.order_date >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY o.status;

-- câu 8
SELECT c.customer_id, c.customer_name
FROM customer c
LEFT JOIN orders o 
  ON c.customer_id = o.customer_id 
  AND o.status = 'DELIVERED'
WHERE o.id IS NULL

-- câu 9
SELECT 
    o.order_id,
    c.customer_name,
    SUM(oi.quantity * oi.unit_price) AS total_amount
FROM lab1.orders o
JOIN lab1.customers c USING (customer_id)
JOIN lab1.order_items oi USING(order_id)
GROUP BY o.order_id, c.customer_name
ORDER BY o.order_id;


-- câu 10
SELECT 
    c.customer_id,
    c.customer_name,
    COALESCE(SUM(oi.quantity * oi.unit_price),0) AS revenue
FROM lab1.customers c
LEFT JOIN lab1.orders o USING (customer_id)
LEFT JOIN lab1.order_items oi USING(order_id)
GROUP BY c.customer_id, c.customer_name
ORDER BY revenue DESC

-- câu 11
SELECT 
    c.customer_id,
    c.customer_name,
    COUNT(o.order_id) AS orders_count
FROM lab1.customers c
LEFT JOIN lab1.orders o USING(customer_id)
GROUP BY c.customer_id, c.customer_name
ORDER BY orders_count DESC, c.customer_name

-- câu 12
WITH order_totals AS (
    SELECT 
        o.order_id,
        o.customer_id,
        SUM(oi.quantity * oi.unit_price) AS total_amount
    FROM lab1.orders o
    JOIN lab1.order_items oi USING(order_id)
    GROUP BY o.order_id, o.customer_id
)
SELECT 
    ot.order_id,
    c.customer_name,
    s.shipper_name,
    ot.total_amount
FROM order_totals ot
JOIN lab1.customers c USING(customer_id)
LEFT JOIN lab1.shipments sh USING(order_id)
LEFT JOIN lab1.shippers s USING(shipper_id)
WHERE ot.total_amount > 400000
ORDER BY ot.total_amount DESC

-- Câu 13
WITH revenue AS (
    SELECT
        o.customer_id,
        sp.shipper_name,
        SUM(oi.quantity * oi.unit_price) AS total_revenue
    FROM lab1.orders o
    JOIN lab1.order_items oi USING (order_id)
    JOIN lab1.shipments s USING (order_id)
    JOIN lab1.shippers sp USING (shipper_id)
    GROUP BY o.order_id, sp.shipper_name
)
SELECT
    c.customer_id,
    c.customer_name,
    COALESCE(r.total_revenue, 0) AS total_revenue,
    COALESCE(r.shipper_name, '') AS shipper_name
FROM customers c
LEFT JOIN revenue r USING (customer_id)

-- câu 14
WITH order_revenue AS (
    SELECT o.order_id,
           oi.product_id,
           SUM(oi.quantity * oi.unit_price) AS revenue
    FROM lab1.orders o
    JOIN lab1.order_items oi 
         ON o.order_id = oi.order_id
    JOIN lab1.payments p 
         ON o.order_id = p.order_id
    WHERE p.status = 'SUCCESS'
    GROUP BY o.order_id, oi.product_id
)
SELECT c.category_name,
       SUM(orv.revenue) AS revenue
FROM order_revenue orv
JOIN lab1.product_categories pc 
     ON orv.product_id = pc.product_id
JOIN lab1.categories c 
     ON pc.category_id = c.category_id
GROUP BY c.category_name
ORDER BY revenue DESC

-- câu 15
WITH customer_categories AS (
    SELECT 
        o.customer_id,
        cu.customer_name,
        COUNT(DISTINCT pc.category_id) AS distinct_categories,
        STRING_AGG(DISTINCT cat.category_name, ', ') AS categories_list
    FROM lab1.orders o
    JOIN lab1.customers cu 
         ON o.customer_id = cu.customer_id
    JOIN lab1.order_items oi 
         ON o.order_id = oi.order_id
    JOIN lab1.product_categories pc 
         ON oi.product_id = pc.product_id
    JOIN lab1.categories cat 
         ON pc.category_id = cat.category_id
    GROUP BY o.customer_id, cu.customer_name
)
SELECT *
FROM customer_categories
WHERE distinct_categories >= 2;

-- câu 16 tối ưu
-- Cần tránh biểu thức trên cột vì nó phá vỡ index trên order_date																
-- => nên truy vấn theo logic sử dụng range (khoảng)																
CREATE INDEX idx_orders_order_date_incl ON orders (order_date) INCLUDE (order_number);

EXPLAIN ANALYZE
SELECT order_number
FROM orders
WHERE order_date >= '2025-08-01' 
	AND order_date <  '2025-08-02';

-- câu 17
-- Cần dùng index (composite & covering) hỗ trợ ORDER BY để tránh sort																							
CREATE INDEX IF NOT EXISTS idx_orders_status_orderdate_ordernum																						
ON orders (status, order_date DESC, order_number ASC)																						
INCLUDE (customer_id, total_amount);																						

DROP INDEX IF EXISTS idx_orders_status_orderdate_ordernum

EXPLAIN ANALYZE
SELECT order_number, customer_id, order_date, total_amount																					
FROM orders																					
WHERE status = 'DELIVERED'																					
ORDER BY order_date DESC, order_number ASC																					
LIMIT 50 OFFSET 0;				

-- câu 18
EXPLAIN ANALYZE
SELECT o.order_number,
       o.order_date,
       c.customer_name
FROM orders o
JOIN customer c 
     ON o.customer_id = c.customer_id
WHERE o.order_date = '2025-01-01'

DROP INDEX IF EXISTS idx_orders_order_date

CREATE INDEX idx_orders_order_date
   ON orders(order_date);

-- câu 19
-- đề
SELECT c.customer_id, c.customer_name
FROM customer c
WHERE (SELECT SUM(o.total_amount)
	FROM orders o
	WHERE o.customer_id = c.customer_id) > 30000

-- tối ưu bằng cách dùng join
EXPLAIN ANALYZE
SELECT 
    c.customer_id,
    c.customer_name
FROM public.customer c
LEFT JOIN public.orders o 
       ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING COALESCE(SUM(o.total_amount),0) > 30000;

-- câu 20
-- đề
EXPLAIN ANALYZE
SELECT
  c.customer_id,
  c.customer_name,
  (
    SELECT MAX(o.order_date)
    FROM orders o
    WHERE o.customer_id = c.customer_id
  ) AS last_order_date
FROM customers c
WHERE c.email LIKE '%@gmail.com';

-- tối ưu bằng cách dùng join và cte
EXPLAIN ANALYZE
WITH last_orders AS (
    SELECT 
        o.customer_id,
        MAX(o.order_date) AS last_order_date
    FROM orders o
    GROUP BY o.customer_id
)
SELECT 
    c.customer_id,
    c.customer_name,
    lo.last_order_date
FROM customers c
LEFT JOIN last_orders lo 
       ON c.customer_id = lo.customer_id
WHERE c.email LIKE '%@gmail.com';

