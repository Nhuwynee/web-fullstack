## Key

- Dev:
  code -> src -> server
  |
  app

CI: là tích hợp liên tục, chạy dc ở local đã

---

## Server, VPS và Cloud Computing

#### Server local: gồm server vật lý và server ảo hóa

- Vai trò:
  - Lưu trữ DLieu
  - Xử lý yêu cầu
  - Cung cáp dịch vụ
  - Bảo mật và quản lý

### VPS:

- Khái niệm : là một máy chủ ảo được tạo ra từ 1 máy chủ vật lý
- Hoạt động: dựa trên công nghệ ảo hóa
- Cho phép chia 1 server vật lý thành nhiều server ảo độc lập

---

### Ưu và nhược điểm của VPS so vs server vật lý

#### Ưu:

- chi phí hiệu quả
- Linh hoạt và khả năng mở rộng (nếu server local muốn mở rộng có thể phải thay máy luôn)
- độc lập
- kiểm soát toàn diện
- bảo mật tốt hơn

#### Nhược

- hiệu suất
- yêu cầu kiến thức kĩ thuật
- ko hoàn toàn độc lập
- khả năng mở rộng hạn chế

---

### Cloud Computing

- Khái niệm: là mô hình sử dụng tài nguyên, được cung cấp qua internet, dữ liệu và phần mềm chạy trên 1 hạ tầng phân tán nhiều máy chủ

* IaaS: cũng như VPS nhưng mà ở hệ sinh thái khác, linh hoạt, có thể thuê theo giờ. Không dùng nữa thì tắt, bật lại thì cộng thêm giờ vào.
* PaaS: giúng như có VPS database, thì mình vào tạo tk, mk mà tick những quyền j đó, rồi cop link đó qua cho ngkhac kèm thông tin user/ mk thì ngkhac có thể vô được..
* SaaS: ví dụ mình có mail, mình có mail bạn và mình chọn gửi tin nhắn qua mail bạn

---

### Câu hỏi

#### 1. Bạn mong đợi ưu điểm nào khi dùng Cloud/VPS so với dùng máy tính cá nhân ?

1. **Mở rộng linh hoạt** 📈
   → Khi cần nhiều CPU, RAM, dung lượng thì tăng ngay; khi ít nhu cầu thì giảm lại → tiết kiệm chi phí.

2. **Truy cập mọi lúc, mọi nơi** 🌍
   → Chỉ cần có Internet là dùng được trên nhiều thiết bị (PC, laptop, điện thoại).

3. **Độ tin cậy cao** 🔄
   → Dữ liệu và ứng dụng được lưu trên nhiều máy chủ phân tán, ít bị mất dữ liệu hơn máy cá nhân khi hỏng ổ cứng.

4. **Bảo mật & sao lưu tự động** 🔐
   → Có cơ chế backup, cập nhật bảo mật từ nhà cung cấp, an toàn hơn tự lưu trên máy cá nhân.

5. **Có các dịch vụ có sẵn** 🔐

---

## Tổng kết

### **Khái niệm**

- **Server Local (Máy chủ cục bộ)**: Máy chủ vật lý đặt ngay tại công ty/phòng làm việc. Doanh nghiệp **tự mua, tự cài đặt, tự bảo trì**.
- **VPS (Virtual Private Server – Máy chủ ảo riêng)**: Máy chủ ảo được tạo ra bằng cách chia nhỏ tài nguyên từ **một server vật lý**. Mỗi VPS hoạt động độc lập, có hệ điều hành riêng.
- **Cloud Computing (Điện toán đám mây)**: Cụm nhiều server phân tán toàn cầu, cung cấp tài nguyên CNTT (máy chủ, lưu trữ, phần mềm) trên **hạ tầng phân tán nhiều server** qua Internet, có khả năng mở rộng, backup và quản lý tự động.

---

### **Bảng so sánh**

| Tiêu chí              | **Server Local** 🖥️                            | **VPS** 🍰                                                | **Cloud Computing** ☁️                                  |
| --------------------- | ---------------------------------------------- | --------------------------------------------------------- | ------------------------------------------------------- |
| **Hạ tầng**           | Máy chủ vật lý riêng, đặt tại chỗ              | Máy chủ ảo chạy trên 1 server vật lý                      | Cụm nhiều server phân tán toàn cầu                      |
| **Chi phí đầu tư**    | Cao (mua phần cứng, bảo trì)                   | Trung bình (trả tiền theo gói VPS)                        | Linh hoạt (trả theo mức sử dụng, thường rẻ khi mở rộng) |
| **Quản lý – bảo trì** | Tự lo toàn bộ (phần cứng, mạng, điện, bảo mật) | Nhà cung cấp lo phần cứng, bạn quản lý phần mềm trong VPS | Nhà cung cấp lo gần hết (hạ tầng, backup, bảo mật)      |
| **Khả năng mở rộng**  | Khó, phải mua thêm máy                         | Giới hạn theo tài nguyên server gốc                       | Rất linh hoạt, tăng/giảm tức thì                        |
| **Độ tin cậy**        | Thấp, hỏng là dừng                             | Vừa phải, phụ thuộc server vật lý                         | Cao, có dự phòng và tự động chuyển đổi                  |
| **Truy cập**          | Nội bộ (hoặc mở cổng ra ngoài)                 | Truy cập qua Internet                                     | Truy cập mọi nơi qua Internet                           |
| **Ví dụ thực tế**     | Máy chủ công ty để chạy phần mềm kế toán       | Thuê VPS để chạy web riêng                                | Dùng Google Cloud, AWS, Azure để chạy ứng dụng          |

---

👉 Tóm lại:

- **Server Local** 🏠: Công ty **mua hẳn 1 cái máy chủ** đặt ở văn phòng để lưu dữ liệu và chạy phần mềm.
- **VPS** 🍰: Công ty **thuê 1 VPS** để chạy website → thực ra nó chỉ là 1 “máy ảo” được chia từ 1 server lớn.
- **Cloud Computing** ☁️: Công ty **dùng Google Drive/AWS/Azure** để lưu trữ, chạy ứng dụng → tài nguyên nằm trên cả một hệ thống phân tán, có thể tăng/giảm theo nhu cầu.

👉 Cực ngắn gọn:

- **Local** = tự mua máy để chạy.
- **VPS** = thuê 1 phần máy chủ.
- **Cloud** = thuê dịch vụ trên hạ tầng phân tán, linh hoạt.

---

### Xem trước bài buổi sau:

- Cài đặt và sử dụng Tera Term để SSH từ Windows
- Cài đặt công cụ: curl, wget, git - Giúp tải file, tương tác HTTP và quản lý mã nguồn
- Cấu hình nâng cao SSH trên server
