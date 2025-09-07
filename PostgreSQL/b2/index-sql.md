set enable_bitmapscan = off;
set enable_indexscan = off;

---

# 📘 Giải thích chi tiết các loại Index trong PostgreSQL

---

## 1. **B-tree Index (Balanced Tree)**

🔹 **Khái niệm**

* Đây là loại index mặc định, dựa trên cấu trúc cây cân bằng (balanced binary tree).
* Khi insert, update, delete, PostgreSQL tự cân bằng lại cây.

🔹 **Vì sao có**

* SQL cần tìm kiếm/sắp xếp theo `=`, `<`, `>`, `BETWEEN`, `ORDER BY` nhanh chóng.
* B-tree đảm bảo tìm kiếm trong **O(log n)** thay vì quét toàn bảng (**O(n)**).

🔹 **Cách hoạt động**

* Cây phân nhánh, mỗi node lưu một khoảng giá trị.
* PostgreSQL chỉ duyệt nhánh phù hợp thay vì toàn bộ.

🔹 **Ví dụ**

```sql
-- Index mặc định
CREATE INDEX idx_orders_created ON orders(created_at);

-- Query được hưởng lợi:
SELECT * FROM orders WHERE created_at > '2023-01-01';
```

📌 **Khi dùng**: 99% trường hợp thông thường (`=, <, >, ORDER BY`).

---

## 2. **Hash Index**

🔹 **Khái niệm**

* Dựa trên **hàm băm (hash function)**, ánh xạ giá trị thành hash code.

🔹 **Vì sao có**

* Một số truy vấn chỉ cần **so sánh bằng (`=`)**, không quan tâm `<` hay `ORDER BY`.
* Hash index cực nhanh trong lookup bằng key.

🔹 **Cách hoạt động**

* PostgreSQL tính hash của giá trị, tìm bucket tương ứng.
* Không thể dùng cho `<`, `>`, vì hash không giữ thứ tự.

🔹 **Ví dụ**

```sql
CREATE INDEX idx_orders_ordernum_hash 
ON orders USING hash(order_number);

SELECT * FROM orders WHERE order_number = 'INV12345';
```

📌 **Khi dùng**: lookup bằng `=` trên giá trị rời rạc, nhiều bản ghi.

---

## 3. **GIN (Generalized Inverted Index)**

🔹 **Khái niệm**

* Là **inverted index** (chỉ mục đảo), thường dùng trong full-text search, JSON, mảng.
* Thay vì index cả dòng → GIN index từng **token / phần tử**.

🔹 **Vì sao có**

* Text search, JSON, array không phù hợp với B-tree.
* GIN giúp tìm phần tử con trong dữ liệu phức hợp.

🔹 **Cách hoạt động**

* Lưu map: **token → danh sách row chứa token đó**.
* Ví dụ từ khóa "cat" → các document chứa "cat".

🔹 **Ví dụ**

```sql
-- Full-text search
CREATE INDEX idx_docs_content 
ON documents USING gin(to_tsvector('english', content));

SELECT * FROM documents WHERE to_tsvector('english', content) @@ to_tsquery('dog & cat');

-- JSON
CREATE INDEX idx_json_data ON users USING gin(data jsonb_path_ops);

SELECT * FROM users WHERE data @> '{"role": "admin"}';
```

📌 **Khi dùng**: full-text search, JSONB, array.

---

## 4. **GiST (Generalized Search Tree)**

🔹 **Khái niệm**

* Cấu trúc index tổng quát hơn, hỗ trợ dữ liệu có "khoảng" (ranges, hình học, không gian).
* Là nền tảng cho nhiều extension (PostGIS).

🔹 **Vì sao có**

* Tìm kiếm dữ liệu **gần đúng, khoảng cách, hình học** không phù hợp với B-tree.
* Ví dụ: "tìm tất cả điểm trong bán kính 5km".

🔹 **Cách hoạt động**

* Mỗi node lưu **một vùng bao phủ (bounding box)** → chỉ cần check vùng, không cần check từng row.

🔹 **Ví dụ**

```sql
-- Index trên dữ liệu hình học (PostGIS)
CREATE INDEX idx_places_geom ON places USING gist(location);

-- Tìm các địa điểm trong bán kính 10km
SELECT * FROM places
WHERE ST_DWithin(location, ST_MakePoint(105.85, 21.03)::geography, 10000);
```

