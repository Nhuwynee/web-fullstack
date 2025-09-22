# Nội dung nâng cao            
## 🎯 1. Cơ chế Index của Postgres:						
───────────────────✦───────────────────  
### ➤  1.1. Quá trình PostgreSQL xây dựng **B-tree index** (ví dụ index trên `orders.customer_id`)

#### 1️⃣ **Full table scan**

* PostgreSQL phải đọc **toàn bộ bảng `orders`** từ heap (vùng lưu dữ liệu chính).
* Với 5 triệu bản ghi, nó sẽ lần lượt lấy từng hàng (row) trong bảng.

👉 Heap table trong Postgres lưu theo dạng **page** (block) 8KB. Mỗi block chứa nhiều row, và row được đánh số offset.

---

#### 2️⃣ **Sinh entry index: (customer\_id, TID)**

* Với mỗi row trong bảng, PostgreSQL tạo ra **entry index** gồm:

  * `customer_id` (giá trị của cột được index, dùng để sắp xếp).
  * `TID` (Tuple Identifier): "tọa độ" của row trong heap table.

    * Gồm 2 phần:

      * `block_number` → số thứ tự của page (8KB).
      * `tuple_offset` → vị trí row trong page đó.

💡 Ví dụ:

```
(customer_id = 101, TID = (block 153, offset 7))
(customer_id = 205, TID = (block 520, offset 3))
```

→ Nhờ TID, khi query có `WHERE customer_id = 205`, Postgres dùng index để nhảy thẳng đến block 520, row số 3 trong heap, thay vì scan cả bảng.

---

#### 3️⃣ **Sắp xếp dữ liệu index**

* Tất cả 5 triệu entry `(customer_id, TID)` được thu thập → PostgreSQL phải **sắp xếp** theo `customer_id`.
* Bước này tốn I/O và CPU (sort dữ liệu trước khi xây B-tree).
* Đây là lý do tạo index trên bảng lớn có thể mất thời gian.

---

#### 4️⃣ **Xây dựng B-tree**

Sau khi có danh sách đã sắp xếp, PostgreSQL tổ chức nó thành một cấu trúc **B-tree**:

* **Root (nút gốc):**

  * Chứa các "khóa phân tách" và con trỏ xuống các nút con.
* **Internal nodes (nút trung gian):**

  * Điều hướng tìm kiếm nhanh (giống mục lục).
* **Leaf nodes (nút lá):**

  * Chứa cặp `(customer_id, TID)` thực tế.

💡 Khi bạn query:

```sql
SELECT * FROM orders WHERE customer_id = 205;
```

→ PostgreSQL đi từ root → internal node → leaf node để tìm đúng entry `(205, TID)`.
→ Sau đó dùng TID nhảy thẳng tới heap và lấy row.

---

#### 🔁 Ví dụ trực quan

Giả sử bảng có các `customer_id`: 10, 20, 30, 40, 50.

**Index B-tree** có thể trông như:

```
          [30]                <-- root
         /    \
   [10,20]   [30,40,50]       <-- leaf nodes
```

* Root `[30]`: nếu `customer_id < 30` thì đi nhánh trái, ngược lại đi nhánh phải.
* Lá chứa `(customer_id, TID)` trỏ về row thật trong heap.

---

👉 Tóm lại:

* Index = bảng phụ, lưu `(key, TID)`.
* TID = “tọa độ” để tìm row nhanh trong heap.
* B-tree = cấu trúc cây cân bằng, giúp tìm kiếm `O(log n)` thay vì `O(n)` như full table scan.

───────────────────✦───────────────────  
### ➤  1.2.  Cơ chế lưu trữ

#### 1️⃣ **Mặc định là B-tree index**

* Khi viết:

  ```sql
  CREATE INDEX idx_orders_customer_id
  ON orders(customer_id);
  ```

  thì nếu không chỉ định `USING ...`, PostgreSQL sẽ mặc định dùng **B-tree**.
* Ngoài B-tree, Postgres còn có **GIN, GiST, BRIN, Hash…** nhưng đó là các loại đặc thù cho text search, dữ liệu không gian, hoặc dải giá trị.

---

#### 2️⃣ **B-tree hoạt động thế nào trong lưu trữ**

* PostgreSQL đọc toàn bộ bảng (`orders`) → duyệt qua từng tuple (row).

* Với mỗi row:

  * Lấy giá trị `customer_id`.
  * Tạo entry `(customer_id, TID)` (giống phần 1.1 giải thích).
  * Đưa entry này vào **cấu trúc B-tree**.

