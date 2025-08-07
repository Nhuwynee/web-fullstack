**TEAMWORK EXERCISE**
**Kế thừa**

### JAVA BE CLASS

 Xây dựng một hệ thống quản lý thông tin
---

### **Lớp PERSON**

Thuộc tính:

* `+ id` : int
* `+ hoTen` : String
* `+ tuoi` : int
* `+ email` : String

Phương thức:

* `input()` : void
* `output()` : void
* `toString()` : void
* `getter`, `setter`

---

### **Lớp STUDENT (kế thừa từ PERSON)**

Thuộc tính:

* `super(id, hoTen, tuoi, email)`
* `+ diemTrungBinh`

Phương thức: 
* `input()` : void
* `output()` : void
* `toString()` : void
* `getter`, `setter`
* `xepLoai(dtb)` : void
* `xepLoai(dtb, soDuAn)` : void

---

#### **Lớp STUDENT\_BE (kế thừa từ STUDENT)**

Thuộc tính:

* `super()`
* `ngonNguLapTrinh` : String

Phương thức:
* `input()` : void
* `output()` : void
* `toString()` : void
* `getter`, `setter`
* `xepLoai(dtb)` : void (Từ class Student)

---

#### **Lớp STUDENT\_FULL (kế thừa từ STUDENT)**

Thuộc tính:

* `super()`
* `soDuAnThamGia` : int

Phương thức:
* `input()` : void
* `output()` : void
* `toString()` : void
* `getter`, `setter`
* `xepLoai(dtb, soDuAn)` : void (Từ class Student)


---

### **Lớp TEACHER (kế thừa từ PERSON)**
Thuộc tính:
* `super()`

* `+ boMon` : String
* `+ soGioDay` : double
* `+ final BigDecimal luong = 200000;`

Phương thức:

* `tinhLuong()`

---
---

**Yêu cầu chức năng:**
- ArrayList<Person> persons
- hàm getID từng loại thành viên:
  * StudentBE : SBE00001
  * StudentFull : SFT00001
  * Teacher: T0000001

`1. Thêm thành viên (học viên Backend / Fullstack hoặc giảng viên.)`
- Hiện menu chọn thêm loại thành vien (BE, Full, GV)
- switch case
- Nếu 1 (BE) :
    - Tạo đối tượng BE, gọi hàm input, add vào list `persons`
- Nếu 2 (Full) :
    - Tạo đối tượng Full, gọi hàm input, add vào list `persons`
- Nếu 3 (GV) :
    - Tạo đối tượng GV, gọi hàm input, add vào list `persons`

`2. Hiển thị danh sách thành viên`
- Gọi output

`3. Tìm kiếm thành viên theo tên hoặc email`
- Tạo `ArrayList<Person> result` chứa danh sách tìm kiếm
- Chuẩn hóa tên và email nhập vào (thêm điều kiện kiểm tra)
- kiểm tra value nhập vào là tên hay email
- Duyệt theo điều kiện: theo tên hoặc email (if-else): 
dùng for lặp trong `persons`, dùng contain, ok thì add vào ds result

`4. Thống kê:`
* Số học viên Backend
* Số học viên Fullstack
* Số học viên Fullstack có ≥ 3 dự án
* Số giảng viên có hơn 30 giờ dạy

- dùng for lặp trong `persons`, if (p `instanceof` cho từng class con), 
rồi tăng biến đếm, nếu có điều kiện thì xét (if - else). 
Xong thì in ra kết quả int

`5. Hiển thị học viên giỏi nhất của từng loại`
- dùng for lặp trong `persons`, if (p `instanceof` cho từng class con),
dùng if else để tìm điểm cao nhâất,
dùng 2 biến để ghi nhớ học viên có điểm cao nhất của từng loại
Tạo 2 mảng 2 loại hv và lặp xem những ai có điểm = biến ghi nhớ thì add vào

`6. Tính tổng lương của toàn bộ giảng viên`
- dùng for lặp trong "persons", if (p instanceof Teacher),
  rồi cộng dồn số giờ dạy tất cả giảng viên, rồi dùng hàm tính lương ra kết quả.

`7. Thoát chương trình`

---