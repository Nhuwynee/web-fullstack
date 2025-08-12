# Bài 1 – Xử lý ngoại lệ ngay tại chỗ bằng try-catch-finally

## Yêu cầu:

#### Viết chương trình đọc một số nguyên từ bàn phím và tính căn bậc hai của số đó:

    1. Nếu người dùng nhập chữ → báo lỗi NumberFormatException.

    2. Nếu số âm → báo lỗi IllegalArgumentException.

    Dù lỗi hay không, vẫn in "Chương trình kết thúc" trong khối finally.

---

# Bài 2 – Sử dụng throw/throws để ném ngoại lệ cho nơi khác xử lý

## Yêu cầu:

#### Viết chương trình mô phỏng việc chia 2 số nguyên:

##### 1. Tạo method divide(int a, int b):

    - Nếu b == 0 → throw ArithmeticException với thông báo "Không thể chia cho 0".
    - Nếu hợp lệ → trả về kết quả chia.

##### 2. Trong main:

    - Nhập a và b từ bàn phím.
    - Gọi divide(a, b) và xử lý ngoại lệ ở main bằng try-catch.

---

# Bài 3 - Ứng dụng quản lý điện thoại

## Yêu cầu:

    Hãy xử lý Exception ở những nơi có thể bị ném ra ngoại lệ khiến chương trình bị chết ở Runtime 