* Toàn bộ B-tree được **sắp xếp tăng dần theo `customer_id`**.

  * Điều này đảm bảo:

    * Tìm kiếm theo giá trị (`=`) → nhanh (`O(log n)`).
    * So sánh phạm vi (`BETWEEN`, `<`, `>`, `ORDER BY`) → hiệu quả, chỉ cần duyệt một đoạn của B-tree.

---

#### 3️⃣ **Cách B-tree lưu trong bộ nhớ**

* Index không lưu toàn bộ row, mà chỉ lưu:

  * **Key (customer\_id)** → dùng để so sánh.
  * **TID (tuple id)** → con trỏ về row trong heap table.

* B-tree được chia thành các **page (block 8KB)**:

  * **Root page**: 1 page, điều hướng xuống internal pages.
  * **Internal pages**: chứa các key để định tuyến.
  * **Leaf pages**: chứa (key, TID).

* Khi bạn query, PostgreSQL không cần duyệt heap hết → chỉ đi B-tree → đến đúng leaf node → nhảy vào heap qua TID.

---

#### 🔎 Ví dụ cụ thể

Giả sử `orders` có 1 triệu dòng với `customer_id` rải từ 1 → 1,000,000.

* B-tree index sẽ sắp xếp và chia thành nhiều page.
* Nếu bạn query:

  ```sql
  SELECT * FROM orders WHERE customer_id BETWEEN 100 AND 200;
  ```

  PostgreSQL sẽ:

  1. Tìm vị trí đầu tiên có `customer_id = 100` trong index.
  2. Duyệt tuần tự các leaf node đến `200`.
  3. Lấy TID trong index, nhảy vào heap để đọc row.

👉 Nhanh hơn nhiều so với quét cả bảng 1 triệu dòng.

---

#### ✅ Tóm tắt ý của đoạn 1.2

* PostgreSQL mặc định dùng **B-tree index**.
* Khi tạo index, nó duyệt toàn bảng, lấy `(customer_id, TID)` rồi sắp xếp vào B-tree.
* B-tree giúp tìm kiếm và range scan hiệu quả nhờ dữ liệu được lưu **theo thứ tự tăng dần**.

---
───────────────────✦───────────────────  
### ➤  1.3.  Tác động sau khi tạo index

#### 1️⃣ **WHERE customer\_id = … dùng index → nhanh hơn**

* Nếu bạn có truy vấn:

  ```sql
  SELECT * FROM orders WHERE customer_id = 123;
  ```
* Nếu **không có index**: PostgreSQL phải quét **toàn bộ bảng** (Sequential Scan) để tìm. Với hàng triệu dòng thì tốn rất nhiều I/O.
* Nếu **có index B-tree trên `customer_id`**: PostgreSQL đi vào index, tìm ra `(123, TID)` trong vài bước (O(log n)), rồi nhảy thẳng đến row trong heap.
  👉 Nhanh hơn rất nhiều.

---

#### 2️⃣ **Các loại scan khi dùng index**

PostgreSQL không phải lúc nào cũng dùng index kiểu giống nhau. Nó có mấy chiến lược:

###### 🔹 **Index Scan**

* Đi qua index để tìm `(customer_id, TID)`.
* Sau đó dùng TID nhảy đến heap table để lấy row đầy đủ.
* Ví dụ:

  ```sql
  SELECT * FROM orders WHERE customer_id = 123;
  ```

###### 🔹 **Bitmap Index Scan**

* Dùng khi query trả về **nhiều row**.
* PostgreSQL sẽ:

  1. Duyệt index để lấy **danh sách TID**.
  2. Đánh dấu chúng vào **bitmap** (một cấu trúc dữ liệu nhớ vị trí row).
  3. Sau đó quét heap theo bitmap → gom nhiều row một lần, giảm random I/O.
* Ví dụ:

  ```sql
  SELECT * FROM orders WHERE customer_id IN (1000, 2000, 3000);
  ```

###### 🔹 **Index Only Scan**

* Nếu index đã chứa đủ cột cần thiết, PostgreSQL **không cần đọc heap table**.
* Thay vì (key, TID), index có thể chứa thêm các cột khác (covering index).
* Ví dụ:

  ```sql
  CREATE INDEX idx_orders_customer_id_date 
  ON orders(customer_id, order_date);

  SELECT customer_id, order_date
  FROM orders
  WHERE customer_id = 123;
  ```

  → PostgreSQL có thể trả kết quả **chỉ từ index**, không động đến heap → nhanh nhất.

---

#### 3️⃣ **Nhược điểm: ghi chậm hơn**

* Mỗi khi bạn `INSERT`, `UPDATE`, `DELETE`:

  * PostgreSQL phải ghi **vào heap table** (bảng gốc).
  * Đồng thời phải cập nhật **tất cả index liên quan**.
