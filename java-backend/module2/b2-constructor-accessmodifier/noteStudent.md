**TEAMWORK EXERCISE**
**Class, Object & Constructor, Static**

### JAVA BE CLASS

#### Xây dựng một hệ thống quản lý thông tin học viên

**Person**

* ID: private int id;
* Họ tên học viên: private String name;
* Tuổi: private int age; 
* Điểm trung bình: private double avgScore; 

* private static int auto id;
* private static int countStudent; (nhớ tạo static getCountStudent())

---

**Yêu cầu chức năng:**

1. Thêm học viên mới (nhập từ bàn phím)
2. Hiển thị danh sách tất cả học viên
3. Tìm học viên theo tên (có thể nhập 1 phần tên)
4. Hiển thị tổng số học viên (sử dụng biến `static`)
5. Tìm học viên điểm cao nhất
6. Liệt kê học viên có dưới điểm trung bình ( < 5 )
7. Thoát chương trình

---

**Student Class**
* Methods:
    * Constructor (mặc định, đầy đủ tham số)
    * getter, setter
    * Output: @Override hàm toString(): format lại cho đẹp
    * public void input() {
      * name: ko có số và ký tự đặc biệt
      * tuổi: số nguyên dương
      * điểm trung bình (từ 0 đến 10)
      
      }

**StudentManager**
* static ArrayList<Student> students = new ArrayList<>();
* Scanner sc

* method: void checkEmptyList (kiểm tra danh sách Student rỗng hay không)

***1. Thêm học viên mới (nhập từ bàn phím)***
- Tạo đối tượng Student mới
- setter
- add vào Danh sách Student

***2. Hiển thị danh sách tất cả học viên***
- Dùng for duyệt trong Danh sách Student và in ra.
  (Nhớ format cho đẹp nhé!)
- 
***3. Tìm học viên theo tên (có thể nhập 1 phần tên)***
- Tạo biến keyword người dùng nhập
- Tạo ArrayList<?> result : để chứa các Student có tên mà người dùng tìm
- Đồng nhất keyword và name: chuyển về toàn bộ in thường, trim()
- gọi method checkEmptyList
- Dùng for duyệt danh sách Student
- Nếu cùng tên thì add vào mảng result
- return result;

***4. Hiển thị tổng số học viên (sử dụng biến `static`)***
- Student.getCountStudent()

***5. Tìm học viên điểm cao nhất***
- Tạo biến max = -1;
- Tạo ArrayList<?> result : để chứa các Student max điểm
- gọi method checkEmptyList
- Dùng for duyệt danh sách Student
- add vào mảng result
- return result;

***6. Liệt kê học viên có dưới điểm trung bình ( < 5 )***
- Tương tự, xét thêm if trong vòng for

***7. Thoát chương trình***