📌 **Khi dùng**: dữ liệu không gian, range, nearest neighbor search.

---

## 5. **SP-GiST (Space-partitioned GiST)**

🔹 **Khái niệm**

* Biến thể của GiST, nhưng **phân vùng không gian theo cây (quad-tree, k-d tree, radix tree)**.

🔹 **Vì sao có**

* Một số loại dữ liệu phân vùng không gian không phù hợp với GiST.
* Ví dụ: IP range, prefix matching.

🔹 **Cách hoạt động**

* Thay vì "bao phủ", nó **chia không gian thành vùng rời rạc**.

🔹 **Ví dụ**

```sql
-- Index trên IP range
CREATE INDEX idx_iprange ON users USING spgist(ip_range);

SELECT * FROM users WHERE ip_range >>= inet '192.168.1.0/24';
```

📌 **Khi dùng**: dữ liệu phân tầng (IP, prefix, cây phân loại).

---

## 6. **BRIN (Block Range Index)**

🔹 **Khái niệm**

* Index cực nhỏ, lưu **metadata của block dữ liệu** thay vì từng row.
* Ví dụ: block 1 có created\_at từ 2023-01-01 đến 2023-01-31.

🔹 **Vì sao có**

* Bảng cực lớn, dữ liệu tuần tự (log, sensor).
* Không thể dùng B-tree vì index sẽ quá nặng.

🔹 **Cách hoạt động**

* PostgreSQL chỉ đọc các block phù hợp với query.

🔹 **Ví dụ**

```sql
CREATE INDEX idx_orders_brin 
ON orders USING brin(created_at);

SELECT * FROM orders WHERE created_at BETWEEN '2023-01-01' AND '2023-01-31';
```

📌 **Khi dùng**: bảng append-only, dữ liệu thời gian, logs.

---

## 7. **Partial Index**

🔹 **Khái niệm**

* Index chỉ áp dụng cho một **phần dữ liệu** thỏa điều kiện `WHERE`.

🔹 **Vì sao có**

* Không cần index toàn bộ bảng (tốn dung lượng, update chậm).
* Ví dụ: chỉ quan tâm đơn hàng 30 ngày gần nhất.

🔹 **Cách hoạt động**

* PostgreSQL chỉ index row thỏa điều kiện → nhỏ hơn, nhanh hơn.

🔹 **Ví dụ**

```sql
CREATE INDEX idx_orders_recent
ON orders(created_at)
WHERE created_at > now() - interval '30 days';

SELECT * FROM orders WHERE created_at > now() - interval '30 days';
```

📌 **Khi dùng**: dữ liệu có phân loại rõ ràng, query thường xuyên trên một subset.

---

## 8. **Covering Index (INCLUDE)**

🔹 **Khái niệm**

* B-tree index + cột phụ được **INCLUDE**.
* Giúp query không cần "quay lại bảng" để lấy cột khác.

🔹 **Vì sao có**

* B-tree chỉ lưu cột trong key → nếu `SELECT` thêm cột khác, PostgreSQL vẫn phải truy cập bảng.
* Covering index giúp tránh "table lookup".

🔹 **Cách hoạt động**

* Các cột `INCLUDE` không tham gia sắp xếp, chỉ để "cover" SELECT.

🔹 **Ví dụ**

```sql
CREATE INDEX idx_orders_covering
ON orders(created_at DESC)
INCLUDE (order_number, total_amount);

-- Query không cần truy vấn bảng nữa
SELECT created_at, order_number, total_amount
FROM orders
WHERE created_at > '2023-01-01';
```

📌 **Khi dùng**: SELECT nhiều cột cùng với cột filter/sort.

---

# 📊 Bảng so sánh nhanh