* Nếu một bảng có **quá nhiều index**, mỗi thao tác ghi sẽ càng nặng.
* Ví dụ: 

##### 1. Lúc **tạo index ban đầu**

* PostgreSQL sẽ **scan toàn bảng** và tạo entry (key, TID) cho từng row → giống như chụp một bức ảnh toàn cảnh.
* Sau đó, index trở thành một cấu trúc **sống** (luôn phải đồng bộ với bảng).

##### 2. Khi có **INSERT/DELETE/UPDATE**

Mỗi lần dữ liệu thay đổi, index cũng phải thay đổi tương ứng.

* `INSERT`: thêm entry mới vào đúng chỗ trong B-tree (đôi khi phải split node, update pointer, rebalance cây).
* `DELETE`: xóa entry trong B-tree (cũng phải rebalance hoặc đánh dấu dead).
* `UPDATE`: xóa entry cũ + thêm entry mới.

👉 Việc “thêm entry” này không chỉ là ghi thêm 1 dòng, mà còn kéo theo:

* Duyệt cây B-tree từ root → leaf để tìm vị trí đúng.
* Chèn vào page, nếu page đầy thì phải **split page**.
* Cập nhật pointer lên root.

Tức là **chi phí xử lý (CPU + I/O)** tăng, không phải chỉ lưu thêm một con số đơn giản.

##### 3. So sánh chi phí

* Nếu **không có index**:
  `INSERT` chỉ cần ghi row vào heap (thêm vào page cuối, rất rẻ).

* Nếu **có 1 index**:
  `INSERT` phải ghi heap + update 1 cây B-tree.

* Nếu **có 5 index**:
  `INSERT` phải ghi heap + update 5 cây B-tree.

👉 Vậy nên số lượng index càng nhiều thì chi phí ghi càng tăng.

##### 4. Ví dụ trực quan

Giả sử bảng giống như một **tủ hồ sơ**:

* Heap table = tập hồ sơ gốc (xếp chồng).
* Index = sổ tay danh mục để tra nhanh.

Nếu bạn thêm một hồ sơ mới:

* Chỉ ghi vào tập hồ sơ (heap) thì nhanh.
* Nhưng nếu bạn có 5 cuốn sổ danh mục (index), bạn phải mở từng cuốn ra, tìm đúng chỗ theo ABC, chèn thêm 1 dòng → tốn thêm công.


👉 Quy tắc chung:

* **Tạo index vừa đủ cho query quan trọng**.
* Không nên tạo index “tràn lan” vì sẽ hại performance khi ghi dữ liệu.

---

#### ✅ Tóm lại:

* Index tăng tốc đọc (SELECT với WHERE, ORDER BY, JOIN).
* Nhưng index làm chậm ghi (INSERT/UPDATE/DELETE) vì phải maintain thêm dữ liệu.
* PostgreSQL còn có nhiều chiến lược scan thông minh: Index Scan, Bitmap Index Scan, Index Only Scan.

---

───────────────────✦───────────────────  
### ➤  1.4.  Cách hoạt động của Index

#### Khi bạn có một index (vd: `idx_orders_customer_id`) và chạy truy vấn:

```sql
SELECT * 
FROM orders2 
WHERE customer_id = 123;
```

Index sẽ trả về danh sách **TID** (tuple ID) của các row phù hợp. Từ đó PostgreSQL có **2 cách đọc dữ liệu**:



#### 1️⃣ **Index Scan (trực tiếp)**

* Nếu **số row khớp ít** (ví dụ chỉ vài dòng).
* PostgreSQL sẽ:

  * Đi qua index, lấy từng TID.
  * Với mỗi TID, nhảy vào heap để lấy row.

👉 Mỗi TID = 1 lần nhảy vào heap.

* Nếu chỉ 10 row → 10 lần I/O, vẫn rẻ.
* Cách này nhanh khi kết quả nhỏ (selectivity cao).


#### 2️⃣ **Bitmap Index Scan**

* Nếu **số row khớp rất nhiều** (ví dụ hàng nghìn, hàng triệu).
* Nếu dùng Index Scan trực tiếp, PostgreSQL sẽ phải “nhảy” vào heap hàng triệu lần → quá nhiều random I/O.

👉 Để tránh tốn kém:

* PostgreSQL gom toàn bộ TID thành **bitmap** (một cấu trúc dữ liệu ghi nhớ các block chứa row).
* Sau đó **quét heap theo từng block** (đọc tuần tự), thay vì nhảy loạn xạ.

💡 Cách này giảm random I/O, vì PostgreSQL đọc dữ liệu thành từng cụm.

