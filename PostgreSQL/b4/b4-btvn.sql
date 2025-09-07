
-- 1	Lấy 10 khách hàng có loyalty_points cao nhất nhưng không thuộc VIP tier = 'NONE'.
SELECT *
FROM CUSTOMER
WHERE VIP_TIER <> 'NONE'
ORDER BY LOYALTY_POINTS DESC
LIMIT 10

-- 2	Lấy 5 đơn hàng có phí vận chuyển cao nhất trong tháng gần đây nhất.
SELECT ORDER_NUMBER,
		DATE_TRUNC('MONTH', ORDER_DATE) AS lastest_month,
		SHIPPING_FEE
FROM ORDERS
WHERE DATE_TRUNC('MONTH', ORDER_DATE) = (SELECT DATE_TRUNC('month', MAX(ORDER_DATE)) FROM ORDERS)
ORDER BY SHIPPING_FEE DESC
LIMIT 5

-- 3	Tìm 10 khách hàng đăng ký sớm nhất nhưng có loyalty_points > 1000.
SELECT CUSTOMER_ID, CUSTOMER_NAME, CREATED_AT, LOYALTY_POINTS
FROM CUSTOMER
WHERE LOYALTY_POINTS > 1000
ORDER BY CREATED_AT DESC
LIMIT 10

-- 4	Tìm khách hàng có email chứa 'yahoo' nhưng không ở thành phố New York.
SELECT CUSTOMER_ID, CUSTOMER_NAME, EMAIL, CITY
FROM CUSTOMER
WHERE EMAIL ILIKE '%yahoo%' AND CITY <> 'New York'

-- 5	Tìm đơn hàng có total_amount lớn hơn trung bình của tất cả đơn hàng.
SELECT ORDER_NUMBER, TOTAL_AMOUNT
FROM ORDERS
WHERE TOTAL_AMOUNT > (SELECT AVG(TOTAL_AMOUNT) FROM ORDERS)

-- 6	Tìm các thành phố có trung bình loyalty_points > 1500.
SELECT CITY, AVG(LOYALTY_POINTS)
FROM CUSTOMER
GROUP BY CITY
HAVING AVG(LOYALTY_POINTS) > 1500

-- 7	Tìm phương thức thanh toán có số đơn hàng > 100 và trung bình total_amount > 200.
SELECT PAYMENT_METHOD, COUNT(*) AS COUNT_ORDERS, AVG(TOTAL_AMOUNT) AS AVG_TOTAL_AMOUNT
FROM ORDERS
GROUP BY PAYMENT_METHOD
HAVING COUNT(*) > 100 AND AVG(TOTAL_AMOUNT) > 200

-- 8	Liệt kê top 5 kênh bán hàng có tổng doanh thu lớn nhất.
SELECT CHANNEL, SUM(TOTAL_AMOUNT) AS TOTAL_REVENUE
FROM ORDERS
GROUP BY CHANNEL
ORDER BY TOTAL_REVENUE DESC
LIMIT 5

-- 9	Tìm trạng thái đơn hàng có nhiều hơn 50 đơn hàng nhưng tổng giá trị < 5000.
SELECT STATUS, COUNT(*) AS COUNT_ORDERS, SUM(TOTAL_AMOUNT) AS TOTAL
FROM ORDERS
GROUP BY STATUS
HAVING COUNT(*) > 50 AND SUM(TOTAL_AMOUNT) < 5000

-- 10	Liệt kê các VIP tier có ít hơn 20 khách hàng.
SELECT VIP_TIER, COUNT(*) AS COUNT_CUSTOMER
FROM CUSTOMER
GROUP BY VIP_TIER
HAVING COUNT(*) < 20

-- 11	Tìm khách hàng có loyalty_points lớn hơn loyalty_points trung bình của tất cả khách hàng.
SELECT CUSTOMER_ID, CUSTOMER_NAME, LOYALTY_POINTS
FROM CUSTOMER
WHERE LOYALTY_POINTS > (SELECT AVG(LOYALTY_POINTS) FROM CUSTOMER)

-- 12	Tìm đơn hàng có total_amount bằng giá trị lớn nhất trong bảng orders.
SELECT ORDER_NUMBER, TOTAL_AMOUNT
FROM ORDERS
WHERE TOTAL_AMOUNT = (SELECT MAX(TOTAL_AMOUNT) FROM ORDERS)

-- Bỏ: 13	Lấy các khách hàng có ngày sinh nhỏ hơn khách hàng lớn tuổi nhất.
-- 14	Tìm đơn hàng thuộc về khách hàng có tổng số đơn hàng nhiều nhất.
SELECT O.ORDER_NUMBER, O.CUSTOMER_ID
FROM ORDERS O
WHERE O.CUSTOMER_ID IN (SELECT CUSTOMER_ID 
						FROM ORDERS 
						GROUP BY CUSTOMER_ID
						ORDER BY COUNT(*) DESC 
						LIMIT 1)

