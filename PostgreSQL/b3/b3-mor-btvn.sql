
--  tạo một materialized view mv_daily_sales tính doanh thu và tổng số đơn hàng theo ngày 
CREATE MATERIALIZED VIEW mv_daily_sales AS
SELECT 
	created_at::date AS order_date,
	SUM(total_amount) AS total_revenue,
	COUNT(*) AS total_orders
FROM orders
GROUP BY created_at::date
ORDER BY order_date;

--  test 
SELECT * FROM mv_daily_sales;

-- 	chạy một truy vấn đọc doanh thu 30 ngày gần nhất từ mv-daily-sales và so sánh tốc độ
--  với chạy trực tiếp trên bảng order 
-- chạy vừ view 
SELECT *
FROM mv_daily_sales
WHERE order_date >= CURRENT_DATE - INTERVAL '30 days'
ORDER BY order_date;

-- chạy trên bảng 
SELECT created_at::date AS order_date,
	   SUM(total_amount) AS total_revenue,
	   COUNT(*) AS total_orders
FROM orders
WHERE created_at >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY created_at::date
ORDER BY order_date;