**Giải thích chi tiết từng tính chất của `interface` trong Java**, cùng với **lý do sâu xa đằng sau** từng đặc điểm:

---
![alt text](image.png)
### ✅ 1. **Không thể tạo được đối tượng của interface**

```java
Animal a = new Animal(); // ❌ Lỗi
```

#### ❓Vì sao?

* Interface **không có phần cài đặt (implementation)** cho các phương thức — nó **chỉ chứa tên phương thức** (tức là định nghĩa, không có nội dung).
* Interface không thể đại diện cho một **thực thể cụ thể** được, nên **không thể dùng `new` để tạo đối tượng từ nó**.

#### 🎯 So sánh:

* **Class thường:** Có đầy đủ thông tin để tạo đối tượng.
* **Interface:** Giống như **bản thiết kế**, chỉ nói rằng "ai đó phải làm được những gì" nhưng không nói "làm như thế nào".

#### ✅ Khi nào dùng?

Interface thường được dùng để tạo ra các chuẩn, hoặc viết **code hướng abstraction** — ví dụ:

```java
List<String> list = new ArrayList<>();
```

Bạn dùng `List` làm kiểu, nhưng thực tế object là `ArrayList`.

---

### ✅ 2. **Các trường trong interface đều là `public static final`**

Ví dụ:

```java
interface Config {
    int MAX_USER = 100;
}
```

Mặc định Java hiểu:

```java
public static final int MAX_USER = 100;
```

#### ❓Vì sao?

* `final`: Không cho phép thay đổi sau khi gán.
* `static`: Không phụ thuộc vào object nào cả.
* `public`: Interface là hợp đồng công khai, nên mọi thứ phải "lộ diện".

#### 💡 Giải thích sâu:

* Interface không có object → không có instance variable.
* Biến trong interface chỉ có thể là **hằng số chia sẻ** giữa các class — ví dụ như `TIMEOUT`, `VERSION`, v.v.

---

### ✅ 3. **Các phương thức đều là `public abstract`**

Ví dụ:

```java
interface Drawable {
    void draw();
}
```

Thực chất Java hiểu:

```java
public abstract void draw();
```

#### ❓Vì sao?

* Interface chỉ **cam kết rằng class triển khai sẽ cung cấp phương thức đó**, nhưng **không tự làm gì cả**.
* Không có code thân hàm ⇒ không thể private/protected.

#### 💡 Giải thích sâu:

* Interface = **"cam kết về hành vi"**.
* Lớp nào **implement** interface thì **phải override** tất cả các phương thức đó.

> **Từ Java 8** trở đi, interface **có thể có `default` và `static` method** với thân hàm cụ thể, nhưng **các method thông thường vẫn là `public abstract` mặc định**.

---

### ✅ 4. **Không có constructor**

#### ❓Vì sao?

* Constructor dùng để tạo đối tượng.
* Nhưng interface **không có object** → không cần constructor.

#### 💡 Bổ sung:

* Nếu interface có constructor, người ta sẽ kỳ vọng có thể `new Interface()` — điều này **vi phạm bản chất trừu tượng** của interface.

---

### ✅ 5. **Có thể `extend` một hoặc nhiều interface khác**

Ví dụ:

```java
interface A { void a(); }
interface B { void b(); }
interface C extends A, B { void c(); }
```

#### ❓Vì sao?

* Interface là **mô tả hành vi**, nên cho phép kế thừa nhiều "bản mô tả" để mở rộng khả năng.
* **Không có xung đột field/method thực thi** như class (vì method đều abstract), nên **đa kế thừa an toàn**.

#### 💡 Giải thích sâu:

* Java không cho **class kế thừa nhiều class**, để tránh xung đột code.
* Nhưng **interface không chứa code**, nên không có xung đột về thực thi → an toàn để đa kế thừa.

---

### 🧠 Tóm tắt lại (tư duy gốc rễ):

