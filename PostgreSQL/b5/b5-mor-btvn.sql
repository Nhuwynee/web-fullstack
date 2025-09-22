-- 0) Chuẩn bị môi trường
-- Yêu cầu:
-- 1. Tạo schema lab để mọi bảng tạo trong lab
-- 2. Đặt search_path để ưu tiên schema lab trước
-- 3. Nếu đã có bảng cũ (customers2, orders2), enum cũ (order_status), hãy dọn dẹp sạch sẽ trước để chuẩn bị môi trường
CREATE SCHEMA IF NOT EXISTS lab;
SET search_path TO lab, public;


show search_path
DROP TABLE IF EXISTS orders2 CASCADE;
DROP TABLE IF EXISTS customers2 CASCADE;

-- A) Cấu trúc bảng, kiểu dữ liệu & ràng buộc
-- Yêu cầu:
-- 1. Tạo bảng customers2 với các cột & ràng buộc như đề (SERIAL PK, NOT NULL, UNIQUE, CHECK, DEFAULT, TIMESTAMPTZ)
-- - customer_id: khóa chính
-- - full_name: kiểu VARCHAR, ràng buộc không cho phép dữ liệu trống
-- - email: kiểu VARCHAR, ràng buộc dữ liệu không trùng lặp
-- - phone_number: kiểu VARCHAR, ràng buộc CHECK chỉ cho nhập ký tự số 0 đến 9
-- - birth_date: kiểu DATE
-- - is_active: kiểu BOOLEAN với giá trị mặc định là TRUE
-- - created_at: kiểu TIMESTAMPTZ với giá trị mặc định là ngày giờ hiện tại theo hệ thống

CREATE TABLE customers2 (
	customer_id	 SERIAL PRIMARY KEY,
	full_name	 VARCHAR(100) NOT NULL,
	email		 VARCHAR(150) UNIQUE,
	phone_number VARCHAR(15) CHECK (phone_number ~ '^[0-9]+$'),
	birth_date	 DATE,
	is_active	 BOOLEAN DEFAULT TRUE,
	created_at	 TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMPr
);

-- 2. Giải thích vì sao phone_number nên là VARCHAR thay vì INT
-- + Kích thước và giới hạn của INT:
-- INT tối đa là khoảng 2 tỷ (10 chữ số).
-- Trong khi số điện thoại có thể dài hơn 10–15 chữ số (ví dụ số quốc tế).
-- + Tính nhất quán:
-- Có thể so sánh chuỗi để kiểm tra trùng lặp mà không lo mất 0 đầu.

-- 3. Thêm 1 dòng dữ liệu hợp lệ; thử chèn 1 dòng sai định dạng phone_number để quan sát lỗi
-- Hợp lệ
INSERT INTO customers2 (full_name, email, phone_number, birth_date)
VALUES ('Lưu Ngọc Yến Như', 'nhuw@gmail.com', '0912345670', '2004-10-08');

-- Không hợp lệ: sẽ vi phạm CHECK do có dấu '-'
INSERT INTO customers2 (full_name, email, phone_number)
VALUES ('Sai Phone Number', 'bad@example.com', '09-123-456');
-- Lỗi: 
-- ERROR:  new row for relation "customers2" violates check constraint "customers2_phone_number_check"
-- Failing row contains (2, Sai Phone Number, bad@example.com, 09-123-456, null, t, 2025-09-05 15:15:11.313777+07). 

-- B) Khởi tạo bảng có ENUM & khóa ngoại
-- Yêu cầu:
-- 1. Tạo TYPE order_status AS ENUM ('pending','completed','canceled')
CREATE TYPE ORDER_STATUS AS ENUM('pending','completed','canceled')
-- DROP TYPE IF EXISTS order_status;

-- 2. Tạo bảng orders2 có FK đến customers2(customer_id)
-- - order_id: khóa chính
-- - order_date: kiểu TIMESTAMPTZ với giá trị mặc định là ngày giờ hiện tại theo hệ thống
-- - total_amount: kiểu NUMERIC(12,2) có ràng buộc CHECK >= 0
-- - status: dùng kiểu ENUM, với giá trị mặc định là 'pending'

CREATE TABLE orders2 (
	order_id SERIAL PRIMARY KEY,
	customer_id INT NOT NULL,
	order_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
	total_amount NUMERIC(12, 2) CHECK (total_amount >= 0),
	status order_status default 'pending',
	CONSTRAINT fk_customers2 FOREIGN KEY (customer_id) REFERENCES customers2(customer_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);
-- select * from pg_enum
select * from pg_type where oid = 17042
-- C) INSERT – thêm dữ liệu & kiểm tra ràng buộc
-- Yêu cầu:
-- 1. Thêm 3 khách hàng mới với dữ liệu hợp lệ vào customers2
INSERT INTO customers2 (full_name, email, phone_number, birth_date)
VALUES 	('Lưu Ngọc Yến Như', 'ynhu@gmail.com', '0912345670', '2004-10-08'),
		('Lưu Yến Ngọc', 'ngoc@gmail.com', '0912346670', '2004-12-08'),
		('Nguyễn Văn An', 'an@gmail.com', '0912445670', '2004-10-05')