| Loại Index   | Ưu điểm                                       | Nhược điểm                           | Khi nào dùng                           |
| ------------ | --------------------------------------------- | ------------------------------------ | -------------------------------------- |
| **B-tree**   | Đa năng, mặc định, hỗ trợ `=, <, >, ORDER BY` | Index to                             | Hầu hết query cơ bản                   |
| **Hash**     | Nhanh cho `=`                                 | Không hỗ trợ `<, >, ORDER BY`        | Lookup bằng key                        |
| **GIN**      | Full-text, JSON, array                        | Nặng khi update                      | Tìm kiếm text, JSON                    |
| **GiST**     | Range, hình học, không gian                   | Cài đặt phức tạp                     | Dữ liệu spatial (PostGIS)              |
| **SP-GiST**  | Dữ liệu phân tầng, IP                         | Hạn chế loại dữ liệu                 | IP range, prefix                       |
| **BRIN**     | Nhẹ, cực nhanh cho bảng lớn tuần tự           | Chỉ chính xác với dữ liệu có trật tự | Logs, sensor, time-series              |
| **Partial**  | Nhỏ, nhanh                                    | Chỉ áp dụng subset                   | Query trên tập dữ liệu nhỏ thường dùng |
| **Covering** | Tránh quay lại bảng                           | Index to hơn                         | SELECT nhiều cột phụ                   |

---


---

# 🔎 Lý thuyết & Cách áp dụng các loại index trong PostgreSQL

---

## 1. **B-Tree Index (mặc định)**

* **Cơ chế**: PostgreSQL tổ chức index theo dạng cây cân bằng (balanced tree). Khi tìm giá trị, nó duyệt cây giống như từ điển → O(logN).
* **Ứng dụng**: tốt cho so sánh `=`, `<`, `>`, `<=`, `BETWEEN`, `ORDER BY`.
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_orders_date ON orders(created_at);
  SELECT * FROM orders WHERE created_at >= '2025-01-01' ORDER BY created_at;
  ```
* **Vì sao**: vì B-tree giữ **thứ tự giá trị** → tối ưu lọc theo range & sắp xếp.
* **Không hợp**: tìm kiếm chuỗi với `%abc%` (vì không tận dụng được thứ tự).

---

## 2. **Covering Index (B-tree + INCLUDE)**

* **Cơ chế**: ngoài các cột chính được index, PostgreSQL lưu thêm cột khác trong **leaf node**.
* **Ứng dụng**: khi bạn `SELECT` vài cột phụ (không nằm trong điều kiện WHERE/ORDER BY).
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_orders_cover 
  ON orders(created_at DESC)
  INCLUDE (order_number, total_amount);

  SELECT order_number, total_amount
  FROM orders
  WHERE created_at > now() - interval '30 days'
  ORDER BY created_at DESC;
  ```
* **Vì sao**: giúp query dùng **Index Only Scan** → không cần quay lại bảng.
* **Khi dùng**: query đọc nhiều nhưng chỉ cần ít cột.

---

## 3. **Partial Index**

* **Cơ chế**: chỉ index một phần dữ liệu thỏa `WHERE`.
* **Ứng dụng**: khi bảng rất lớn nhưng thường chỉ truy vấn subset dữ liệu (ví dụ đơn hàng còn “PENDING”).
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_orders_pending 
  ON orders(created_at)
  WHERE status = 'PENDING';
  ```
* **Vì sao**: tiết kiệm dung lượng, tăng tốc độ vì loại bỏ dữ liệu không cần thiết.
* **Lưu ý**: chỉ hiệu quả nếu query có cùng điều kiện `WHERE`.

---

## 4. **BRIN (Block Range Index)**

* **Cơ chế**: thay vì index từng dòng, BRIN chỉ lưu **min/max giá trị theo block (8KB)**.
* **Ứng dụng**: bảng **rất lớn**, dữ liệu có tính tuần tự (logs, sensor, thời gian).
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_logs_brin 
  ON logs USING brin(created_at);
  ```
* **Vì sao**: index cực nhỏ (MB cho hàng trăm GB dữ liệu), nhưng vẫn lọc nhanh.
* **Không hợp**: dữ liệu ngẫu nhiên (non-sequential).

---

## 5. **GIN (Generalized Inverted Index)**

* **Cơ chế**: tạo index cho từng **token** trong mảng, JSON, full-text → giống inverted index trong search engine.
* **Ứng dụng**: full-text search, JSONB, array.
* **Ví dụ**:

  ```sql
  -- Full-text
  CREATE INDEX idx_articles_text
  ON articles USING gin(to_tsvector('english', content));

  SELECT * FROM articles 
  WHERE to_tsvector('english', content) @@ to_tsquery('makeup & tutorial');

  -- JSONB
  CREATE INDEX idx_orders_json
  ON orders USING gin(metadata);

  SELECT * FROM orders WHERE metadata @> '{"payment": "card"}';
  ```