#### 🧩 Ví dụ minh họa

* Giả sử index trả về:

  ```
  TID: (block 1, offset 3), (block 1, offset 7), (block 2, offset 1), ...
  ```

* **Index Scan trực tiếp**:

  * Nhảy (block 1 → offset 3), rồi (block 1 → offset 7), rồi (block 2 → offset 1), v.v… → nhiều random I/O.

* **Bitmap Index Scan**:

  * Gom lại:

    * Block 1 có offset 3, 7
    * Block 2 có offset 1
  * Rồi đọc block 1 một lần, lấy cả 2 row, sau đó đọc block 2.
  * Ít random I/O hơn → nhanh hơn khi kết quả lớn.

#### ✅ Tóm gọn:

* Ít row → **Index Scan** (nhanh, ít I/O).
* Nhiều row → **Bitmap Index Scan** (gom cụm, giảm nhảy loạn).

---
───────────────────✦───────────────────  
### ➤  1.5.  Bitmap Index Scan + 1.6. Bitmap Heap Scan

#### 1️⃣ Khi chạy truy vấn

```sql
SELECT *  
FROM orders  
WHERE customer_id BETWEEN 90000 AND 99000;
```

* Index `idx_orders_customer_id` được dùng để lấy ra **danh sách TID** (Tuple ID).
* Vì khoảng này (`90000–99000`) trả về rất nhiều row (\~450k), PostgreSQL quyết định **không dùng Index Scan trực tiếp** mà dùng **Bitmap Index Scan + Bitmap Heap Scan**.

#### 2️⃣ Bitmap Index Scan

* PostgreSQL không giữ nguyên danh sách TID rời rạc.
* Nó **gom TID theo block**.
* Mỗi block có nhiều row (tuple), trong block nó tạo ra một **bitmap**:

  * `1` = row match điều kiện.
  * `0` = row không match.

Ví dụ bạn đưa rất đúng:

```
Block 100: [0,0,0,0,1,0,0,1,1,0,...]
                    ↑     ↑ ↑
                 row5 row8 row9 match
```

Tức là:

* Trong block 100 của heap, PostgreSQL sẽ đọc toàn bộ block (8KB).
* Nhưng nó chỉ lấy row 5, 8, 9 thôi.

#### 3️⃣ Bitmap Heap Scan

Sau khi gom bitmap xong, PostgreSQL đi quét heap **theo block** (ít random I/O hơn).

* Đọc block 100 → lấy row 5, 8, 9.
* Đọc block 101 → lấy row 1, 2.
* Đọc block 102 → lấy row 7.

👉 So với Index Scan trực tiếp (nhảy từng row), cách này tiết kiệm I/O hơn nhiều khi số lượng row lớn.

#### 4️⃣ Giải thích EXPLAIN ANALYZE bạn đưa

```
Bitmap Heap Scan on orders  
  (cost=6112.24..153768.30 rows=445250 width=113) 
  (actual time=36.607..603.884 rows=450349 loops=1)  
   Recheck Cond: ((customer_id >= 90000) AND (customer_id <= 99000))  
   Rows Removed by Index Recheck: 1560406  
   Heap Blocks: exact=62038 lossy=33262  
   -> Bitmap Index Scan on idx_orders_customer_id  
        (cost=0.00..6000.93 rows=445250 width=0)  
        (actual time=27.864..27.864 rows=450349 loops=1)  
        Index Cond: ((customer_id >= 90000) AND (customer_id <= 99000))  
```

🔎 Giải nghĩa:

* **Bitmap Index Scan**: lấy được \~450k row phù hợp từ index.
* **Bitmap Heap Scan**: gom row theo block và đọc heap.
* `Rows Removed by Index Recheck: 1560406` → index lấy ra 2 triệu row, nhưng khi check lại ở heap thì loại bỏ 1.56 triệu (do MVCC/visibility check).
* `Heap Blocks: exact=62038 lossy=33262`:

  * exact = bitmap có đủ chi tiết bit cho từng row.
  * lossy = bitmap chỉ biết “block này có row match” chứ không nhớ cụ thể row nào (thường xảy ra khi bitmap quá lớn, Postgres nén lại).

⏱️ Thời gian chạy: \~614 ms → chấp nhận được vì phải xử lý gần nửa triệu dòng.

#### ✅ Tóm lại:

* **Bitmap Index Scan** = lấy TID theo block → nén thành bitmap.
* **Bitmap Heap Scan** = dùng bitmap để quét heap block theo thứ tự → giảm random I/O.
* Đây là chiến lược tối ưu khi điều kiện match rất nhiều row.

