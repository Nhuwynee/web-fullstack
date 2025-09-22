INSERT INTO
	CUSTOMER (
		CUSTOMER_NAME,
		PHONE,
		EMAIL,
		GENDER,
		BIRTH_DATE,
		VIP_TIER,
		SIGNUP_SOURCE
	)
VALUES
	(
		'Nguyen Van A',
		'0123856789',
		'aaa@example.com',
		'F',
		'1990-05-10',
		'GOLD',
		'web'
	);

SELECT
	C.CUSTOMER_ID,
	C.CUSTOMER_NAME,
	O.ID ORDER_ID
FROM
	CUSTOMER C
	INNER JOIN ORDERS O ON C.CUSTOMER_ID = O.CUSTOMER_ID
ORDER BY
	O.ID DESC;

SELECT c.customer_id, c.customer_name, o.order_number
FROM (SELECT * FROM customer LIMIT 3) c
CROSS JOIN (SELECT * FROM orders LIMIT 5) o;


-- 2.5 Bài tập cơ bản		
-- A. INNER JOIN	
-- 1. Top 10 đơn hàng lớn nhất kèm tên KH	
-- - Mục tiêu: Lấy order_number, order_date, customer_name, total_amount	
-- - Gợi ý: Sử dụng ORDER BY o.total_amount DESC với LIMIT 10	
select o.order_number, o.order_date, c.customer_name, o.total_amount
from orders o
join customer c using (customer_id)
ORDER BY o.total_amount DESC
LIMIT 10

-- 2. Doanh thu theo kênh đăng ký của KH	
-- - Mục tiêu: Tổng total_amount nhóm theo c.signup_source	
-- - Gợi ý: SUM(o.total_amount) được GROUP BY c.signup_source	
select c.signup_source, SUM(o.total_amount) as revenue
from orders o
join customer c
using (customer_id)
group by c.signup_source
order by revenue desc
-- B. LEFT JOIN	
-- 3. KH không có đơn trong 30 ngày gần đây	
-- - Mục tiêu: Liệt kê KH và last_30d_orders = 0	
-- - Gợi ý: Sử dụng AND o.order_date>=current_date-7 trong điều kiện nối, sau đó kiểm tra để lọc o.id IS NULL	
select c.customer_id, c.customer_name
from customer c
left join orders o
on c.customer_id = o.customer_id and o.order_date >= current_date - 30
WHERE o.id is null
order by c.customer_id

-- C. RIGHT JOIN	
-- 4. So sánh điều kiện ON vs WHERE với Gmail	
-- - Mục tiêu: Chạy hai truy vấn đếm số KH có đuôi 'gmail' với:	
-- a) Điều kiện gmail trong ON	
select count(*) as count_customer
from orders o
right join customer c
on c.customer_id = o.customer_id
and email like '%gmail.com'

-- b) Điều kiện gmail trong WHERE	
select count(*) as count_customer
from orders o
right join customer c
using (customer_id)
where email like '%gmail.com'

-- - Gợi ý: ON ảnh hưởng khớp nối; WHERE lọc kết quả cuối	

-- A. INNER JOIN
-- 1. AOV của khách dùng Gmail
-- - Mục tiêu: Tính AVG(o.total_amount) cho khách c.email LIKE '%@gmail.com'
select c.customer_id, c.customer_name, avg(o.total_amount)
from customer c
join orders o
using (customer_id)
where c.email like '%@gmail.com'
group by c.customer_id, c.customer_name

-- 2. Số đơn 30 ngày gần đây theo trạng thái
-- - Mục tiêu: Đếm đơn theo o.status trong current_date - 30
select status, count(*) as count_order
from orders
where order_date >= current_date - 30
group by status

-- B. LEFT JOIN
-- 3. Ngày đặt hàng gần nhất của mỗi KH
-- - Mục tiêu: customer_id, customer_name, last_order_date
select c.customer_id, c.customer_name, max(o.order_date) as last_order_date
from customer c
left join orders o
using(customer_id)
group by c.customer_id