-- 15	Tìm khách hàng có tổng chi tiêu lớn hơn bất kỳ khách hàng nào ở thành phố Chicago.
SELECT CUSTOMER_ID, SUM(TOTAL_AMOUNT) AS TOTAL
FROM ORDERS
GROUP BY CUSTOMER_ID
HAVING SUM(TOTAL_AMOUNT) > ANY 
						(SELECT SUM(TOTAL_AMOUNT) 
						 FROM ORDERS 
						 WHERE CUSTOMER_ID IN (
						 					SELECT CUSTOMER_ID 
											FROM CUSTOMER 
											WHERE CITY = 'Chicago') 
						 GROUP BY CUSTOMER_ID)


-- 16	Lấy danh sách khách hàng cùng với tổng số đơn hàng của họ (dùng subquery trong FROM).
SELECT *
FROM (SELECT CUSTOMER_ID, COUNT(*) AS COUNT_ORDERS
		FROM ORDERS
		GROUP BY CUSTOMER_ID)

-- 17	Tính trung bình tổng chi tiêu của khách hàng, sau đó liệt kê khách hàng có tổng chi tiêu cao hơn mức đó.
SELECT CUSTOMER_ID, SUM(TOTAL_AMOUNT) AS TOTAL
FROM ORDERS
GROUP BY CUSTOMER_ID
HAVING SUM(TOTAL_AMOUNT) > (SELECT AVG(TOTAL_AMOUNT) FROM ORDERS)

-- 18	Lấy top 5 khách hàng theo tổng loyalty_points nhưng chỉ xét những khách hàng có ít nhất 3 đơn hàng.
SELECT CUSTOMER_ID, CUSTOMER_NAME, SUM(LOYALTY_POINTS) AS SUM_LOYALTY
FROM CUSTOMER
WHERE CUSTOMER_ID IN (SELECT CUSTOMER_ID FROM ORDERS GROUP BY CUSTOMER_ID HAVING COUNT(*) >= 3)
GROUP BY CUSTOMER_ID
ORDER BY LOYALTY_POINTS DESC
LIMIT 5

-- 19	Lấy danh sách các thành phố kèm tổng số tiền đơn hàng đã thanh toán (status = PAID), sắp xếp giảm dần.
SELECT city,
       (SELECT SUM(total_amount) 
        FROM orders o
        WHERE o.customer_id IN (
            SELECT customer_id 
            FROM customer c2 
            WHERE c2.city = c.city
        )
        AND o.status = 'PAID'
       ) AS total
FROM customer c
GROUP BY city
ORDER BY total DESC;

-- 20	Tìm khách hàng có tổng số đơn hàng hủy (CANCELLED) nhiều hơn 3.
SELECT CUSTOMER_ID, COUNT(*) AS COUNT_ORDERS
FROM ORDERS
WHERE STATUS = 'CANCELLED'
GROUP BY CUSTOMER_ID
HAVING COUNT(*) > 3

-- 21	Tìm 5 khách hàng có tổng chi tiêu cao nhất bằng cách dùng subquery trong SELECT.
SELECT CUSTOMER_ID, CUSTOMER_NAME, 
		(SELECT SUM(TOTAL_AMOUNT)
		FROM ORDERS O
		WHERE O.CUSTOMER_ID = C.CUSTOMER_ID
		) AS TOTAL
FROM CUSTOMER C
ORDER BY TOTAL DESC
LIMIT 5

-- 22	Liệt kê khách hàng và đơn hàng gần nhất của họ.
SELECT CUSTOMER_ID, CUSTOMER_NAME, 
		(SELECT ORDER_NUMBER 
		 FROM ORDERS O 
		 WHERE O.CUSTOMER_ID = C.CUSTOMER_ID 
		 ORDER BY ORDER_DATE DESC 
		 LIMIT 1) AS LASTEST_ORDERS
FROM CUSTOMER C

-- 23	Tìm các khách hàng có tổng chi tiêu cao hơn tất cả khách hàng ở New York.
SELECT customer_id, SUM(total_amount) AS total
FROM orders
GROUP BY customer_id
HAVING SUM(total_amount) > ALL (
    SELECT SUM(total_amount)
    FROM orders
    WHERE customer_id IN (
        SELECT customer_id
        FROM customer
        WHERE city = 'New York'
    )
    GROUP BY customer_id
)

-- 24	Lấy danh sách các đơn hàng có total_amount lớn hơn trung bình đơn hàng của chính khách hàng đó.
SELECT o.order_number, 
       o.customer_id, 
       o.total_amount
FROM orders o
WHERE o.total_amount > (
    SELECT AVG(o2.total_amount)
    FROM orders o2
    WHERE o2.customer_id = o.customer_id
)

-- 25	Tìm khách hàng chưa từng có đơn hàng nào.
SELECT c.customer_id, c.customer_name
FROM customer c
WHERE NOT EXISTS (
    SELECT 1
    FROM orders o
    WHERE o.customer_id = c.customer_id
)




