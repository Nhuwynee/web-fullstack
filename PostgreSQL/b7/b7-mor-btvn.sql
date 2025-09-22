CREATE SCHEMA IF NOT EXISTS demo;
SET search_path TO demo, public;

show search_path

CREATE TABLE hoc_vien (
    id SERIAL PRIMARY KEY,
    ho_ten VARCHAR
) TABLESPACE demo_tz;

CREATE TABLE giao_vien (
    id SERIAL PRIMARY KEY,
    ho_ten VARCHAR(150) NOT NULL
) TABLESPACE demo_tz;

CREATE TABLE khoa_hoc (
    id SERIAL PRIMARY KEY,
    ten VARCHAR
) TABLESPACE demo_tz;

CREATE TABLE lop_hoc (
    id SERIAL PRIMARY KEY,
    ten VARCHAR,
    khoa_hoc_id INT NOT NULL REFERENCES khoa_hoc(id),
    giao_vien_id INT NOT NULL REFERENCES giao_vien(id),
    CONSTRAINT uq_lop_khoa UNIQUE (id, khoa_hoc_id)
) TABLESPACE demo_tz;

CREATE TABLE dang_ky_hoc (
    hoc_vien_id INT NOT NULL REFERENCES hoc_vien(id),
    khoa_hoc_id INT NOT NULL REFERENCES khoa_hoc(id),
    lop_hoc_id INT NULL,
    ngay_dang_ky DATE,
    trang_thai varchar,
    CONSTRAINT uq_hv_lop UNIQUE (hoc_vien_id, lop_hoc_id),
    CONSTRAINT uq_hv_kh UNIQUE (hoc_vien_id, khoa_hoc_id),
    CONSTRAINT fk_dangky_lop_khoa FOREIGN KEY (lop_hoc_id, khoa_hoc_id) REFERENCES lop_hoc(id, khoa_hoc_id)
) TABLESPACE demo_tz;

---

BEGIN;

-- Reset dữ liệu
TRUNCATE TABLE dang_ky_hoc, lop_hoc, hoc_vien, giao_vien, khoa_hoc
RESTART IDENTITY CASCADE

-- 1) 100 khóa học
INSERT INTO demo.khoa_hoc (ten)
SELECT 'Khoa ' || i
FROM generate_series(1, 100) AS g(i);

-- 2) 5000 giáo viên
INSERT INTO demo.giao_vien (ho_ten)
SELECT 'Giao Vien ' || i
FROM generate_series(1, 5000) AS g(i);

-- 3) 20k lớp học: mỗi khóa 200 lớp
INSERT INTO demo.lop_hoc (ten, khoa_hoc_id, giao_vien_id)
SELECT 'Lop KH' || kh || '-' || n,
       kh,
       ((kh*200 + n) % 5000) + 1
FROM generate_series(1, 100) AS kh
CROSS JOIN generate_series(1, 200) AS n;

-- 4) 1 triệu học viên
INSERT INTO demo.hoc_vien (ho_ten)
SELECT 'Hoc Vien ' || i
FROM generate_series(1, 1000000) AS g(i);

-- 5) Tạo mảng lớp cho mỗi khóa
WITH lop_map AS (
    SELECT khoa_hoc_id, array_agg(id) AS lop_ids
    FROM demo.lop_hoc
    GROUP BY khoa_hoc_id
)
INSERT INTO demo.dang_ky_hoc(hoc_vien_id, khoa_hoc_id, lop_hoc_id, ngay_dang_ky, trang_thai)
SELECT 
    hv.id,
    -- Random khóa học dựa trên id học viên → mỗi học viên khác nhau
    1 + (abs(hashtextextended(hv.id::text, 0)) % 100) AS kh_id,
    CASE WHEN random() < 0.25 THEN NULL
         ELSE (
            -- Lấy mảng lớp từ lop_map tương ứng khóa học
            (SELECT lop_ids[1 + floor(random()*array_length(lop_ids,1))::int]
             FROM lop_map lm 
             WHERE lm.khoa_hoc_id = 1 + (abs(hashtextextended(hv.id::text, 0)) % 100))
         )
    END,
    DATE '2024-01-01' + (floor(random()*600)::int),
    (ARRAY['ACTIVE','CANCELLED','COMPLETED','ON_HOLD'])[1 + floor(random()*4)::int]
FROM demo.hoc_vien hv;

COMMIT;

-- 4. Tạo index để tối ưu truy vấn cho các nghiệp vụ sau:		
-- - Tìm kiếm tất cả các lớp thuộc một khóa học cụ thể
EXPLAIN ANALYZE
SELECT * FROM lop_hoc WHERE khoa_hoc_id = 1;
CREATE INDEX idx_lophoc_khoahoc ON lop_hoc(khoa_hoc_id);

-- - Tra cứu các đăng ký của một học viên	
EXPLAIN ANALYZE
SELECT ngay_dang_ky, trang_thai 
FROM dang_ky_hoc 
WHERE hoc_vien_id = 4
ORDER BY ngay_dang_ky DESC;

CREATE INDEX idx_dangkyhoc_hv_ngay_include 
    ON dang_ky_hoc(hoc_vien_id, ngay_dang_ky DESC)
    INCLUDE (trang_thai);
	
-- - Báo cáo liên quan đến số lượng học viên trên mỗi khóa học	
EXPLAIN ANALYZE
SELECT khoa_hoc_id, COUNT(*) 
FROM dang_ky_hoc 
GROUP BY khoa_hoc_id;

CREATE INDEX idx_dangkyhoc_khoahoc ON dang_ky_hoc(khoa_hoc_id);

CREATE MATERIALIZED VIEW mv_hocvien_khoahoc AS
SELECT khoa_hoc_id, COUNT(*) AS so_luong
FROM dang_ky_hoc
GROUP BY khoa_hoc_id;

REFRESH MATERIALIZED VIEW mv_hocvien_khoahoc;

EXPLAIN ANALYZE
SELECT khoa_hoc_id, COUNT(*) 
FROM mv_hocvien_khoahoc 
GROUP BY khoa_hoc_id;

-- - Tra cứu thông tin theo lớp học	
EXPLAIN ANALYZE
SELECT * FROM lop_hoc WHERE id = 1;

EXPLAIN ANALYZE
SELECT * FROM dang_ky_hoc WHERE lop_hoc_id = 1;

CREATE INDEX idx_dangkyhoc_lophoc ON dang_ky_hoc(lop_hoc_id);

-- - Đếm đăng ký theo khóa học & trạng thái
EXPLAIN ANALYZE
SELECT khoa_hoc_id, trang_thai, COUNT(*) 
FROM dang_ky_hoc 
GROUP BY khoa_hoc_id, trang_thai;

CREATE INDEX idx_dangkyhoc_khoahoc_trangthai 
    ON dang_ky_hoc(khoa_hoc_id, trang_thai);

-- - Tìm lớp theo khóa học để liệt kê id lớp & id giáo viên	
EXPLAIN ANALYZE
SELECT id, giao_vien_id FROM lop_hoc WHERE khoa_hoc_id = 1;

CREATE INDEX idx_lophoc_khoahoc
    ON lop_hoc(khoa_hoc_id);

-- - Lịch sử đăng ký của một học viên theo thời gian
EXPLAIN ANALYZE
SELECT * FROM dang_ky_hoc 
WHERE hoc_vien_id = 1
ORDER BY ngay_dang_ky DESC;

CREATE INDEX idx_dangkyhoc_hocvien_ngaydangky 
    ON DangKyHoc(hoc_vien_id, ngay_dang_ky DESC);