-- 4. KH chưa từng có đơn DELIVERED
-- - Mục tiêu: Tìm KH không có bất kỳ đơn status='DELIVERED'
SELECT
	C.CUSTOMER_ID,
	C.CUSTOMER_NAME
FROM CUSTOMER C
LEFT JOIN ORDERS O
ON O.CUSTOMER_ID = C.CUSTOMER_ID AND O.STATUS = 'DELIVERED'
WHERE O.STATUS IS NULL
GROUP BY C.CUSTOMER_ID;

-- 5. Số đơn của KH VIP GOLD theo kênh
-- - Mục tiêu: Đếm đơn theo c.vip_tier='GOLD' và o.channel
SELECT COUNT(*), O.CHANNEL
FROM ORDERS AS O 
LEFT JOIN CUSTOMER AS C
ON O.CUSTOMER_ID = C.CUSTOMER_ID 
WHERE C.VIP_TIER='GOLD'
GROUP BY O.CHANNEL

-- C. RIGHT JOIN
-- 6. Đếm số đơn theo vip_tier (giữ cả tier không có đơn)
-- - Mục tiêu: Số đơn mỗi vip_tier, vẫn hiển thị vip_tier không có đơn
SELECT C.VIP_TIER, COUNT(*) AS COUNT_ORDERS
FROM ORDERS O
RIGHT JOIN CUSTOMER C
USING (CUSTOMER_ID)
GROUP BY C.VIP_TIER

-- 7. Khách hàng không hoạt động nhưng vẫn có đơn
-- - Mục tiêu: Tìm KH c.is_active=false nhưng có ít nhất một đơn
SELECT DISTINCT
       c.customer_id,
       c.customer_name
FROM orders o
RIGHT JOIN customer c
USING (customer_id)
WHERE c.is_active = false
  AND o.id IS NOT NULL


-------------------------------------
-------------------------------------
	-- ---------- Setup ----------			
DROP SCHEMA IF EXISTS lab1 CASCADE;			
CREATE SCHEMA lab1;			
			
-- Product & Category (N:N)			
CREATE TABLE lab1.products (			
  product_id SERIAL PRIMARY KEY,			
  product_code TEXT UNIQUE NOT NULL,			
  product_name TEXT NOT NULL,			
  list_price NUMERIC(12,2) NOT NULL			
);			
			
CREATE TABLE lab1.categories (			
  category_id SERIAL PRIMARY KEY,			
  category_name TEXT NOT NULL			
);			
			
CREATE TABLE lab1.product_categories (			
  product_id INT REFERENCES lab1.products(product_id),			
  category_id INT REFERENCES lab1.categories(category_id),			
  PRIMARY KEY (product_id, category_id)			
);			
			
-- Customer / Orders / Order Items			
CREATE TABLE lab1.customers (			
  customer_id SERIAL PRIMARY KEY,			
  customer_name TEXT NOT NULL,			
  email TEXT NOT NULL			
);			
			
CREATE TABLE lab1.orders (			
  order_id SERIAL PRIMARY KEY,			
  customer_id INT NOT NULL REFERENCES lab1.customers(customer_id),			
  order_date DATE NOT NULL,			
  status TEXT NOT NULL CHECK (status IN ('PENDING','PAID','SHIPPED','DELIVERED','CANCELLED'))			
);			
			
CREATE TABLE lab1.order_items (			
  order_item_id SERIAL PRIMARY KEY,			
  order_id INT NOT NULL REFERENCES lab1.orders(order_id),			
  product_id INT NOT NULL REFERENCES lab1.products(product_id),			
  quantity INT NOT NULL CHECK (quantity>0),			
  unit_price NUMERIC(12,2) NOT NULL			
);			
			
