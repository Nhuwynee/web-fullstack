SELECT
	CUSTOMER_ID,
	ORDER_DATE,
	TOTAL_AMOUNT,
	SUM(TOTAL_AMOUNT) OVER (
		PARTITION BY
			CUSTOMER_ID
		ORDER BY
			ORDER_DATE
	) AS CUMULATIVE_AMOUNT
FROM
	ORDERS
LIMIT
	100;

---
CREATE OR REPLACE VIEW customer_orders AS	
SELECT o.id, o.order_date, o.status, c.customer_name, c.customer_id	
FROM orders o	
JOIN customer c ON o.customer_id = c.customer_id;	


SELECT * FROM customer_orders WHERE status = 'PAID';