-- 2. Thêm 3 khách hàng tiếp theo vào customers2 với dữ liệu:
-- - Một khách không có email (để kiểm tra cột email có thể NULL)
INSERT INTO customers2 (full_name, phone_number, birth_date)
VALUES 	('Email có thể null', '0966345670', '2004-10-08')
-- ok

-- - Một khách có phone_number chứa khoảng trắng (ví dụ '0912 345 678') để quan sát hành vi với CHECK
INSERT INTO customers2 (full_name, email, phone_number, birth_date)
VALUES 	('Phone number chứa khoảng trắng', 'yu@gmail.com', '0912 345 678', '2004-10-08')

-- ERROR:  new row for relation "customers2" violates check constraint "customers2_phone_number_check"
-- Failing row contains (8, Phone number chứa khoảng trắng, yu@gmail.com, 0912 345 678, 2004-10-08, t, 2025-09-05 15:31:20.090093+07). 

-- - Một khách có birth_date để trống
INSERT INTO customers2 (full_name, email, phone_number, birth_date)
VALUES 	('Birth_date để trống', 'birthdate3@gmail.com', '0909345670', null);

-- ERROR:  invalid input syntax for type date: " "
-- LINE 3: ...h_date để trống', 'birthdate@gmail.com', '0909345670', ' ');

-- 3. Thêm 4 đơn hàng (ít nhất 1 dòng status mặc định, 1 dòng completed)
-- - Hai đơn hàng cho cùng một khách hàng với các total_amount khác nhau
INSERT INTO orders2 (customer_id, total_amount, status)
VALUES 
    (1, 150000.00, 'canceled'),
    (1, 250000.00, 'completed');

	select * from customers2
-- Thêm 3 đơn hàng mới
INSERT INTO orders2 (customer_id, total_amount, status)
VALUES 
    (4, 550000.00, 'completed'),
	(4, 150000.00, 'completed'),
    (5, 350000.00, default);

-- - Một đơn hàng có status = 'completed'
-- - Một đơn hàng không chỉ định status (để dùng giá trị mặc định)
-- 4. Thử thêm 1 đơn hàng với total_amount là số âm để quan sát lỗi từ CHECK
INSERT INTO orders2 (customer_id, total_amount, status)
VALUES 
    (4, -550000.00, 'completed')

-- ERROR:  new row for relation "orders2" violates check constraint "orders2_total_amount_check"
-- Failing row contains (9, 4, 2025-09-05 15:42:38.153308+07, -550000.00, completed). 

-- 5. Thêm một giá trị ENUM mới, ví dụ 'returned', và chèn ít nhất 1 đơn hàng ở trạng thái này
ALTER TYPE order_status ADD VALUE 'returned' AFTER 'pending';

INSERT INTO orders2 (customer_id, total_amount, status)
VALUES 
    (7, 550000.00, 'returned')

-- 6. Xóa giá trị ENUM 'returned'
SELECT * FROM pg_enum;

-- 6.1. Tạo enum mới ko chứa 'returned'
CREATE TYPE order_status_new AS ENUM ('pending','completed','canceled');

-- 6.2. Update những row nào có status là 'returned' sang 'canceled'
UPDATE orders2
SET status = 'canceled'
WHERE status = 'returned';

-- 6.3. Bỏ default cũ
ALTER TABLE orders2 ALTER COLUMN status DROP DEFAULT;

-- 6.4. Đổi type sang ENUM mới
ALTER TABLE orders2 
  ALTER COLUMN status TYPE order_status_new 
  USING status::text::order_status_new;

-- 6.5. Set default lại (giữ 'pending')
ALTER TABLE orders2 ALTER COLUMN status SET DEFAULT 'pending';

-- 6.6. Xóa enum cũ
DROP TYPE order_status;

SELECT * FROM CUSTOMERS2
-- D) UPDATE – cập nhật dữ liệu
-- Yêu cầu:
-- 1. Đặt is_active = FALSE cho tất cả khách hàng có birth_date IS NULL
UPDATE CUSTOMERS2
SET IS_ACTIVE = FALSE
WHERE BIRTH_DATE IS NULL

-- 2. Với các khách hàng có email kết thúc bằng @example.com, thêm tiền tố [NEW] vào trước full_name
-- data test
UPDATE CUSTOMERS2
SET email = 'ngoc@yahoo.com'
WHERE customer_id = 5

UPDATE customers2
SET full_name = CONCAT('[NEW]', full_name)
WHERE email LIKE '%@yahoo.com'

-- 3. Xóa tiền tố [NEW] vừa thêm, đảm bảo:
-- - Chỉ xóa ở đầu chuỗi
-- - Không ảnh hưởng tới các vị trí khác nếu tình cờ xuất hiện trong tên
UPDATE customers2
SET full_name = REGEXP_REPLACE(full_name, '^\[NEW\]\s*', '', '');