---
───────────────────✦───────────────────  
### ➤  1.7.  Bitmap bị lossy

#### 1️⃣ Bitmap thường (exact bitmap)

* Với Bitmap Index Scan bình thường, PostgreSQL nhớ rõ trong **mỗi block** row nào match, row nào không.
* Ví dụ block 100 có 10 row:

  ```
  [0,0,0,1,0,0,1,0,0,0]
         ↑     ↑
      row4  row7 match
  ```

👉 Khi đọc block 100, nó chỉ lấy row4 và row7, bỏ qua các row khác.

#### 2️⃣ Khi bitmap bị **lossy**

* Nếu kết quả truy vấn match **quá nhiều row** (bitmap quá to → vượt memory limit), PostgreSQL không thể lưu chi tiết từng bit (row nào match).
* Nó sẽ **nén bitmap** bằng cách đánh dấu **cả block là match**.

Ví dụ block 100:

```
[1,1,1,1,1,1,1,1,1,1]   (lossy)
```

👉 PostgreSQL chỉ nhớ “block 100 có match”, nhưng **không biết row nào**.

#### 3️⃣ Hậu quả

* PostgreSQL buộc phải đọc **toàn bộ block 100** từ heap.
* Sau đó **recheck lại từng row trong block** bằng cách áp dụng điều kiện WHERE một lần nữa.

👉 Điều này tạo ra:

```
Rows Removed by Index Recheck = ...
```

trong EXPLAIN ANALYZE (như bạn thấy trước đó có \~1.5 triệu row bị loại).

#### 4️⃣ Ảnh hưởng đến hiệu năng

* Với ít row → Bitmap Index Scan (exact) rất nhanh.
* Với nhiều row nhưng chưa quá nhiều → Bitmap Index Scan (lossy) vẫn có lợi so với Index Scan trực tiếp.
* Nhưng nếu quá nhiều row (gần như “half table”) → **Seq Scan** lại nhanh hơn, vì đọc tuần tự nguyên bảng rẻ hơn recheck hàng triệu row.

👉 PostgreSQL optimizer sẽ tự quyết định: dùng **Seq Scan** hay **Bitmap Index Scan** tùy tình huống.

#### ✅ Tóm lại:

* **Lossy bitmap** xảy ra khi kết quả quá lớn, PostgreSQL không đủ memory để lưu chi tiết bit.
* Khi đó nó đánh dấu cả block = match → phải recheck từng row.
* Xuất hiện trong plan với `Rows Removed by Index Recheck`.
* Nếu nhiều quá → đôi khi còn **tệ hơn Seq Scan**.

---
───────────────────✦───────────────────  
### ➤  1.8. Tối ưu

#### 1️⃣ Vấn đề khi dùng `SELECT *`

* Nếu bạn viết:

  ```sql
  SELECT * 
  FROM orders
  WHERE customer_id BETWEEN 90000 AND 99000;
  ```
* PostgreSQL **bắt buộc** phải vào heap table để lấy tất cả các cột, bởi vì index (B-tree) thường chỉ lưu **cột khóa** (`customer_id` trong ví dụ).
* Do đó → phải dùng **Bitmap Heap Scan** hoặc **Index Scan + Heap Fetch**, dẫn đến nhiều I/O.

#### 2️⃣ Giải pháp 1 – Chỉ chọn cột cần thiết

Nếu bạn chỉ cần `id`, `customer_id`, `total_amount` thì query nên viết:

```sql
SELECT id, customer_id, total_amount 
FROM orders
WHERE customer_id BETWEEN 90000 AND 99000;
```

👉 Ít cột hơn → ít phải động tới heap → tốc độ cải thiện.

#### 3️⃣ Giải pháp 2 – Covering Index (Index với INCLUDE)

Postgres hỗ trợ **INCLUDE** (covering index):

```sql
CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_orders_customer_id_inc
ON orders (customer_id)
INCLUDE (id, total_amount);
```

* Phần **(customer\_id)** là cột khóa (dùng để sắp xếp trong B-tree).
* Phần **INCLUDE (id, total\_amount)** là các cột thêm vào index, không ảnh hưởng đến cấu trúc B-tree.
* Nhờ vậy, index không chỉ biết "row nào match" mà còn **chứa sẵn dữ liệu cần SELECT**.

#### 4️⃣ Kết quả thực tế (EXPLAIN ANALYZE bạn đưa)

```
Index Only Scan using idx_orders_customer_id_inc on orders  
  (cost=0.43..15765.43 rows=445250 width=14) 
  (actual time=0.025..36.270 rows=450349 loops=1)
  Index Cond: ((customer_id >= 90000) AND (customer_id <= 99000))
  Heap Fetches: 0
Planning Time: 0.135 ms
Execution Time: 43.068 ms
```

