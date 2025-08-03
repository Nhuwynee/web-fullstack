# **Ứng dụng quản lý điện thoại**

## **I. Mô tả chương trình**

Chương trình quản lý điện thoại của một cửa hàng bán điện thoại di động.
Điện thoại được chia làm 2 loại:

- Điện thoại mới
- Điện thoại cũ

### **[Các thông tin chung]**

#### Mỗi điện thoại bao gồm các thông tin:

- id
- Tên điện thoại
- Giá bán
- Thời gian bảo hành
- OS (Android/iOS)
- Hãng sản xuất (ví dụ: iPhone, Samsung, ...)

#### Quy tắc tạo id tự động:

- Điện thoại mới: DTMXXX (với XXX là số tự động tăng từ 000 đến 999)
- Điện thoại cũ: DTCXXX (với XXX là số tự động tăng từ 000 đến 999)

### **[Mô tả thêm]**

- Điện thoại mới có thêm: Số lượng
- Điện thoại cũ có thêm: Tình trạng pin (còn bao nhiêu phần trăm)

## II. Yêu cầu chương trình

### Task 1: Tạo menu (chỉ cần hiển thị menu, sử dụng vòng lặp)

#### MENU CHÍNH - CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI

1. Xem danh sách điện thoại
2. Thêm mới
3. Cập nhật
4. Xóa
5. Sắp xếp theo giá
6. Tìm kiếm
7. Tính tổng tiền
8. Giảm giá cho điện thoại cũ
9. Thoát chương trình

- #### Menu con 1: Xem danh sách điện thoại

1. Xem tất cả
2. Xem điện thoại cũ
3. Xem điện thoại mới
4. Trở về menu chính

- #### Menu con 2: Thêm mới

1. Thêm mới điện thoại cũ
2. Thêm mới điện thoại mới
3. Trở về menu chính

- #### Menu con 3, 4 (Cập nhật, Xóa) => Không cần tạo

- #### Menu con 5: Sắp xếp theo giá

1. Tăng dần
2. Giảm dần
3. Trở về menu chính

- #### Menu con 6: Tìm kiếm

1. Tìm kiếm tất cả điện thoại
2. Tìm kiếm điện thoại cũ
3. Tìm kiếm điện thoại mới
4. Trở về menu chính

   ##### Trong menu con 6, khi chọn các lựa chọn 1, 2, 3 thì xuất hiện menu tiếp theo:

    - Tìm kiếm theo giá (Nhập khoảng từ bao nhiêu đến bao nhiêu)
    - Tìm kiếm theo tên
    - Tìm kiếm theo hãng
    - Trở về menu Tìm kiếm

- #### Menu con 7, 8, 9 (Tính tổng tiền, Giảm giá điện thoại cũ, Thoát chương trình) => Không cần tạo

### Task 2: Tạo các class, property, method theo OOP (tạo kiến trúc cơ bản cho chương trình)

Task này chỉ cần thực hiện việc tạo các lớp, phương thức, phương thức theo OOP, không yêu cầu viết code chức năng chương
trình.

### Task 3: Xây dựng hoàn chỉnh các chức năng sau

1. Xem danh sách điện thoại
2. Thêm mới
3. Cập nhật
4. Xóa

### Hướng dẫn task 3:

#### 1. Xem danh sách điện thoại

- #### Mô tả

  Khởi tạo: tạo ra 1 ArrayList chứa cả thông tin điện thoại cũ và điện thoại mới (dùng static để khởi tạo dữ liệu ban
  đầu)

- #### Chức năng

      1. Xem tất cả: dùng vòng lặp duyệt từng phần tử trong ArrayList gồm cả 2 loại điện thoại cũ và mới
      2. Xem điện thoại cũ: dùng instanceof để duyệt điện thoại cũ
      3. Xem điện thoại mới: dùng instanceof để duyệt điện thoại mới

#### 2. Thêm mới

- #### Mô tả
  ##### Chọn chức năng thêm mới:
      1. Thêm mới điện thoại cũ: Gọi đến phương thức input của điện thoại cũ, xử lý id tự đông tăng theo format quy định.
      2. Thêm mới điện thoại mới: Gọi đến phương thức input của điện thoại mới, xử lý id tự đông tăng theo format quy định.

#### 3. Cập nhật

- #### Mô tả

  ##### Nhập id (bắt đầu bằng DTC hoặc DTM, 6 ký tự):

      1. Nếu sai format → báo lỗi
      2. Nếu đúng: Duyệt từng phần từ trong ArrayList để lấy ra id của điện thoại cũ hoặc mới
      3. Nếu không tìm thấy → báo không tồn tại
      4. Nếu tìm thấy → gọi input() để cập nhật thông tin, sau đó báo cập nhật thành công

#### 4. Xóa

- #### Mô tả

  ##### Nhập id (6 ký tự, bắt đầu bằng DTC hoặc DTM):

      1. Nếu sai format → báo lỗi
      2. Nếu đúng: Duyệt từng phần từ trong ArrayList để lấy ra id của điện thoại cũ hoặc mới
      3. Nếu không tìm thấy → báo id không tồn tại
      4. Nếu tìm thấy → hỏi người dùng Yes/No để xóa hoặc không

#### 5. Xem danh sách điện thoại

- #### Mô tả

      - Sắp xếp ArrayList dựa trên giá. Tham khảo hàm sortByFor() ở bài trước.

#### 6. Tìm kiếm

- #### Mô tả

      1. Tìm kiếm theo loại: Dùng instanceof để lọc ra các điện thoại cũ hoặc mới.
      2. Tìm kiếm theo giá: Cho phép người dùng nập khoảng giá, duyệt qua ArrayList và lọc ra các điện thoại nằm trong khoảng giá này.
      3. Tìm kiếm theo tên: Nhận đầu vào từ người dùng, duyệt qua ArrayList và sử dụng phương thức contains trên tên của mỗi điện thoại để tìm kiếm gần đúng.

#### 7. Tính tổng giá

- #### Mô tả

      Tính tổng giá của tất cả các điện thoại có trong cửa hàng.

- ### Gợi ý
      1. Trong lớp cha Phone (hoặc tên class cha tự đặt), thêm một phương thức trừu tượng (abstract method) để tính tổng giá. Việc này cần thiết vì mỗi loại điện thoại có cách tính tổng giá riêng biệt, không thể triển khai body để tính toán tại lớp cha DienThoai.
      2. Trong lớp OldPhone, triển khai phương thức tính tổng giá theo cách riêng của điện thoại cũ và trả về giá của điện thoại = tình trạng pin * giá cứng (ví dụ 2 triệu đồng).
      3. Trong lớp NewPhone, triển khai phương thức tính tổng giá theo cách riêng của điện thoại mới và trả về giá của điện thoại = số lượng * giá.
      4. Sử dụng vòng lặp để duyệt qua danh sách tất cả các điện thoại và tính tổng giá.

#### 8. Giảm giá cho điện thoại cũ

- #### Mô tả

      Thêm chức năng giảm giá cho điện thoại cũ để khuyến khích mua sắm.

- ### Gợi ý
      1. Tạo mt interface mang tên Promotion (nghĩa là khuyến mãi) để đại diện cho chức năng giảm giá.
      2. Trong interface Promotion, định nghĩa một phương thức promote() và truyền vào tỷ lệ phần trăm giảm giá.
      3. Khi thực hiện giảm gi cho các điện thoại cũ, gọi phương thức promote() thông qua interface Promotion. Điều này sẽ giúp tất cả các điện thoại cũ giảm giá theo tỷ lệ phần trăm được truyền vào.
      