* **Vì sao**: giúp tìm kiếm từ khóa nhanh, hoặc lọc JSON mà không phải scan toàn bộ.
* **Không hợp**: query dạng `ORDER BY`, `range`.

---

## 6. **GiST (Generalized Search Tree)**

* **Cơ chế**: cấu trúc cây “search tree” tổng quát, có thể chứa nhiều loại dữ liệu đặc biệt (geo, range).
* **Ứng dụng**: dữ liệu không gian (PostGIS), range type.
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_places_geom ON places USING gist(location);

  SELECT * FROM places 
  WHERE ST_DWithin(location, ST_MakePoint(105.85, 21.03), 1000);
  ```
* **Vì sao**: GIN mạnh cho search text, GiST mạnh cho **toán học hình học & phạm vi (range)**.

---

## 7. **SP-GiST (Space-Partitioned GiST)**

* **Cơ chế**: thay vì cây cân bằng, SP-GiST phân chia dữ liệu theo **không gian** (prefix tree, quad-tree).
* **Ứng dụng**: dữ liệu phân vùng (IP address, text prefix).
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_users_ip 
  ON users USING spgist(ip_address inet_ops);

  SELECT * FROM users WHERE ip_address << '192.168.0.0/24';
  ```
* **Vì sao**: tối ưu lookup theo “phân vùng không gian”.

---

## 8. **Hash Index**

* **Cơ chế**: dùng hàm băm để ánh xạ giá trị → lookup O(1).
* **Ứng dụng**: chỉ phù hợp với `=` (tìm chính xác).
* **Ví dụ**:

  ```sql
  CREATE INDEX idx_orders_hash 
  ON orders USING hash(order_number);

  SELECT * FROM orders WHERE order_number = 'ORD123';
  ```
* **Vì sao**: rất nhanh cho lookup bằng khóa chính xác.
* **Không hợp**: không hỗ trợ `<, >, ORDER BY`.

---

# 📊 Bảng so sánh nhanh

| Loại index   | Dùng cho                       | Ưu điểm                 | Nhược điểm                       |
| ------------ | ------------------------------ | ----------------------- | -------------------------------- |
| **B-tree**   | =, <, >, BETWEEN, ORDER BY     | Linh hoạt, mặc định     | Không tối ưu cho text search     |
| **Covering** | SELECT thêm cột                | Tránh quay lại bảng     | Tốn thêm dung lượng              |
| **Partial**  | Subset dữ liệu                 | Nhỏ gọn, nhanh          | Chỉ hợp với query đúng điều kiện |
| **BRIN**     | Bảng cực lớn, tuần tự          | Nhẹ, tiết kiệm          | Không chính xác cao              |
| **GIN**      | Full-text, JSON, array         | Search cực nhanh        | Không hỗ trợ ORDER BY            |
| **GiST**     | Geo, range                     | Hỗ trợ dữ liệu phức tạp | Chậm hơn GIN cho text            |
| **SP-GiST**  | Dữ liệu phân vùng (IP, prefix) | Lookup đặc thù nhanh    | Ít phổ biến                      |
| **Hash**     | `=` lookup                     | Nhanh, đơn giản         | Không hỗ trợ range/sort          |

---

💡 **Tóm lại cách áp dụng**

* Cột số/ngày → **B-tree** (default).
* SELECT thêm cột phụ → **Covering index**.
* Truy vấn subset dữ liệu cố định → **Partial index**.
* Bảng log khổng lồ → **BRIN**.
* Search từ khóa/JSON → **GIN**.
* Dữ liệu không gian/range → **GiST**.
* Dữ liệu IP, prefix → **SP-GiST**.
* Lookup chính xác, đơn giản → **Hash**.

---

👌 Mình sẽ xây dựng một **chiến lược index tối ưu cho bảng `orders`** trong hệ thống bán hàng (theo kiểu thực tế).
Mình vừa giải thích lý thuyết, giờ sẽ **áp dụng trực tiếp** để bạn thấy vì sao cần loại index nào.

---

# 📦 Giả sử bảng `orders`