* `Index Only Scan`: PostgreSQL chỉ quét **index** mà không vào heap.
* `Heap Fetches: 0`: không cần lấy thêm từ bảng gốc.
* `Execution Time: 43ms` → nhanh hơn nhiều so với hơn **600ms** khi dùng Bitmap Heap Scan trước đó.

#### 5️⃣ Ý nghĩa tối ưu

* Tránh `SELECT *` → chỉ lấy cột cần.
* Tạo **covering index** để Postgres có thể dùng **Index Only Scan**.
* Giảm **random I/O** cực mạnh → tốc độ tăng gấp nhiều lần.

---
    Các loại scan và index
---
### 😅 Tổng hợp:  (1) các loại scan khi dùng index + (2) cách đánh index tương ứng

---

#### 🔎 Các loại Scan & Cách đánh Index trong PostgreSQL

| Loại Scan                                | Khi xảy ra                                                  | Cách hoạt động                                               | Cách đánh index để dùng                                                           |
| ---------------------------------------- | ----------------------------------------------------------- | ------------------------------------------------------------ | --------------------------------------------------------------------------------- |
| **Index Scan**                           | Truy vấn trả ít dòng (`WHERE id = ...`)                     | Theo index → lấy TID → nhảy vào heap lấy dữ liệu             | `CREATE INDEX idx_orders_id ON orders(id);`                                       |
| **Index Only Scan**                      | Truy vấn chỉ SELECT các cột có trong index (covering index) | Lấy dữ liệu trực tiếp từ index, không cần heap               | `CREATE INDEX idx_orders_cust_inc ON orders(customer_id) INCLUDE (total_amount);` |
| **Bitmap Index Scan + Bitmap Heap Scan** | Truy vấn trả nhiều dòng (`WHERE customer_id BETWEEN ...`)   | Gom TID theo block → đọc block 1 lần → recheck row nào match | `CREATE INDEX idx_orders_cust ON orders(customer_id);`                            |

---

#### 📌 Cách chọn loại index

* **B-Tree (default)** → 90% trường hợp (`=, <, >, BETWEEN, ORDER BY`).
* **GIN** → full-text, JSONB, mảng.
* **GiST** → dữ liệu không gian (PostGIS), search gần đúng.
* **BRIN** → bảng cực lớn, dữ liệu có thứ tự (id tăng, timestamp).
* **Partial / Expression / Covering index** → để tối ưu cho query đặc thù.
---
#### 👉 Mối liên quan: 2 nhóm khái niệm khác nhau nhưng liên quan trực tiếp:

#### 1️⃣ Nhóm **Scan**

Đây là cách **PostgreSQL thực thi query** khi nó đã có index:

* **Index Scan** → ít row match → đi từng TID vào heap.
* **Index Only Scan** → đủ cột trong index → đọc trực tiếp từ index, không vào heap.
* **Bitmap Index Scan + Bitmap Heap Scan** → nhiều row match → gom TID theo block, tránh nhảy lung tung.

📌 Nhóm này là "cách đọc dữ liệu".

#### 2️⃣ Nhóm **Index Type**

Đây là "cách tạo index", tức **dạng cấu trúc dữ liệu** để lưu index:

* **B-Tree** → mặc định, hỗ trợ `=, <, >, BETWEEN, ORDER BY`.
* **GIN** → tối ưu cho full-text, JSONB, mảng.
* **GiST** → dữ liệu không gian, tìm kiếm gần đúng.
* **BRIN** → bảng rất lớn, dữ liệu tuần tự.
* **Partial, Expression, Covering index** → kỹ thuật đặc biệt để tối ưu query.

📌 Nhóm này là "kiểu index".

#### 3️⃣ Liên hệ 2 nhóm

* Khi bạn **tạo index** (B-Tree, GIN, GiST, BRIN, …) → PostgreSQL sẽ **dùng index đó để scan** (Index Scan, Index Only Scan, Bitmap Heap Scan).
* Ví dụ:

  * `B-Tree` trên `customer_id` → query `WHERE customer_id = 123` → PostgreSQL chọn **Index Scan**.
  * `B-Tree` + `INCLUDE` → query chỉ lấy cột trong index → PostgreSQL chọn **Index Only Scan**.
  * `B-Tree` + query trả hàng trăm nghìn dòng → PostgreSQL chọn **Bitmap Heap Scan**.
  * `GIN` trên `to_tsvector(content)` → query full-text → PostgreSQL chọn **Bitmap Index Scan** hoặc **Index Scan** tùy tình huống.

