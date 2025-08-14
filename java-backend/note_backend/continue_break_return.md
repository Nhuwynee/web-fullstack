## ✅ 1. `break` – **Thoát khỏi vòng lặp**

* Dùng để **dừng hoàn toàn vòng lặp** (for, while, do-while).
* Sau `break`, chương trình **nhảy ra khỏi vòng lặp** và tiếp tục chạy bên ngoài vòng lặp đó.

🔸 **Ví dụ:**

```java
for (int i = 0; i < 5; i++) {
    if (i == 3) break;
    System.out.println(i);
}
// Output: 0 1 2
```

---

## ✅ 2. `continue` – **Bỏ qua lần lặp hiện tại**

* Dùng để **bỏ qua phần còn lại của lần lặp hiện tại**, và **chuyển sang lần lặp tiếp theo**.
* Không thoát vòng lặp, chỉ **bỏ qua một vòng duy nhất**.

🔸 **Ví dụ:**

```java
for (int i = 0; i < 5; i++) {
    if (i == 3) continue;
    System.out.println(i);
}
// Output: 0 1 2 4 (bỏ qua 3)
```

---

## ✅ 3. `return` – **Thoát khỏi phương thức**

* Dùng để **kết thúc ngay lập tức một phương thức** (hàm).
* Có thể trả về một giá trị nếu phương thức không phải `void`.

🔸 **Ví dụ:**

```java
public static void sayHi() {
    System.out.println("Xin chào");
    return;
    // Dòng sau return sẽ không được thực thi
}

public static int getNumber() {
    return 10; // trả về giá trị 10
}
```

---

## 🆚 So sánh nhanh:

| Từ khóa    | Tác dụng chính                   | Thoát khỏi gì?                       | Dùng ở đâu?                  |
| ---------- | -------------------------------- | ------------------------------------ | ---------------------------- |
| `break`    | Dừng vòng lặp                    | Vòng lặp hiện tại                    | `for`, `while`, `switch`     |
| `continue` | Bỏ qua phần còn lại của vòng lặp | Không thoát, chỉ bỏ qua lần hiện tại | `for`, `while`               |
| `return`   | Thoát khỏi phương thức           | Phương thức hiện tại                 | Trong bất kỳ phương thức nào |

---

## 🧠 Tổng kết dễ nhớ:

* `break` → **“Bẻ gãy vòng lặp”**
* `continue` → **“Tiếp vòng tiếp theo”**
* `return` → **“Rời khỏi hàm”**
---
---
---
Dưới đây là **một ví dụ Java có cả `break`, `continue` và `return`**, rất dễ hiểu:

---

### ✅ Ví dụ: Tìm số đầu tiên chia hết cho 7 trong mảng, nhưng bỏ qua số âm. Nếu không có thì thoát khỏi hàm (`return`).

```java
public class Demo {
    public static void main(String[] args) {
        int[] numbers = {3, -5, 10, -14, 22, 28, 33};

        findFirstDivisibleBy7(numbers);
        System.out.println("Kết thúc chương trình main.");
    }

    public static void findFirstDivisibleBy7(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Bỏ qua số âm
            if (arr[i] < 0) {
                continue;
            }

            // Nếu chia hết cho 7
            if (arr[i] % 7 == 0) {
                System.out.println("Số đầu tiên chia hết cho 7 là: " + arr[i]);
                break; // Dừng vòng lặp khi đã tìm thấy
            }
        }

        // Nếu không tìm thấy số chia hết cho 7
        if (!containsDivisibleBy7(arr)) {
            System.out.println("Không có số dương nào chia hết cho 7.");
            return; // Thoát khỏi hàm
        }

        System.out.println("Đã kiểm tra xong.");
    }

    public static boolean containsDivisibleBy7(int[] arr) {
        for (int num : arr) {
            if (num >= 0 && num % 7 == 0) return true;
        }
        return false;
    }
}
```

---

### 📌 Giải thích:

| Dòng       | Hành động                                                                        |
| ---------- | -------------------------------------------------------------------------------- |
| `continue` | Bỏ qua số âm, không kiểm tra điều kiện chia hết                                  |
| `break`    | Ngay khi tìm thấy số chia hết cho 7, dừng vòng lặp                               |
| `return`   | Nếu không có số nào phù hợp, thoát luôn khỏi hàm và không in "Đã kiểm tra xong." |

