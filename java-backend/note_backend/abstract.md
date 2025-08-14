Rất tuyệt! Dưới đây là phần **giải thích kỹ từng tính chất của `abstract class` trong Java** dựa theo ảnh bạn cung cấp:
![alt text](image-2.png)
---

### ✅ 1. **Không thể tạo được đối tượng**

```java
abstract class Animal { }
Animal a = new Animal(); // ❌ Lỗi
```

#### ❓ Vì sao?

* `abstract class` là **lớp chưa hoàn chỉnh**, có thể chứa phương thức chưa cài đặt (abstract).
* Java không cho phép tạo đối tượng từ một lớp mà có khả năng chứa hành vi chưa được định nghĩa cụ thể.

> 📌 Chỉ khi **class con kế thừa và hiện thực toàn bộ abstract methods**, bạn mới có thể tạo đối tượng từ class con đó.

---

### ✅ 2. **Một lớp chứa method `abstract` thì bắt buộc lớp đó phải là `abstract` (ngược lại thì không)**

```java
// ❌ Sai vì class chưa được đánh dấu là abstract
class Shape {
    abstract void draw(); // Lỗi
}
```

```java
// ✅ Đúng
abstract class Shape {
    abstract void draw();
}
```

#### ❓ Vì sao?

* Một method `abstract` là phương thức **không có thân hàm**, chỉ là định nghĩa.
* Java cần cảnh báo rõ ràng rằng: “class này chưa hoàn thiện” → phải dùng từ khóa `abstract` để bắt buộc các class con override lại.

> ✅ **Ngược lại thì không bắt buộc**: Một `abstract class` **có thể không chứa** bất kỳ method abstract nào, và vẫn hợp lệ.

---

### ✅ 3. **Lớp abstract có thể chứa thuộc tính và method bình thường**

```java
abstract class Animal {
    String name;

    void speak() {
        System.out.println("Animal is speaking...");
    }

    abstract void move(); // bắt buộc lớp con override
}
```

#### ❓ Vì sao?

* `abstract class` là một class thật sự, nên nó **có thể chứa trạng thái (`field`) và hành vi đầy đủ (`method`)**.
* Nó cho phép dùng chung logic cho các lớp con, thay vì bắt tất cả phải tự viết lại.

> 🧠 Điều này làm `abstract class` linh hoạt hơn `interface` (trước Java 8) vì có thể chứa cả phần thực thi.

---

### ✅ 4. **`abstract` không thể đi chung với `final`**

```java
final abstract class A {} // ❌ Sai
```

#### ❓ Vì sao?

* `abstract`: Mong muốn **cho phép lớp khác kế thừa và hoàn thiện hành vi**.
* `final`: Ngược lại, **ngăn không cho lớp khác kế thừa**.

> ⚠️ Hai mục đích này **mâu thuẫn trực tiếp**, nên không được dùng chung.

---

### ✅ 5. **Lớp abstract có thể `extend` từ lớp abstract khác (và không cần override method abstract của lớp cha)**

```java
abstract class Animal {
    abstract void move();
}

abstract class Mammal extends Animal {
    // Không override move() cũng được
}
```

#### ❓ Vì sao?

* Miễn là **class con vẫn là abstract**, thì nó **chưa bắt buộc phải hiện thực tất cả abstract method** từ class cha.
* Java chỉ yêu cầu **lớp cuối cùng không còn abstract** thì **bắt buộc phải override** toàn bộ các phương thức chưa hoàn thiện.

> ✅ Điều này giúp ta **xây dựng nhiều cấp độ trừu tượng**, theo kiểu phân tầng — rất hữu ích trong thiết kế hướng đối tượng.

---

### 🎯 Tóm tắt bảng:

| Tính chất                                     | Vì sao?                                                |
| --------------------------------------------- | ------------------------------------------------------ |
| Không tạo object                              | Vì class chưa hoàn chỉnh                               |
| Chứa method abstract → phải là abstract       | Vì có phương thức chưa định nghĩa                      |
| Có field & method thường                      | Vì là class thật sự, không phải hợp đồng như interface |
| Không đi với `final`                          | Vì trái ngược ý nghĩa kế thừa                          |
| Extend từ abstract khác mà không override hết | Miễn là vẫn là abstract, có thể trì hoãn việc override |

---

Nếu bạn cần **so sánh trực tiếp `interface` vs `abstract class`**, hoặc ví dụ tổng hợp áp dụng cả hai, mình có thể giúp tiếp nhé!
