
SELECT COUNT(*)
FROM FILM;

SELECT
	EXTRACT(
		MONTH
		FROM
			CURRENT_DATE
	);

SELECT
	CASE
		WHEN LENGTH > 120 THEN 'Long'
		ELSE 'Short'
	END AS LENGTH_OF_FILM
FROM
	FILM;

SELECT
	TITLE,
	COALESCE(LENGTH, 0) AS FILM_LENGTH
FROM
	FILM;

SELECT title, rental_rate														
FROM film														
ORDER BY rental_rate ASC;		

SELECT film_id, title, rental_rate												
FROM film												
LIMIT 5 OFFSET 5;												

SELECT * 											
FROM customer											
WHERE customer_name ILIKE '%al%';											

-- Bài tập cơ bản

-- 1. Lấy danh sách 10 khách hàng đầu tiên, hiển thị theo cả 2 yêu cầu sau:															
-- - Tên viết hoa															
-- - Email ở dạng chữ thường															
SELECT 
    UPPER(CUSTOMER_NAME) AS NAME,
    LOWER(EMAIL) AS EMAIL
FROM CUSTOMER
LIMIT 10;


-- 2. Tính số tiền sau thuế (giả sử +10% của total_amount) của từng đơn hàng. 																						
-- Chỉ tính cho các đơn hàng có total_amount từ 100 đến 500, và hiển thị với cột alias tong_sau_thue.																						
SELECT ORDER_NUMBER, TOTAL_AMOUNT, TOTAL_AMOUNT * 1.1 AS TONG_SAU_THUE
FROM ORDERS
WHERE TOTAL_AMOUNT BETWEEN 100 AND 500


-- 3. Lấy danh sách 1000 khách hàng VIP (GOLD hoặc PLATINUM), sắp xếp theo tên tăng dần, và bỏ qua 500 người đầu tiên.																											
SELECT *
FROM CUSTOMER
WHERE VIP_TIER IN ('GOLD', 'PLATINUM')
ORDER BY CUSTOMER_NAME ASC
LIMIT 1000 OFFSET 500


-- 4. Lấy danh sách các thành phố (city) duy nhất của khách hàng VIP (GOLD hoặc PLATINUM).																										
-- Chỉ lấy những khách hàng còn hoạt động (is_active = TRUE), và sắp xếp tên thành phố theo thứ tự bảng chữ cái tăng dần.																										
SELECT DISTINCT CITY
FROM CUSTOMER
WHERE VIP_TIER IN ('GOLD', 'PLATINUM') AND IS_ACTIVE = 'TRUE'
ORDER BY CITY ASC


-- 5. Tạo cột mới phân loại trạng thái đơn hàng (alias trang_thai_don_hang) thõa điều kiện:																			
-- - Nếu status = 'PENDING' → 'Chờ xử lý'																			
-- - Nếu status = 'PAID' hoặc status = 'SHIPPED' → 'Đang thực hiện'																			
-- - Ngược lại → 'Khác'																			
SELECT
	STATUS,
	CASE
		WHEN STATUS = 'PENDING' THEN 'CHỜ XỬ LÝ'
		WHEN STATUS IN ('PAID', 'SHIPPED') THEN 'ĐANG THỰC HIỆN'
		ELSE 'KHÁC'
	END AS TRANG_THAI_DON_HANG
FROM
	ORDERS


-- 6. Hãy viết truy vấn lấy danh sách 100 khách hàng đầu tiên, hiển thị thêm cột phan_loai theo tên như sau:																								
-- - Nếu customer_name bắt đầu bằng 'Je' → hiển thị 'Tên bắt đầu Je'																								
-- - Nếu customer_name chứa 'smith' (không phân biệt hoa/thường, dùng ILIKE) → hiển thị 'Tên có chứa Smith'																								
-- - Ngược lại → 'Khác'																								
EXPLAIN ANALYZE
SELECT
	CUSTOMER_NAME,
	CASE
		WHEN CUSTOMER_NAME LIKE 'Je%' THEN 'TÊN BẮT ĐẦU Je'
		WHEN CUSTOMER_NAME ILIKE '%SMITH%' THEN 'TÊN CÓ CHỨA SMITH'
		ELSE 'KHÁC'
	END AS PHAN_LOAI
FROM
	CUSTOMER
LIMIT
	100



-- =============  Bài tập nâng cao


-- 2.4.1
-- Hãy viết truy vấn để hiển thị trang số 3 của danh sách đơn hàng đã giao thành công (status = 'DELIVERED').																									
-- Mỗi trang gồm 20 đơn hàng, sắp xếp theo ngày đặt hàng mới nhất trước (nếu cùng ngày, sắp theo order_number ASC).																									

SELECT *
FROM ORDERS
WHERE STATUS = 'DELIVERED'
ORDER BY
	ORDER_DATE DESC,
	ORDER_NUMBER ASC
LIMIT 20
OFFSET 20 * 2


-- 2.4.2
-- Tạo danh sách 10 khách hàng mới nhất (theo created_at) và hiển thị các cột với alias:																						
-- - full_info: nối tên khách hàng + email + số điện thoại																						
-- - name_length: độ dài tên khách hàng																						
-- - email_domain: chỉ lấy phần domain của email (sau dấu @) (gợi ý: dùng SUBSTRING + POSITION)																						
SELECT CUSTOMER_ID, CONCAT(CUSTOMER_NAME, EMAIL, PHONE) AS FULL_INFO,
		LENGTH(CUSTOMER_NAME) NAME_LENGTH, 
		SUBSTRING(EMAIL FROM POSITION('@' IN EMAIL) + 1) AS EMAIL_DOMAIN