#### 👉 Tóm lại:

* **Index Type** (B-Tree, GIN, …) → bạn quyết định khi tạo index.
* **Scan Type** (Index Scan, Index Only Scan, Bitmap Heap Scan) → PostgreSQL tự chọn khi chạy query, dựa trên index có sẵn + số row ước lượng.
---
| **Index Type**                      | **Index Scan**                                        | **Index Only Scan**                                                   | **Bitmap Index Scan + Bitmap Heap Scan**                               | Ghi chú                                     |
| ----------------------------------- | ----------------------------------------------------- | --------------------------------------------------------------------- | ---------------------------------------------------------------------- | ------------------------------------------- |
| **B-Tree** (default)                | ✅ thường dùng cho `=`, `<`, `>`, `BETWEEN` với ít row | ✅ nếu có covering index (`INCLUDE`) và cột cần SELECT nằm trong index | ✅ nếu query trả nhiều row (range lớn, ví dụ `BETWEEN 90000 AND 99000`) | 90% query thực tế                           |
| **GIN** (full-text, JSONB, array)   | ❌ hiếm khi                                            | ❌ (không hỗ trợ Index Only Scan)                                      | ✅ thường chạy Bitmap Index Scan để gom nhiều TID match keyword         | Dùng cho `to_tsvector`, JSONB, mảng         |
| **GiST** (spatial, search gần đúng) | ✅ có thể (cho vài row)                                | ❌ không hỗ trợ Index Only Scan                                        | ✅ thường dùng Bitmap Index Scan khi kết quả lớn                        | PostGIS, fuzzy search                       |
| **BRIN** (Block Range Index)        | ❌ (không trỏ row trực tiếp)                           | ❌                                                                     | ✅ luôn dùng dạng “Bitmap Heap Scan” vì BRIN đánh index theo block      | Rất nhẹ, hợp bảng cực lớn tuần tự           |
| **Hash**                            | ✅ chỉ với `=`                                         | ❌                                                                     | ❌                                                                      | Hiếm dùng, ít tính năng, không hỗ trợ range |

---

#### 🌳 Sơ đồ chọn Index + Scan trong PostgreSQL

```
Query có điều kiện WHERE?
   │
   ├── Có (lọc dữ liệu)
   │      │
   │      ├── Điều kiện =, <, >, BETWEEN, ORDER BY
   │      │        └── Tạo B-Tree Index
   │      │              │
   │      │              ├── Ít row match → Index Scan
   │      │              ├── Nhiều row match → Bitmap Index + Bitmap Heap Scan
   │      │              └── Query chỉ cần cột trong index → Index Only Scan
   │      │
   │      ├── Full-text search / JSONB / Array
   │      │        └── Tạo GIN Index
   │      │              └── Thường → Bitmap Index Scan
   │      │
   │      ├── Dữ liệu không gian / fuzzy / k-NN
   │      │        └── Tạo GiST (hoặc SP-GiST) Index
   │      │              └── Index Scan hoặc Bitmap Index Scan
   │      │
   │      └── Dữ liệu cực lớn, có tính tuần tự (id, date tăng dần)
   │               └── Tạo BRIN Index
   │                     └── Luôn Bitmap Heap Scan (theo block)
   │
   └── Không có WHERE (full scan)
            └── Seq Scan (không dùng index)
```

---

#### 📌 Ý nghĩa

* **Scan Type** (Index Scan / Index Only Scan / Bitmap Heap Scan) → PostgreSQL **tự chọn** khi chạy query.
* **Index Type** (B-Tree, GIN, GiST, BRIN, …) → do bạn **tạo ra** trước.
* PostgreSQL kết hợp cả 2 để quyết định cách thực thi tối ưu.

---

### 👇 Trả lời câu hỏi so sánh ? 

#### C1. `CONCURRENTLY` khi tạo Index

* Bình thường khi bạn chạy:

  ```sql
  CREATE INDEX idx_name ON orders(customer_id);
  ```

  → PostgreSQL sẽ **khóa ghi toàn bộ bảng** → các lệnh `INSERT/UPDATE/DELETE` sẽ bị chặn cho tới khi index tạo xong.

* Nếu chạy:

  ```sql
  CREATE INDEX CONCURRENTLY idx_name ON orders(customer_id);
  ```

  → bảng **vẫn cho phép ghi bình thường**, không làm gián đoạn dịch vụ.
  Nhưng: tạo chậm hơn và không được chạy trong transaction.

👉 Giống như: sửa đường **ban đêm phải cấm xe đi** (CREATE bình thường), hoặc **sửa từng làn để xe vẫn chạy** (CONCURRENTLY).

