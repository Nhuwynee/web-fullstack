---


## **Đề tài**: ỨNG DỤNG QUẢN LÝ THƯ VIỆN SÁCH

---

### **I. Mô tả**

Thư viện quản lý **2 loại tài liệu**: **Sách** và **Tạp chí**.
Mỗi tài liệu có: `id`, `tên`, `nhà xuất bản`, `giá`.

* **Sách** có thêm: `tác giả`, `số trang`.
* **Tạp chí** có thêm: `số phát hành`, `tháng`.

---

### **II. Yêu cầu**

#### **Câu 1 (OOP – 20 điểm)**

* Tạo lớp trừu tượng `Document` với các thuộc tính chung, phương thức trừu tượng `double calculateValue()`.
* Tạo 2 lớp con `Book`, `Magazine` kế thừa `Document` và override `calculateValue()`.
* Viết cơ chế sinh `id` tự động (static):

    * `Bxxx` cho Sách, `Mxxx` cho Tạp chí.

👉 Kiểm tra: **Constructor, Access Modifier, Static, 4 tính chất OOP**.

---

#### **Câu 2 (Quản lý danh sách – 25 điểm)**

* Tạo `ArrayList<Document>` để quản lý danh sách.
* Có dữ liệu mẫu (static block).
* Xây dựng các chức năng:

    1. Thêm mới tài liệu (sinh id tự động).
    2. Hiển thị toàn bộ tài liệu (dùng `instanceof`).
    3. Xóa tài liệu theo id (ném `NotFoundException` nếu không tồn tại).

👉 Kiểm tra: **ArrayList, Exception Handling**.

---

#### **Câu 3 (Tìm kiếm & Sắp xếp – 20 điểm)**

* Chức năng tìm kiếm tài liệu theo tên (gần đúng).
* Chức năng sắp xếp theo giá tăng dần (Collections.sort + Comparator).

👉 Kiểm tra: **Collections, Comparator, Search**.

---

#### **Câu 4 (Cấu trúc dữ liệu khác – 20 điểm)**

* Dùng **LinkedList<Document>** để lưu lịch sử thao tác thêm/xóa.
* Dùng **Stack<Document>** để cài Undo thao tác xóa cuối cùng.
* Dùng **Queue<Document>** để quản lý danh sách tài liệu chờ kiểm duyệt.

👉 Kiểm tra: **LinkedList, Stack, Queue**.

---

#### **Câu 5 (Set, Map, File – 15 điểm)**

1. Dùng **HashSet** để lưu danh sách nhà xuất bản (không trùng).
2. Dùng **HashMap\<String, Integer>** để thống kê số lượng tài liệu theo nhà xuất bản.
3. Ghi danh sách tài liệu ra file `documents.txt` và đọc lại hiển thị.

👉 Kiểm tra: **Set, Map, File I/O**.

---

### **III. Tổng kết**

* Câu 1: 20 điểm
* Câu 2: 25 điểm
* Câu 3: 20 điểm
* Câu 4: 20 điểm
* Câu 5: 15 điểm
  **Tổng: 100 điểm**

---
