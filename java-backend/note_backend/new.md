## ✅ **Vì sao phải dùng từ khóa `new` trong Java?**

### 👉 Vì `new` là từ khóa dùng để **tạo ra một đối tượng mới trong bộ nhớ (heap)**.

Khi bạn viết:

```java
Student s = new Student();
```

Java sẽ làm 3 việc:

1. **Cấp phát vùng nhớ** trong heap để lưu đối tượng `Student`.
2. **Gọi constructor** để khởi tạo đối tượng.
3. **Gán địa chỉ đối tượng** cho biến `s` (biến `s` nằm trong stack, trỏ tới vùng nhớ trong heap).

---

### 🔍 Nếu không dùng `new` thì sao?

Ví dụ:

```java
Student s;
```

Thì chỉ là **khai báo biến tham chiếu**, nhưng **chưa tạo ra đối tượng thực sự**. Biến `s` lúc này đang **trỏ đến `null`**, và nếu bạn cố dùng:

```java
System.out.println(s.name);
```

\=> **Sẽ lỗi `NullPointerException`** vì `s` chưa trỏ đến đối tượng nào cả.

---

### 📦 Tóm lại:

| Từ khóa `new` dùng để làm gì? | Giải thích ngắn gọn                  |
| ----------------------------- | ------------------------------------ |
| 🎯 Tạo đối tượng mới          | Cấp phát bộ nhớ trong heap           |
| 🔧 Gọi constructor            | Khởi tạo giá trị ban đầu             |
| 🧭 Gán địa chỉ cho biến       | Biến trỏ tới vùng nhớ chứa đối tượng |

---

### 🧠 Ví dụ cụ thể:

```java
Student s1 = new Student(); // OK: tạo và khởi tạo đối tượng
Student s2;                 // Chỉ là khai báo biến, chưa có đối tượng

s2.name = "Như"; // ❌ Lỗi vì s2 chưa được gán đối tượng (null)
```

