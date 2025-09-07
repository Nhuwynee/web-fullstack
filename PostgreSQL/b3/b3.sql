SELECT STRING_AGG(title, ', ')
FROM film;

SELECT ARRAY_AGG(title)
FROM film;

SELECT BOOL_AND(is_active) AS all_active
FROM customer;

SELECT BOOL_OR(is_active) AS someone_active
FROM customer;

SELECT ARRAY_TO_STRING(ARRAY[1, 2, 3], ', ');

SELECT
	STATUS,
	ARRAY_TO_STRING(
		(
			ARRAY_AGG(
				ORDER_NUMBER
				ORDER BY
					CREATED_AT DESC
			)
		) [1:5],
		', '
	) AS SAMPLE_ORDERS
FROM
	ORDERS
GROUP BY
	STATUS;

-- test
CREATE TABLE SCORES (
	ID SERIAL PRIMARY KEY,
	STUDENT VARCHAR(50),
	MATH INT,
	PHYSICS INT,
	ENGLISH INT
) TABLESPACE nhuwspace;

INSERT INTO
	SCORES (STUDENT, MATH, PHYSICS, ENGLISH)
VALUES
	('An', 8, 6, 9),
	('Bình', 7, 9, 8),
	('Chi', 5, 7, 6),
	('Dũng', 10, 9, 8),
	('Hạnh', 6, 6, 7),
	('Lan', 4, 8, 5),
	('Minh', 9, 7, 9),
	('Nga', 3, 5, 6),
	('Oanh', 7, 8, 10),
	('Phúc', 6, 9, 7);

SELECT * FROM SCORES

SELECT student, math, physics, english,
GREATEST(math, physics, english) AS highest_score,
LEAST(math, physics, english)    AS lowest_score
FROM scores;


SELECT DATE_TRUNC('month', order_date)
FROM orders;


SELECT STATUS, SUM(TOTAL_AMOUNT) AS TOTAL_SALES
FROM ORDERS
GROUP BY STATUS
HAVING STATUS = 'DELIVERED' AND SUM(TOTAL_AMOUNT) > 2000

-- Lý thuyết nâng cao
SELECT status, payment_method, SUM(total_amount) AS total_sales
FROM orders
GROUP BY GROUPING SETS (
    (status),         -- nhóm theo trạng thái
    (payment_method), -- nhóm theo phương thức thanh toán
    ()                -- tổng cộng (grand total)
);


SELECT CHANNEL, STATUS, SUM(TOTAL_AMOUNT) AS TOTAL_SALES
FROM ORDERS
GROUP BY ROLLUP(CHANNEL, STATUS)
ORDER BY CHANNEL, STATUS


SELECT STATUS, PAYMENT_METHOD, CHANNEL, SUM(TOTAL_AMOUNT) AS TOTAL_SALES
FROM ORDERS
GROUP BY CUBE(STATUS, PAYMENT_METHOD, CHANNEL)
ORDER BY STATUS, PAYMENT_METHOD, CHANNEL

-- tạo materialized view (chưa tính dữ liệu)
CREATE MATERIALIZED VIEW mv_monthly_channel_revenue AS
SELECT 
	DATA_TRUC('MONTH', ORDER_DATE) AS MONTH,
	CHANNEL,
	SUM(TOTAL_AMOUNT) AS TOTAL_SALES,
	COUNT(*) AS ORDERS_COUNT
FROM ORDERS
GROUP BY DATE_TRUNC('MONTH', ORDER_DATE), CHANNEL
WITH NO DATA; -- tạo khung, chưa nạp dữ liệu

-- tính dữ liệu lần đầu
REFRESH MATERIALIZED VIEW mv_monthly_channel_revenue;

-- truy vấn nhanh: chỉ cần đọc vài nghìn dòng đã tổng hợp sẵn
-- nhanh hơn rất nhiều nếu chạy trên bảng gốc orders (5 triệu dòng)
SELECT *
FROM mv_monthly_channel_revenue
WHERE MONTH >= DATE '2025-01-01'
ORDER BY MONTH, CHANNEL;

-- làm mới định kỳ (ví dụ hằng đêm) bằng cron job hay pgAgent
REFRESH MATERIALIZED VIEW mv_monthly_channel_revenue;

-- tăng workers
SET max_parallel_workers_per_gather = 3;

EXPLAIN ANALYZE
SELECT 
    DATE_TRUNC('day', order_date) AS day,
    SUM(total_amount) AS total_sales,
    COUNT(*) AS orders_count
FROM orders
WHERE order_date >= '2025-01-01'
GROUP BY day
ORDER BY day;



-- Bài tập cơ bản
-- 1. Tổng doanh thu theo trạng thái đơn hàng
-- Yêu cầu: Tính tổng total_amount theo từng status, sắp xếp giảm dần theo doanh thu.
SELECT STATUS, SUM(TOTAL_AMOUNT) AS TOTAL
FROM ORDERS
GROUP BY STATUS
ORDER BY TOTAL DESC

