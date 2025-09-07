SELECT 
    c.customer_id, 
    c.customer_name,
    (
        SELECT COUNT(*) 
        FROM orders o
        WHERE o.customer_id = c.customer_id
    ) AS total_orders
FROM customer c
LIMIT 10;

SELECT * 
FROM film
WHERE film_id IN (
	SELECT DISTINCT film_id
	FROM rental
	WHERE rental_date BETWEEN '2023-01-02' AND '2023-01-07'
);

SELECT *
FROM film f
WHERE EXISTS (
	SELECT 1 -- không quan tâm đến giá trị SELECT này, EXISTS chỉ cần kiểm tra có record nào thỏa mãn
	FROM rental r
	WHERE r.film_id = f.film_id
		AND r.rental_date BETWEEN '2023-01-02' AND '2023-01-07'
);

SELECT *
FROM (
	SELECT customer_id, COUNT(*) AS order_count
	FROM orders
	GROUP BY customer_id
) t
WHERE t.order_count > 50;

SELECT customer_id, customer_name
FROM customer c
WHERE (
	SELECT SUM(total_amount)
	FROM orders o
	WHERE o.customer_id = c.customer_id
) = (
	SELECT MAX(total_spent)
	FROM (
		SELECT SUM(total_amount) AS total_spent
		FROM orders
		GROUP BY customer_id
	) t
);


SELECT order_date, status, COUNT(*) AS order_count
FROM orders
GROUP BY order_date, status;

SELECT order_date, COUNT(*) AS total_order_count
FROM orders
GROUP BY order_date;

SELECT
	day_detail.order_date,
	day_detail.status,
	ROUND(100.0 * day_detail.order_count / day_total.total_order_count, 2) AS percentage
FROM (
	-- Bước 1
	SELECT order_date, status, COUNT(*) AS order_count
	FROM orders
	GROUP BY order_date, status
) day_detail,
(
	-- Bước 2
	SELECT order_date, COUNT(*) AS total_order_count
	FROM orders
	GROUP BY order_date
) day_total
WHERE day_detail.order_date = day_total.order_date
ORDER BY day_detail.order_date, percentage DESC;

-- bài tập cơ bản
-- Lấy đơn hàng mới nhất cho từng khách hàng (chỉ lấy 10 khách hàng đầu)
SELECT C.CUSTOMER_ID, C.CUSTOMER_NAME,
	(SELECT MAX(O.ORDER_DATE)
    FROM ORDERS O
    WHERE O.CUSTOMER_ID = C.CUSTOMER_ID
) AS LAST_ORDER_DATE
FROM CUSTOMER C
LIMIT 10

-- 2. Subquery trong WHERE
-- 2.1 Với IN
-- - Lấy tất cả đơn hàng của những khách hàng VIP (GOLD hoặc PLATINUM).
SELECT *
FROM ORDERS
WHERE CUSTOMER_ID IN (SELECT CUSTOMER_ID FROM CUSTOMER WHERE VIP_TIER IN ('GOLD', 'PLATINUM'))

-- 2.2 Với EXISTS
-- - Tìm khách hàng có ít nhất một đơn hàng bị hủy.
SELECT c.customer_id, customer_name
FROM CUSTOMER C
WHERE EXISTS (
	SELECT 1
	FROM ORDERS O
	WHERE C.CUSTOMER_ID = O.CUSTOMER_ID AND O.STATUS = 'CANCELLED'
)
-- 2.3 Với toán tử so sánh
-- - Tìm 10 khách hàng đầu tiên có tổng chi tiêu > 30000
select c.customer_id, customer_name
from customer c
where (select sum(total_amount) as tong_chi_tieu
		from orders o
		where c.customer_id = o.customer_id) > 30000
limit 10

-- 3. Subquery trong FROM
-- - Tìm ngày nào có nhiều đơn hàng nhất
-- - Tính tỷ lệ đơn hàng theo trạng thái







