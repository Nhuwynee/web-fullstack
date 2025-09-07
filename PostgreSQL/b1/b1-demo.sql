-- Xem workspace
SELECT SPCNAME, PG_TABLESPACE_LOCATION(OID)
FROM PG_TABLESPACE

-- Tạo table lưu vào nhuwspace
CREATE TABLE FILM_TEST (
	FILM_ID SERIAL PRIMARY KEY,
	TITLE TEXT NOT NULL,
	RENTAL_RATE NUMERIC(4, 2),
	LENGTH INT
) TABLESPACE nhuwspace;

SELECT INDEXNAME, INDEXDEF
FROM PG_INDEXES;

-- ORDERS

-- Xem các index của bảng Orders
SELECT INDEXNAME, INDEXDEF
FROM PG_INDEXES WHERE TABLENAME = 'ORDERS';

-- Tạo index cho customer_id
CREATE INDEX IDX_ORDERS_CUSTOMER_ID ON ORDERS(CUSTOMER_ID)
-- Xóa index vừa tạo
DROP INDEX IF EXISTS IDX_ORDERS_CUSTOMER_ID

-- Run để test hiệu suất
EXPLAIN ANALYZE
SELECT * FROM ORDERS
WHERE CUSTOMER_ID = '45000'

-- CUSTOMER

-- Tạo index cho customer_id
CREATE INDEX IDX_CUSTOMER_NAME ON CUSTOMER(CUSTOMER_NAME)

-- Xóa index vừa tạo
DROP INDEX IF EXISTS IDX_CUSTOMER_NAME

-- Run để test hiệu suất
EXPLAIN ANALYZE
SELECT * FROM CUSTOMER
WHERE LOWER(CUSTOMER_NAME) LIKE LOWER('%JOSH%');

-- Đánh GIN index (Generalized Inverted Index) bằng extension pg_trgm (PostgreSQL Trigram)
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_customer_name_trgm ON customer USING gin (customer_name gin_trgm_ops);
DROP INDEX IF EXISTS idx_customer_name_trgm

-- Kiểm tra index của bảng orders
SELECT indexname, indexdef FROM pg_indexes WHERE tablename = 'customer';


