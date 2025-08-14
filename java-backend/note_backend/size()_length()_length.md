Trong Java:

### ✅ `length`, `length()`, và `size()` khác nhau thế nào?

| Cách dùng  | Dùng cho kiểu dữ liệu nào?                                            | Trả về gì?                  | Ghi chú                                                           |
| ---------- | --------------------------------------------------------------------- | --------------------------- | ----------------------------------------------------------------- |
| `length`   | Mảng (`int[]`, `String[]`, ...)                                       | Số phần tử trong mảng       | Là một **thuộc tính**, không phải phương thức (không có dấu `()`) |
| `length()` | Đối tượng `String`                                                    | Số ký tự trong chuỗi        | Là **phương thức**                                                |
| `size()`   | Các lớp Collection (ví dụ: `ArrayList`, `HashSet`, `LinkedList`, ...) | Số phần tử trong collection | Là **phương thức**                                                |

---

### 🔍 Ví dụ minh họa:

```java
// 1. length với mảng
int[] arr = {1, 2, 3, 4};
System.out.println(arr.length); // 👉 Kết quả: 4

// 2. length() với String
String s = "Hello";
System.out.println(s.length()); // 👉 Kết quả: 5

// 3. size() với ArrayList
ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
System.out.println(list.size()); // 👉 Kết quả: 2
```

---

### 📌 Ghi nhớ:

* `length` 👉 dùng cho mảng
* `length()` 👉 dùng cho `String`
* `size()` 👉 dùng cho các Collection như `ArrayList`, `Set`, `Map`...