-- Payment / Shipper			
CREATE TABLE lab1.payments (			
  payment_id SERIAL PRIMARY KEY,			
  order_id INT NOT NULL REFERENCES lab1.orders(order_id),			
  paid_at TIMESTAMPTZ,			
  amount NUMERIC(12,2) NOT NULL,			
  method TEXT NOT NULL CHECK (method IN ('CARD','EWALLET','BANK')),			
  status TEXT NOT NULL CHECK (status IN ('SUCCESS','FAILED','PENDING'))			
);			
			
CREATE TABLE lab1.shippers (			
  shipper_id SERIAL PRIMARY KEY,			
  shipper_name TEXT NOT NULL			
);			
			
CREATE TABLE lab1.shipments (			
  shipment_id SERIAL PRIMARY KEY,			
  order_id INT NOT NULL REFERENCES lab1.orders(order_id),			
  shipper_id INT NOT NULL REFERENCES lab1.shippers(shipper_id),			
  shipped_at TIMESTAMPTZ			
);			
			
-- ---------- Mock data ----------			
INSERT INTO lab1.products (product_code, product_name, list_price) VALUES			
('TSHIRT','Áo thun',199000), ('HOOD','Áo hoodie',399000), ('CAP','Mũ lưỡi trai',99000),			
('BAG','Túi tote',149000), ('JACKET','Áo khoác',699000);			
			
INSERT INTO lab1.categories (category_name) VALUES			
('Thời trang'),('Phụ kiện'),('Đồ thu đông');			
			
INSERT INTO lab1.product_categories VALUES			
(1,1),(2,1),(3,2),(4,2),(5,1),(5,3),(2,3); -- JACKET thuộc 1&3, HOOD thuộc 1&3			
			
INSERT INTO lab1.customers (customer_name,email) VALUES			
('An','an@gmail.com'),('Bình','binh@yahoo.com'),('Chi','chi@outlook.com');			
			
INSERT INTO lab1.orders (customer_id, order_date, status) VALUES			
(1, CURRENT_DATE-10, 'PAID'),			
(1, CURRENT_DATE-2 , 'PENDING'),			
(2, CURRENT_DATE-5 , 'DELIVERED'),			
(3, CURRENT_DATE-1 , 'PAID');			
			
INSERT INTO lab1.order_items (order_id, product_id, quantity, unit_price) VALUES			
(1,1,2,199000),(1,3,1,99000),		-- order #1: TSHIRT x2, CAP x1			
(2,2,1,399000),						-- order #2: HOOD x1			
(3,5,1,699000),(3,4,2,149000),		-- order #3: JACKET x1, BAG x2			
(4,2,1,399000);						-- order #4: HOOD x1			
			
INSERT INTO lab1.payments (order_id, paid_at, amount, method, status) VALUES			
(1, NOW()-INTERVAL '9 days', 497000, 'CARD','SUCCESS'),			
(3, NOW()-INTERVAL '4 days', 997000, 'BANK','SUCCESS'),			
(4, NOW()-INTERVAL '12 hours', 399000, 'EWALLET','PENDING'); -- chưa thành công			
			
INSERT INTO lab1.shippers (shipper_name) VALUES ('Ahamove'),('GHN');			
			
INSERT INTO lab1.shipments (order_id, shipper_id, shipped_at) VALUES			
(3,2, NOW()-INTERVAL '4 days'); -- chỉ order #3 đã giao vận			

set search_path to lab1, public
-- A. Kết hợp nhiều JOIN (Bài E-commerce)	
-- Câu 1: Doanh thu theo danh mục (chỉ tính đơn đã thanh toán thành công)	
-- - Mục tiêu: Lấy category_name, revenue = SUM(quantity * unit_price) qua các bảng:	
-- orders → order_items → products → product_categories → categories và JOIN thêm payments để lọc	
-- status='SUCCESS'	
-- - Yêu cầu: Sử dụng CTE và JOIN nhiều bảng	
WITH delivered_orders AS (
    SELECT o.order_id
    FROM orders o
    JOIN payments pm 
      ON pm.order_id = o.order_id
    WHERE pm.status = 'SUCCESS'
)
SELECT c.category_id, c.category_name, SUM(oi.quantity * oi.unit_price) AS revenue
FROM delivered_orders d
JOIN order_items oi 
    ON d.order_id = oi.order_id
