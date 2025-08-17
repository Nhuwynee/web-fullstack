## 🎯 **CSS Pseudo-classes**

| **Pseudo-class**   | **Công dụng**                                                                                                     | **Cú pháp**                | **Ví dụ**                                      |
| ------------------ | ----------------------------------------------------------------------------------------------------------------- | -------------------------- | ---------------------------------------------- |
| **`:root`**        | Chọn phần tử gốc của tài liệu (thường là thẻ `<html>`). Hay dùng để định nghĩa **CSS variables** dùng toàn trang. | `:root {…}`                | `css :root {--main-color: #3498db;} `          |
| **`:hover`**       | Áp dụng style khi người dùng **di chuột vào** phần tử.                                                            | `selector:hover {…}`       | `css button:hover {background: orange;} `      |
| **`:active`**      | Áp dụng style khi phần tử đang **được click** (nhấn giữ chuột).                                                   | `selector:active {…}`      | `css button:active {transform: scale(0.95);} ` |
| **`:first-child`** | Chọn phần tử **đầu tiên** trong nhóm các phần tử con cùng cấp.                                                    | `selector:first-child {…}` | `css li:first-child {color: red;} `            |
| **`:last-child`**  | Chọn phần tử **cuối cùng** trong nhóm các phần tử con cùng cấp.                                                   | `selector:last-child {…}`  | `css li:last-child {color: blue;} `            |

---

💡 **Mẹo nhớ nhanh**

* `:root` → gốc của cây DOM (html).
* `:hover` → rê chuột.
* `:active` → bấm giữ.
* `:first-child` → con đầu tiên.
* `:last-child` → con cuối cùng.

---
## 🎯 **CSS Pseudo-elements**

> Dùng để **tạo và định dạng các phần tử ảo** không tồn tại trong HTML nhưng có thể được hiển thị qua CSS.

| **Pseudo-element**   | **Công dụng**                                                          | **Cú pháp**                        | **Ví dụ**                                               |
| -------------------- | ---------------------------------------------------------------------- | ---------------------------------- | ------------------------------------------------------- |
| **`::before`**       | Chèn **nội dung ảo** ngay **trước** nội dung thật của phần tử.         | `selector::before {content: "…";}` | `css h1::before {content: "🔥 ";} `                     |
| **`::after`**        | Chèn **nội dung ảo** ngay **sau** nội dung thật của phần tử.           | `selector::after {content: "…";}`  | `css h1::after {content: " 🚀";} `                      |
| **`::first-letter`** | Chọn **chữ cái đầu tiên** của phần tử (thường dùng cho style văn bản). | `selector::first-letter {…}`       | `css p::first-letter {font-size: 2em; color: red;} `    |
| **`::first-line`**   | Chọn **dòng đầu tiên** của văn bản trong phần tử.                      | `selector::first-line {…}`         | `css p::first-line {font-weight: bold;} `               |
| **`::selection`**    | Chọn phần **văn bản được bôi đen** bởi người dùng.                     | `selector::selection {…}`          | `css p::selection {background: yellow; color: black;} ` |

---

💡 **Mẹo nhớ**:

* `::before` & `::after` → thêm nội dung ảo.
* `::first-letter` → chữ đầu tiên.
* `::first-line` → dòng đầu tiên.
* `::selection` → đoạn được bôi đen.

---
