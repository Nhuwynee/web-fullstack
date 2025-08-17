## 🎯 **Các hàm trong CSS**

| **Hàm**                 | **Công dụng**                                                                                                      | **Cú pháp**                                                                           | **Ví dụ**                                                             |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------- | --------------------------------------------------------------------- |
| **`var()`**             | Dùng để **lấy giá trị của biến CSS** (custom property). Thường dùng để dễ thay đổi màu sắc, font size...           | `var(--tên-biến, giá-trị-dự-phòng)`<br>*(giá trị dự phòng là optional)*               | `css :root {--main-color: #3498db;} div {color: var(--main-color);} ` |
| **`linear-gradient()`** | Tạo **màu chuyển sắc** tuyến tính (linear gradient).                                                               | `linear-gradient(hướng, màu1, màu2, ...)`<br>Hướng: `to right`, `45deg`, `to bottom`… | `css div {background: linear-gradient(to right, red, yellow);} `      |
| **`rgba()`**            | Xác định **màu** bằng giá trị **Red, Green, Blue + Alpha** (độ trong suốt).                                        | `rgba(đỏ, xanh-lá, xanh-dương, alpha)`<br>Alpha từ `0` (trong suốt) → `1` (đậm đặc).  | `css div {background-color: rgba(255, 0, 0, 0.5);} `                  |
| **`rgb()`**             | Xác định **màu** bằng giá trị **Red, Green, Blue**. Không có alpha (trong suốt).                                   | `rgb(đỏ, xanh-lá, xanh-dương)`<br>Mỗi giá trị từ `0` đến `255`.                       | `css div {color: rgb(34, 139, 34);} `                                 |
| **`calc()`**            | Tính toán **giá trị CSS động** (có thể cộng, trừ, nhân, chia).                                                     | `calc(biểu-thức)`<br>Có thể kết hợp đơn vị `%`, `px`, `em`…                           | `css div {width: calc(100% - 50px);} `                                |
| **`attr()`**            | Lấy giá trị của **thuộc tính HTML** và dùng trong CSS (thường dùng với `content` trong `::before` hoặc `::after`). | `attr(tên-thuộc-tính)`                                                                | `css a::after {content: " (" attr(href) ")";} `                       |

---

💡 **Ghi nhớ nhanh**:

* `var()` → lấy biến.
* `linear-gradient()` → màu chuyển sắc.
* `rgb()` / `rgba()` → màu số học.
* `calc()` → tính toán kích thước.
* `attr()` → lấy dữ liệu từ HTML attribute.