```sql
CREATE TABLE orders (
    order_id       BIGSERIAL PRIMARY KEY,
    order_number   VARCHAR(50) UNIQUE,
    customer_id    BIGINT NOT NULL,
    status         VARCHAR(20),           -- PENDING, SHIPPED, DELIVERED, CANCELED
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP,
    total_amount   NUMERIC(12,2),
    metadata       JSONB                   -- lưu thêm thông tin linh hoạt
);
```

---

# 🛠️ Các loại query thường gặp

1. **Tra cứu đơn hàng mới nhất**

   ```sql
   SELECT * FROM orders 
   WHERE status = 'DELIVERED'
   ORDER BY created_at DESC
   LIMIT 20;
   ```

2. **Tìm đơn hàng theo số order\_number (tra cứu nhanh)**

   ```sql
   SELECT * FROM orders WHERE order_number = 'ORD123456';
   ```

3. **Thống kê doanh thu theo ngày**

   ```sql
   SELECT date(created_at), SUM(total_amount) 
   FROM orders 
   GROUP BY date(created_at);
   ```

4. **Danh sách đơn hàng của một khách hàng**

   ```sql
   SELECT * FROM orders 
   WHERE customer_id = 1001 
   ORDER BY created_at DESC;
   ```

5. **Tìm kiếm theo metadata (ví dụ: phương thức thanh toán)**

   ```sql
   SELECT * FROM orders 
   WHERE metadata @> '{"payment_method":"credit_card"}';
   ```

6. **Query báo cáo log nhiều năm (big data)**

   ```sql
   SELECT count(*) FROM orders 
   WHERE created_at >= '2020-01-01' AND created_at < '2021-01-01';
   ```

---

# 📊 Chiến lược Index Tối Ưu

### 1. **Covering Index cho đơn hàng gần đây**

```sql
CREATE INDEX idx_orders_recent 
ON orders (created_at DESC) 
INCLUDE (order_number, total_amount, status);
```

👉 Hỗ trợ query **top N đơn hàng** mà không cần quay lại bảng.

---

### 2. **Unique B-tree cho order\_number**

```sql
CREATE UNIQUE INDEX idx_orders_ordernumber 
ON orders(order_number);
```

👉 Tìm đơn hàng theo số → **truy xuất O(logN)**.
(Postgres đã tạo unique index vì `UNIQUE`, nhưng nếu bạn muốn rõ ràng thì viết riêng).

---

### 3. **B-tree cho customer\_id**

```sql
CREATE INDEX idx_orders_customer 
ON orders(customer_id, created_at DESC);
```

👉 Tối ưu khi lấy danh sách đơn hàng theo **khách hàng** (thường kèm sort theo ngày).

---

### 4. **Partial Index cho status**

```sql
CREATE INDEX idx_orders_pending 
ON orders(created_at)
WHERE status = 'PENDING';
```

👉 Thường xuyên lọc đơn hàng “chưa xử lý” → index nhỏ, rất nhanh.

---

### 5. **GIN Index cho metadata (JSONB)**

```sql
CREATE INDEX idx_orders_metadata 
ON orders USING gin(metadata);
```

👉 Tìm kiếm linh hoạt trong JSONB (`payment_method`, `shipping_info`, …).

---

### 6. **BRIN Index cho created\_at (Big Data)**

```sql
CREATE INDEX idx_orders_brin_date 
ON orders USING brin(created_at);
```

👉 Dùng cho báo cáo dữ liệu **hàng trăm triệu dòng** → index cực nhỏ, scan nhanh.

---

# 📈 Kết quả mong đợi

* **Dashboard/Top orders** → dùng `idx_orders_recent` (covering).
* **Tra cứu theo order\_number** → `idx_orders_ordernumber`.
* **Xem đơn theo khách hàng** → `idx_orders_customer`.
* **Theo dõi pending orders** → `idx_orders_pending`.
* **Tìm kiếm metadata** → `idx_orders_metadata`.
* **Báo cáo năm** → `idx_orders_brin_date`.

👉 PostgreSQL sẽ tự chọn index phù hợp (dùng `EXPLAIN ANALYZE` để kiểm tra).

---

📌 Như vậy:

* **B-tree** = nền tảng cho lookup/range.
* **Covering** = tối ưu SELECT nhiều.
* **Partial** = giảm size khi chỉ cần subset.
* **BRIN** = cực hiệu quả cho big data.
* **GIN** = JSONB & full-text.

---