#### C2. Composite Index vs Covering Index

* **Composite Index** = index nhiều cột để lọc/sắp xếp.

  ```sql
  CREATE INDEX idx_composite ON orders(customer_id, order_date);
  ```

  Dùng khi query có điều kiện theo **nhiều cột**:

  ```sql
  SELECT * FROM orders 
  WHERE customer_id = 10 AND order_date > '2025-01-01';
  ```

* **Covering Index** = ngoài cột lọc, còn “kèm” thêm cột SELECT để không cần quay lại bảng.

  ```sql
  CREATE INDEX idx_covering 
  ON orders(customer_id) INCLUDE(order_date, amount);
  ```

  Dùng khi query như:

  ```sql
  SELECT order_date, amount 
  FROM orders 
  WHERE customer_id = 10;
  ```

  → dữ liệu `order_date, amount` có sẵn trong index, không phải vào bảng nữa.

👉 Composite = nhiều cột để lọc.
👉 Covering = thêm cột để **đọc dữ liệu chỉ từ index**.

#### C3. Index Scan vs Index Only Scan

* **Index Scan**:

  * DB dùng index tìm bản ghi, nhưng vẫn phải vào bảng để lấy dữ liệu.
  * Ví dụ: index chỉ có `customer_id`, nhưng query cần thêm `amount` (không có trong index) → phải vào bảng.

* **Index Only Scan**:

  * DB lấy hết dữ liệu từ index, không cần vào bảng.
  * Ví dụ: index có `customer_id INCLUDE(amount)`
    Query chỉ cần `customer_id, amount` → đọc ngay từ index, nhanh hơn.

👉 Index Scan = tra index → rồi vào bảng.
👉 Index Only Scan = chỉ cần index, bỏ qua bảng → nhanh hơn.



#### Tóm lại:

| Khái niệm           | Ý nghĩa                                           |
| ------------------- | ------------------------------------------------- |
| **CONCURRENTLY**    | Tạo index mà không khóa ghi bảng                  |
| **Composite Index** | Index trên nhiều cột để lọc/sắp xếp               |
| **Covering Index**  | Index + thêm cột để SELECT mà không phải vào bảng |
| **Index Scan**      | Dùng index nhưng vẫn phải đọc bảng                |
| **Index Only Scan** | Chỉ đọc index, không cần vào bảng                 |

---
### 🤝 Giải thích vấn đề vào bảng (heap) lấy dữ liệu và không lấy là như thế nào ?

#### 🗄️ 1. Bảng (table) và Index khác nhau như thế nào?

* **Bảng (heap)**: chứa **toàn bộ dữ liệu gốc** (tất cả các cột).
* **Index**: giống như **mục lục sách** → chỉ lưu **một vài cột** (cột khóa + cột INCLUDE nếu có).

Ví dụ:
Bảng `orders` có dữ liệu:

| order\_id | customer\_id | order\_date | amount |
| --------- | ------------ | ----------- | ------ |
| 1         | 101          | 2025-09-01  | 500    |
| 2         | 102          | 2025-09-02  | 300    |
| 3         | 101          | 2025-09-03  | 450    |

Tạo index:

```sql
CREATE INDEX idx_customer ON orders(customer_id);
```

Index này **chỉ lưu customer\_id + vị trí dòng trong bảng** (gọi là TID).
Nó **không có order\_date, amount**.

#### 🔍 2. Index Scan (có vào bảng)

Khi query:

```sql
SELECT order_date, amount
FROM orders
WHERE customer_id = 101;
```

* Index `idx_customer` giúp tìm dòng nào có `customer_id = 101`.
* Nhưng **order\_date, amount không có trong index**, nên PostgreSQL phải “nhảy” vào bảng (heap) để lấy thêm dữ liệu.
  👉 Đây gọi là **Index Scan**.

#### ⚡ 3. Index Only Scan (không vào bảng)

Nếu ta tạo index:

```sql
CREATE INDEX idx_customer_cover 
ON orders(customer_id) INCLUDE(order_date, amount);
```

Bây giờ index đã có:

* `customer_id` (dùng để lọc)
* `order_date, amount` (dùng để SELECT)

Query:

```sql
SELECT order_date, amount
FROM orders
WHERE customer_id = 101;
```

👉 PostgreSQL chỉ cần đọc index `idx_customer_cover`, **không cần quay lại bảng** nữa → nhanh hơn.

#### ⚡Hình dung:

* **Index Scan** = bạn mở mục lục sách để tìm trang, nhưng phải lật sách ra để đọc nội dung.
* **Index Only Scan** = mục lục đã chứa luôn nội dung bạn cần → khỏi mở sách.