| Tính chất                        | Bản chất              | Vì sao                        |
| -------------------------------- | --------------------- | ----------------------------- |
| Không tạo object                 | Interface là hợp đồng | Không đủ thông tin thực thi   |
| Trường là `public static final`  | Không có instance     | Chỉ dùng để khai báo hằng     |
| Phương thức là `public abstract` | Cam kết hành vi       | Không cài đặt → phải abstract |
| Không có constructor             | Không có object       | Constructor không có ý nghĩa  |
| Extend nhiều interface           | Mô tả hành vi         | Không có xung đột code        |

---
✅✅✅✅✅✅✅
![alt text](image-1.png)
Rất hay! Từ **Java 8**, interface được nâng cấp và cho phép có **`default` method** và **`static` method\`**, giúp **interface không còn hoàn toàn “trừu tượng”** như trước nữa. Dưới đây là giải thích kỹ từng loại:

---

## ✅ 1. `default` method trong interface (Java 8+)

### 📌 Mục đích:

Cho phép **cung cấp cài đặt sẵn (implementation)** trong interface mà **không làm ảnh hưởng** đến các class đã implement interface đó.

### 📖 Cú pháp:

```java
interface MyInterface {
    default void sayHello() {
        System.out.println("Hello from default method");
    }
}
```

### 🔍 Ví dụ đầy đủ:

```java
interface Greeting {
    default void sayHi() {
        System.out.println("Hi from Greeting");
    }
}

class EnglishGreeting implements Greeting {
    // Không override cũng được, vì interface đã có default method
}

class VietnameseGreeting implements Greeting {
    @Override
    public void sayHi() {
        System.out.println("Xin chào từ VietnameseGreeting");
    }
}
```

### ✅ Khi chạy:

```java
Greeting eg = new EnglishGreeting();
eg.sayHi(); // Hi from Greeting

Greeting vg = new VietnameseGreeting();
vg.sayHi(); // Xin chào từ VietnameseGreeting
```

---

### ❓ Vì sao Java cho phép `default` method?

Trước Java 8, nếu bạn muốn thêm method mới vào 1 interface đã có hàng chục class implement thì bạn **phải sửa TẤT CẢ các class** đó.

> 👉 Với `default`, bạn có thể **bổ sung hành vi mới** mà không ảnh hưởng đến các class cũ.

---

## ✅ 2. `static` method trong interface (Java 8+)

### 📌 Mục đích:

Cho phép **viết method tiện ích (utility method)** gắn liền với interface mà **không cần object**.

### 📖 Cú pháp:

```java
interface MyInterface {
    static void info() {
        System.out.println("Interface static method");
    }
}
```

### 🔍 Cách gọi:

```java
MyInterface.info(); // ✅ Gọi trực tiếp qua tên interface
```

### ❗ Không được gọi từ object:

```java
MyInterface obj = new SomeImplementation();
obj.info(); // ❌ Lỗi! static method chỉ gọi qua tên interface
```

---

### 📌 Tóm tắt sự khác nhau:

| Đặc điểm                  | `default` method              | `static` method                    |
| ------------------------- | ----------------------------- | ---------------------------------- |
| Có thân hàm không?        | ✅ Có                          | ✅ Có                               |
| Override được không?      | ✅ Có thể override trong class | ❌ Không override được              |
| Gọi từ object được không? | ✅ Được                        | ❌ Không, chỉ gọi qua tên interface |
| Dùng để làm gì?           | Cung cấp mặc định cho method  | Viết hàm tiện ích chung (utility)  |

---

### 🔎 Ví dụ dùng chung:

```java
interface MathHelper {
    static int square(int x) {
        return x * x;
    }

    default void description() {
        System.out.println("Interface hỗ trợ toán học cơ bản");
    }
}
```

---

### ✅ Tại sao vẫn giữ interface mà không dùng abstract class?

* Interface có thể **đa kế thừa** → vẫn linh hoạt.
* `default` và `static` method giúp **gần như thay thế được abstract class** trong nhiều tình huống đơn giản.

---

Nếu bạn muốn ví dụ kết hợp `default` + `static` + `implements`, hoặc so sánh với abstract class mới có `private method` (Java 9), mình có thể làm tiếp nhé!
