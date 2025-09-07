-- 1	LẤY TẤT CẢ CÁC CỘT TỪ BẢNG CUSTOMER.
SELECT * FROM CUSTOMER

-- 2	LẤY TÊN VÀ EMAIL CỦA TẤT CẢ KHÁCH HÀNG.	
SELECT CUSTOMER_NAME, EMAIL
FROM CUSTOMER

-- 3	LẤY 10 KHÁCH HÀNG ĐẦU TIÊN.	
SELECT *
FROM CUSTOMER
LIMIT 10

-- 4	Lấy danh sách đơn hàng gồm id, order_number, total_amount.		
SELECT ID, ORDER_NUMBER, TOTAL_AMOUNT FROM ORDERS

-- 5	Lấy các khách hàng ở thành phố “New York”.	
CREATE EXTENSION IF NOT EXISTS pg_trgm;																	
CREATE INDEX idx_customer_city_trgm 																	
ON customer USING gin (city gin_trgm_ops);																	

EXPLAIN ANALYZE
SELECT * 
FROM CUSTOMER
WHERE CITY ILIKE 'New York'

-- 6	Tìm các khách hàng có vip_tier = 'GOLD'.
CREATE EXTENSION IF NOT EXISTS pg_trgm;																	
CREATE INDEX idx_customer_city_trgm 																	
ON customer USING gin (city gin_trgm_ops);

SELECT * 
FROM CUSTOMER
WHERE VIP_TIER ILIKE 'GOLD'

-- 7	Tìm khách hàng có loyalty_points > 2000.	
SELECT * 
FROM CUSTOMER
WHERE LOYALTY_POINTS > 2000

-- 8	Tìm khách hàng có tên bắt đầu bằng 'James'.	
SELECT * 
FROM CUSTOMER
WHERE CUSTOMER_NAME LIKE 'James%'

-- 9	Tìm đơn hàng có status = 'CANCELLED'.	
SELECT *
FROM ORDERS
WHERE STATUS ILIKE 'CANCELLED'

-- 10	Lấy 5 đơn hàng có total_amount cao nhất.	
SELECT *
FROM ORDERS
ORDER BY TOTAL_AMOUNT DESC
LIMIT 5

-- 11	Tìm khách hàng sinh từ 1990 đến 2000.
SELECT *
FROM CUSTOMER
WHERE EXTRACT(YEAR FROM BIRTH_DATE) BETWEEN 1990 AND 2000

-- 12	Tìm đơn hàng có tổng tiền từ 100 đến 200 USD.	
SELECT *
FROM ORDERS
WHERE TOTAL_AMOUNT BETWEEN 100 AND 200

-- 13	Tìm khách hàng ở thành phố “Los Angeles” hoặc “Chicago”.
explain analyze
SELECT *
FROM CUSTOMER
WHERE CITY IN ('Los Angeles', 'Chicago')

-- 14	Lấy đơn hàng thanh toán bằng thẻ và trạng thái = 'PAID'.	
SELECT *
FROM ORDERS
WHERE PAYMENT_METHOD ILIKE 'CARD' AND STATUS ILIKE 'PAIN'

-- 15	Lấy khách hàng có email kết thúc bằng gmail.com.
SELECT *
FROM CUSTOMER
WHERE EMAIL LIKE '%gmail.com'

-- 16	Tìm 10 khách hàng có nhiều loyalty_points nhất.	
SELECT *
FROM CUSTOMER
ORDER BY LOYALTY_POINTS DESC
LIMIT 10

-- 17	Lấy 5 đơn hàng gần nhất theo created_at.
SELECT *
FROM ORDERS
ORDER BY CREATED_AT DESC
LIMIT 5

-- 18	Tìm khách hàng mới đăng ký gần đây nhất.	
SELECT *
FROM CUSTOMER
ORDER BY CREATED_AT DESC
LIMIT 1

-- 19	Lấy 10 khách hàng sinh sớm nhất (tuổi lớn nhất).		

SELECT *
FROM CUSTOMER
ORDER BY BIRTH_DATE
LIMIT 10

-- 20	Lấy 5 đơn hàng có phí vận chuyển cao nhất.		
SELECT *
FROM ORDERS
ORDER BY TAX_AMOUNT DESC
LIMIT 5

-- 21	Lấy 10 khách hàng nữ có loyalty_points cao nhất.
SELECT *
FROM CUSTOMER
WHERE GENDER = 'F'
ORDER BY LOYALTY_POINTS DESC
LIMIT 10

-- 22	Tìm 5 đơn hàng lớn nhất nhưng chỉ lấy của kênh web.
SELECT *
FROM ORDERS
WHERE CHANNEL ILIKE 'WEB'
ORDER BY TOTAL_AMOUNT DESC
LIMIT 5

-- 23	Lấy 5 đơn hàng nhỏ nhất có trạng thái PAID.		
SELECT *
FROM ORDERS
WHERE STATUS ILIKE 'PAID'
ORDER BY TOTAL_AMOUNT
LIMIT 5

-- 24	Lấy 10 khách hàng VIP (vip_tier khác NONE) mới nhất theo ngày tạo.
SELECT *
FROM CUSTOMER
WHERE VIP_TIER != 'NONE'
ORDER BY CREATED_AT DESC
LIMIT 10

-- 25	Lấy 5 khách hàng đã opt-in marketing có nhiều loyalty_points nhất.									
SELECT *
FROM CUSTOMER
WHERE MARKETING_OPT_IN IS TRUE
ORDER BY LOYALTY_POINTS DESC
LIMIT 5