JOIN products p 
    ON oi.product_id = p.product_id
JOIN product_categories pc 
    ON pc.product_id = p.product_id
JOIN categories c 
    ON c.category_id = pc.category_id
GROUP BY c.category_id, c.category_name
ORDER BY revenue DESC

-- Câu 2: Doanh thu theo khách hàng và shipper	
-- - Mục tiêu:	
-- + Tính tổng doanh thu của từng khách hàng (customer) dựa trên tất cả đơn hàng đã đặt	
-- + Hiển thị thêm tên shipper nếu đơn hàng của khách đã được giao vận (bảng shipments)	
-- + Nếu khách hàng chưa có shipment thì vẫn hiển thị với tổng doanh thu = 0 và shipper để trống	
-- - Yêu cầu: Sử dụng CTE và JOIN nhiều bảng
WITH revenue_per_order AS (
    SELECT 
        o.order_id,
        o.customer_id,
        SUM(oi.quantity * oi.unit_price) AS order_revenue
    FROM lab1.orders o
    JOIN lab1.order_items oi USING (order_id)
    GROUP BY o.order_id, o.customer_id
),
shipper_per_customer AS (
    SELECT 
        o.customer_id,
        ARRAY_AGG(DISTINCT s.shipper_name ORDER BY s.shipper_name) AS shipper_list
    FROM lab1.orders o
    JOIN lab1.shipments sm USING (order_id)
    JOIN lab1.shippers s USING (shipper_id)
    GROUP BY o.customer_id
)
SELECT
    c.customer_id,
    c.customer_name,
    COALESCE(SUM(r.order_revenue), 0) AS revenue,
    COALESCE(ARRAY_TO_STRING(spc.shipper_list, ', '), '') AS shippers
FROM lab1.customers c
LEFT JOIN revenue_per_order r USING (customer_id)
LEFT JOIN shipper_per_customer spc USING (customer_id)
GROUP BY c.customer_id, c.customer_name, spc.shipper_list
ORDER BY revenue DESC


-- Câu 3: Mở rộng câu 2 để chỉ muốn tính doanh thu cho đơn đã thanh toán thành công
WITH paid_orders AS (
    SELECT o.order_id,
           o.customer_id
    FROM orders o
    JOIN payments pm
     USING (order_id)
    WHERE pm.status = 'SUCCESS'
),
revenue_per_order AS (
    SELECT po.order_id,
           po.customer_id,
           SUM(oi.quantity * oi.unit_price) AS order_revenue
    FROM paid_orders po
    JOIN order_items oi
      USING (order_id)
    GROUP BY po.order_id, po.customer_id
),
shipper_per_customer AS (
    SELECT o.customer_id,
           ARRAY_AGG(DISTINCT s.shipper_name ORDER BY s.shipper_name) AS shipper_list
    FROM orders o
    JOIN shipments sm
      USING (order_id)
    JOIN shippers s
      USING (shipper_id)
    GROUP BY o.customer_id
)
SELECT 
    c.customer_id,
    c.customer_name,
    COALESCE(SUM(r.order_revenue), 0) AS revenue,
    COALESCE(ARRAY_TO_STRING(spc.shipper_list, ', '), '') AS shippers
FROM lab1.customers c
LEFT JOIN revenue_per_order r
	USING (customer_id)
LEFT JOIN shipper_per_customer spc
	USING (customer_id)
GROUP BY c.customer_id, c.customer_name, spc.shipper_list
ORDER BY revenue DESC