UPDATE customers2
SET full_name = REPLACE(full_name, '[NEW] ', '')

-- 4. Cập nhật status = 'canceled' cho tất cả đơn hàng của các khách hàng có is_active = FALSE
UPDATE orders2 o
SET status = 'canceled'
FROM customers2 c
WHERE o.customer_id = c.customer_id
  AND c.is_active = false

-- 5. Thử cập nhật email của 2 khách hàng khác nhau thành cùng một giá trị để quan sát lỗi ràng buộc UNIQUE
UPDATE customers2
SET email = 'emailtrung@yahoo.com'
WHERE customer_id in (1, 5)
select * from customers2
-- ERROR:  duplicate key value violates unique constraint "customers2_email_key"
-- Key (email)=(emailtrung@yahoo.com) already exists. 

-- E) DELETE – xóa dữ liệu
-- Yêu cầu:
-- 1. Xóa các đơn hàng có total_amount lớn hơn 900000 và status khác 'canceled'
DELETE FROM orders2
WHERE total_amount > 900000 and status <> 'canceled'

-- 2. Xóa tất cả đơn hàng thuộc các khách hàng không có birth_date (dùng điều kiện trên bảng customers2)
DELETE FROM orders2 o
USING customers2 c
WHERE o.customer_id = c.customer_id
  AND c.birth_date IS NULL
  
-- 3. Xóa các khách hàng có email IS NULL và is_active = FALSE
DELETE FROM customers2
WHERE email is null and is_active = FALSE

-- F) Transaction – BEGIN/COMMIT/ROLLBACK & SAVEPOINT
-- Yêu cầu:
-- 1. Mở một transaction:
-- - Cập nhật status thành 'completed' cho tất cả đơn hàng của khách hàng customer_id = 1
-- - Thực hiện SELECT kiểm tra
-- - ROLLBACK và xác nhận dữ liệu quay về như cũ
begin;

update orders2
set status = 'pending'
where customer_id = 4;

select * from orders2 where customer_id = 4;

rollback;
show search_path
set search_path to ops, lab, public
-- 2. Mở transaction khác:
BEGIN;

-- - Đặt is_active = FALSE cho những khách hàng có không đơn hàng nào (sử dụng NOT EXISTS)
UPDATE customers2 c
SET is_active = FALSE
WHERE NOT EXISTS (
    SELECT 1
    FROM orders2 o
    WHERE o.customer_id = c.customer_id
);

select * from customers2
-- - Tạo SAVEPOINT
SAVEPOINT before_delete;

-- - Thử DELETE một khách hàng vẫn còn đơn hàng, rồi rollback lại về SAVEPOINT
DELETE FROM customers2
WHERE customer_id = (
    SELECT customer_id
    FROM orders2
    LIMIT 1
);

ROLLBACK TO SAVEPOINT before_delete;

-- - COMMIT phần cập nhật hợp lệ còn lại
COMMIT;

-- G) Schema – tạo/move bảng & search_path
-- Yêu cầu:
-- 1. Tạo schema mới tên ops
CREATE SCHEMA IF NOT EXISTS ops;

-- 2. Di chuyển bảng orders2 sang schema ops (sử dụng ALTER TABLE … SET SCHEMA …)
alter table lab.orders2 set schema ops;

-- 3. Cấu hình search_path cho schema mới này
SET search_path TO ops, labs, public;

-- H) ALTER TABLE – thay đổi cấu trúc
-- Yêu cầu:
-- 1. Trên customers2:
-- - Thêm cột updated_at TIMESTAMPTZ với giá trị mặc định là thời điểm hiện tại
ALTER TABLE lab.customers2 
ADD COLUMN updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP

-- - Thêm ràng buộc CHECK đảm bảo full_name không rỗng sau khi TRIM()
ALTER TABLE lab.customers2
ADD CONSTRAINT full_name_not_empty
CHECK (TRIM(full_name) <> '')

-- 2. Đặt giá trị mặc định mới cho orders2.status là 'completed'
ALTER TABLE orders2
ALTER COLUMN status SET DEFAULT 'completed'

-- 3. Tạo INDEX trên orders2(customer_id, order_date DESC)
CREATE INDEX idx_orders_customer_id_orders_date ON orders2(customer_id, order_date DESC)

-- 4. Đổi kiểu email của customers2 từ VARCHAR(150) sang VARCHAR(200)
ALTER TABLE lab.customers2
ALTER COLUMN email TYPE VARCHAR(200)

-- 3.3. Bài tập nâng cao	
-- 	3.4.1. Bài tập lý thuyết
-- 	C1. Hãy tìm hiểu cách sử dụng từ khóa CONCURRENTLY khi tạo Index
-- 	C2. Hãy tìm hiểu để phân biệt Composite & Covering Index
-- 	C3. Hãy tìm hiểu sự khác biệt Index Scan hoặc Index Only Scan

