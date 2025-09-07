
set enable_bitmapscan = off;
set enable_indexscan = off;

EXPLAIN ANALYZE
SELECT *
FROM orders
WHERE customer_id = 90000;
-- -> Nên dùng index

EXPLAIN ANALYZE
SELECT *
FROM orders
WHERE customer_id BETWEEN 80000 AND 99000;
-- -> ko nên dùng index

RESET ALL

EXPLAIN ANALYZE
SELECT COUNT(*)	
FROM orders	
WHERE status = 'PAID'	
  AND order_date >= CURRENT_DATE - INTERVAL '30 days';	