FROM CUSTOMER
ORDER BY CREATED_AT DESC
LIMIT 10


-- 2.4.3 Biểu thức điều kiện (CASE):																								
-- Viết truy vấn lấy danh sách 100 đơn hàng gần nhất (theo created_at) và phân loại cột order_value_level như sau:																								
-- total_amount < 50 → 'Rất nhỏ'																								
-- 50 <= total_amount < 200 → 'Nhỏ'																								
-- 200 <= total_amount < 1000 → 'Trung bình'																								
-- >= 1000 → 'Lớn'	

CREATE INDEX idx_orders_created ON orders(created_at desc) include (ORDER_NUMBER, TOTAL_AMOUNT);	
-- về tham khảo hc thêm về covering
DROP INDEX IF EXISTS idx_orders_created

SELECT indexname, indexdef
FROM pg_indexes
WHERE tablename = 'orders';

EXPLAIN ANALYZE
SELECT ORDER_NUMBER, TOTAL_AMOUNT,
	CASE
	WHEN TOTAL_AMOUNT < 50 THEN 'Rất nhỏ'
	WHEN TOTAL_AMOUNT < 200 THEN 'Nhỏ'
	WHEN TOTAL_AMOUNT < 1000 THEN 'Trung bình'
	ELSE 'Lớn'
	END AS ORDER_VALUE_LEVEL
FROM ORDERS
ORDER BY CREATED_AT DESC
LIMIT 100

-- 3.7 Bài tập tối ưu hóa																													
-- Hãy kiểm tra và tối ưu hóa cho các truy vấn sau:																													
-- B1.		
create index idx_customer_id on orders using hash (customer_id);

-- Tắt index scan


SELECT indexname, indexdef FROM pg_indexes WHERE tablename = 'orders';

EXPLAIN ANALYZE
SELECT *																											
FROM orders																											
WHERE customer_id = 90000;																											
																													
-- B2.	
CREATE INDEX idx_orders_customer_id ON orders(customer_id);

CREATE INDEX IF NOT EXISTS idx_orders_customer_id
						ON ORDERS (CUSTOMER_ID) 
						INCLUDE (ORDER_NUMBER, TOTAL_AMOUNT, ORDER_DATE);
						
DROP INDEX IF EXISTS idx_orders_customer_id

EXPLAIN ANALYZE
SELECT *																											
FROM orders																											
WHERE customer_id BETWEEN 80000 AND 99000;																											

SET enable_indexscan = on;
SET enable_bitmapscan = on;
SET enable_seqscan = on;
-- B3.
DROP INDEX IF EXISTS idx_orders_customer_id_full
CREATE INDEX idx_orders_customer_id_full ON orders(customer_id) include (status, payment_method)

-- include (id,
--     order_number,
--     order_date,
--     status,
--     payment_method,
--     shipping_method,
--     channel,
--     currency,
--     coupon_code,
--     subtotal_amount,
--     discount_amount,
--     tax_amount,
--     shipping_fee,
--     total_amount,
--     created_at,
--     updated_at);

-- ANALYZE orders

EXPLAIN ANALYZE
SELECT customer_id, status, payment_method																							
FROM orders																											
WHERE customer_id BETWEEN 90000 AND 99000;																											
																													
-- B4.	
CREATE INDEX idx_orders_status_date ON orders(status, order_date);
SELECT indexname, indexdef FROM pg_indexes WHERE tablename = 'orders';

DROP INDEX IF EXISTS idx_orders_status_date

EXPLAIN ANALYZE
SELECT COUNT(*)																											
		FROM orders																											
		WHERE status = 'PAID'																											
		  AND order_date >= CURRENT_DATE - INTERVAL '30 days';																											
																													
-- B5.	
DROP INDEX IF EXISTS idx_orders_status_date
CREATE INDEX idx_orders_status_date_number 
ON orders(status, order_date DESC, order_number ASC) INCLUDE (customer_id, total_amount);

CREATE INDEX idx_orders_status_date_number2
ON orders(status, order_date DESC, order_number ASC);

DROP INDEX IF EXISTS idx_orders_status_date_number

EXPLAIN ANALYZE
SELECT order_number, customer_id, order_date, total_amount																											
		FROM orders																											
		WHERE status = 'DELIVERED'																											
		ORDER BY order_date DESC, order_number ASC																										
																													
-- B6.	
																									
EXPLAIN 
ANALYZE
SELECT ORDER_NUMBER
FROM ORDERS
WHERE DATE_TRUNC('DAY', ORDER_DATE) = DATE '2025-08-01';

CREATE INDEX IDX_ORDERS_ORDER_DATE ON ORDERS (ORDER_DATE);

DROP INDEX IF EXISTS IDX_ORDERS_STATUS_DATE_NUMBER;

EXPLAIN 
ANALYZE
SELECT ORDER_NUMBER
FROM ORDERS
WHERE ORDER_DATE >= TIMESTAMP '2025-08-01 00:00:00'
  AND ORDER_DATE < TIMESTAMP '2025-08-02 00:00:00';







		
		







													


	