-- 2. Đếm số đơn theo phương thức thanh toán
-- Yêu cầu: Đếm số đơn hàng theo payment_method, sắp xếp theo số lượng giảm dần.
SELECT PAYMENT_METHOD, COUNT(*) AS COUNT_ORDERS
FROM ORDERS
GROUP BY PAYMENT_METHOD
ORDER BY COUNT(*) DESC

-- 3. Doanh thu theo tháng năm 2025
-- Yêu cầu: Tính số đơn, tổng doanh thu và giá trị trung bình đơn theo tháng của năm 2025.
SELECT * FROM ORDERS

SELECT 
    EXTRACT(MONTH FROM ORDER_DATE) AS MONTH,
    COUNT(*) AS TOTAL_ORDERS,
    SUM(TOTAL_AMOUNT) AS TOTAL_REVENUE,
    AVG(TOTAL_AMOUNT) AS AVG_ORDERS
FROM ORDERS
WHERE EXTRACT(YEAR FROM ORDER_DATE) = 2025
GROUP BY MONTH;

-- 4. Tình hình opt-in marketing theo kênh signup (BOOL_AND/BOOL_OR)
-- Yêu cầu: Với bảng customer, thống kê theo signup_source để hiển thị các cột: 
-- - Tổng khách, số khách opt-in, tỷ lệ opt-in (%), và cờ kiểm tra any_opt_in/all_opt_in.
SELECT * FROM CUSTOMER

SELECT 
    SIGNUP_SOURCE,
    COUNT(*) AS TOTAL_CUSTOMERS,
    SUM(CASE WHEN MARKETING_OPT_IN THEN 1 ELSE 0 END) AS OPT_IN_CUSTOMERS,
    ROUND((SUM(CASE WHEN MARKETING_OPT_IN THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS OPT_IN,
    BOOL_OR(MARKETING_OPT_IN) AS ANY_OPT_IN,
    BOOL_AND(MARKETING_OPT_IN) AS ALL_OPT_IN
FROM CUSTOMER
GROUP BY SIGNUP_SOURCE;

-- 5. Phí vận chuyển trung bình theo phương thức giao hàng
-- Yêu cầu: Tính AVG(shipping_fee) theo shipping_method, chỉ lấy các phương thức có trung bình > 5.
SELECT SHIPPING_METHOD, AVG(SHIPPING_FEE) AS AVG_SHIPPING_FEE
FROM ORDERS
GROUP BY SHIPPING_METHOD
HAVING AVG(SHIPPING_FEE) > 5

-- BÀI TẬP NÂNG CAO
-- 1. Top 10 ngày doanh thu cao nhất
-- Yêu cầu: Tìm 10 ngày có tổng doanh thu cao nhất. Chỉ xét các ngày có tối thiểu 10.000 đơn để tránh ngày ít dữ liệu.
SELECT EXTRACT(DAY FROM ORDER_DATE) AS DAY, 
		SUM(TOTAL_AMOUNT) AS TOTAL_REVENUE,
		COUNT(*) AS TOTAL_ORDERS
FROM ORDERS
GROUP BY EXTRACT(DAY FROM ORDER_DATE)
HAVING COUNT(*) > 1000
ORDER BY TOTAL_REVENUE DESC
LIMIT 10

-- 2. Tỷ lệ dùng coupon theo phương thức giao hàng
-- Yêu cầu: Với mỗi shipping_method, tính tổng số đơn, số đơn có coupon (coupon_code IS NOT NULL) và tỷ lệ sử dụng coupon (%).
SELECT SHIPPING_METHOD,
		COUNT(*) AS COUNT_ORDERS,
		SUM(CASE WHEN COUPON_CODE IS NOT NULL THEN 1 ELSE 0 END) AS COUNT_COUPON,
		 ROUND(SUM(CASE WHEN COUPON_CODE IS NOT NULL THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS COUPON_RATE
FROM ORDERS
GROUP BY SHIPPING_METHOD

select * from customer
-- 3. Top 10 thành phố có nhiều khách hàng VIP đang hoạt động
-- Yêu cầu: Với bảng customer, tìm 10 thành phố có nhiều khách đang hoạt động (is_active = TRUE) và thuộc hạng GOLD/PLATINUM nhất.
-- Hiển thị kèm 5 tên khách mẫu mới tạo gần đây mỗi thành phố.
SELECT CITY, 
       COUNT(*) AS COUNT_CUSTOMER,
       ARRAY_TO_STRING(
           (ARRAY_AGG(CUSTOMER_NAME ORDER BY CREATED_AT DESC))[1:5],
           ', '
       ) AS NAME_CUSTOMER
FROM CUSTOMER
WHERE IS_ACTIVE = TRUE 
  AND VIP_TIER IN ('GOLD', 'PLATINUM')
GROUP BY CITY
ORDER BY COUNT_CUSTOMER DESC
LIMIT 10







