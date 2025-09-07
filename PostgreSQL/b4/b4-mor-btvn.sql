
-- 2.4 
-- 1. Tìm khách hàng có ít nhất một đơn CANCLELED và có ít nhất một đơn DELIVERED 
SELECT c.customer_id, c.customer_name
FROM customer c
WHERE EXISTS (
	SELECT 1
	FROM orders o
	WHERE o.customer_id = c.customer_id
	  AND o.status = 'CANCELLED'
)
AND EXISTS (
	SELECT 1 
	FROM orders o
	WHERE o.customer_id = c.customer_id
	  AND o.status = 'DELIVERED'
);

-- 2. Tìm ngày có tổng doanh thu cao nhất

SELECT created_at::date, SUM(total_amount) as total_renueve 
FROM ORDERS
GROUP BY created_at::date
ORDER BY total_renueve DESC
LIMIT 1;

-- 3.4 Bài tập nâng cao 
--- 1. Tìm tất cả khách hàng mà tồn tại ít nhất một đơn hàng có status là CANCELLED

SELECT c.customer_id, c.customer_name
FROM customer c
WHERE EXISTS (
	SELECT 1 
	FROM orders o
	WHERE o.customer_id = c.customer_id
	AND o.status = 'CANCELLED'
)
--- 2. tìm tất cả khách hàng mà không tồn tài bất kì đơn nào có status là DELIVERED

SELECT c.customer_id, c.customer_name
FROM customer c
WHERE NOT EXISTS (
	SELECT 1 
	FROM orders o
	WHERE o.customer_id = c.customer_id
	AND o.status = 'DELIVERED'
)

-- 3. tìm khách hàng GOLD có loyalty_points lơn hơn tất cả khách PLATINUM 
SELECT *
FROM customer AS c
WHERE c.vip_tier = 'GOLD'
  AND loyalty_points > (
	  SELECT loyalty_points
	  FROM customer AS cus
	  WHERE cus.vip_tier = 'PLATINUM'
	  ORDER BY loyalty_points DESC 
	  LIMIT 1
  );

-- 4. tìm khách hàng SILVER có loyalty_points >= ít nhất một khách GOLD 
SELECT *
FROM customer AS c
WHERE c.vip_tier = 'SILVER'
  AND c.loyalty_points >= ANY (
	  SELECT loyalty_points
	  FROM customer AS cus
	  WHERE cus.vip_tier = 'GOLD'
  );




	

	



	


