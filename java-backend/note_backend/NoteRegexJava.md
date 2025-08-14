
## 🔰 1. CẤU TRÚC CƠ BẢN CỦA REGEX

| Ký hiệu  | Ý nghĩa                              | Ví dụ                                 |
| -------- | ------------------------------------ | ------------------------------------- |
| `.`      | Bất kỳ ký tự nào (trừ `\n`)          | `a.c` khớp `abc`, `axc`, `a#c`        |
| `^`      | Bắt đầu chuỗi                        | `^abc` khớp chuỗi bắt đầu bằng `abc`  |
| `$`      | Kết thúc chuỗi                       | `abc$` khớp chuỗi kết thúc bằng `abc` |
| `*`      | Lặp 0 hoặc nhiều lần                 | `a*` khớp "", "a", "aaa"              |
| `+`      | Lặp 1 hoặc nhiều lần                 | `a+` khớp "a", "aaa"                  |
| `?`      | Lặp 0 hoặc 1 lần                     | `a?` khớp "", "a"                     |
| `{n}`    | Chính xác n lần                      | `a{3}` khớp "aaa"                     |
| `{n,}`   | Ít nhất n lần                        | `a{2,}` khớp "aa", "aaa", "aaaa"      |
| `{n,m}`  | Từ n đến m lần                       | `a{2,4}` khớp "aa", "aaa", "aaaa"     |
| `[...]`  | Một trong các ký tự bên trong        | `[abc]` khớp "a", "b", "c"            |
| `[^...]` | Bất kỳ ký tự nào **không** nằm trong | `[^abc]` khớp "d", "x", "9"           |
| `(abc)`  | Nhóm ký tự                           | `(ab)+` khớp "ab", "abab"             |
| \`       | \`                                   | OR (hoặc)                             |
| `\\`     | Escape ký tự đặc biệt                | `\\.` để khớp dấu `.` thật            |

---

## 🔠 2. CÁC NHÓM KÝ TỰ THƯỜNG DÙNG

| Regex | Ý nghĩa                            | Ví dụ                   |
| ----- | ---------------------------------- | ----------------------- |
| `\d`  | Ký tự số (0-9)                     | `\d+` khớp "123"        |
| `\D`  | Không phải số                      | `\D+` khớp "abc", "@"   |
| `\w`  | Ký tự chữ cái + số + `_`           | `\w+` khớp "abc123"     |
| `\W`  | Không phải `\w`                    | `\W+` khớp "@!% "       |
| `\s`  | Khoảng trắng (space, tab, newline) | `\s+` khớp khoảng trống |
| `\S`  | Không phải khoảng trắng            | `\S+` khớp từ/câu       |
| `.`   | Bất kỳ ký tự nào (trừ newline)     | `a.c` khớp "abc", "a-c" |

> ⚠️ Trong Java phải **escape 2 lần**, nên `\d` viết là `"\\d"`.

---

## 📘 3. VÍ DỤ THỰC TẾ

| Regex                | Mô tả                   | Ví dụ khớp                                    |
| -------------------- | ----------------------- | --------------------------------------------- |
| `\\d{4}`             | 4 chữ số                | "2024"                                        |
| `[a-zA-Z]+`          | 1 hoặc nhiều chữ cái    | "Hello"                                       |
| `^\\w+@\\w+\\.com$`  | Email đơn giản          | "[abc123@gmail.com](mailto:abc123@gmail.com)" |
| `^\\d{10}$`          | Số điện thoại 10 chữ số | "0123456789"                                  |
| `[a-zA-ZÀ-Ỹà-ỹ\\s]+` | Tên có dấu tiếng Việt   | "Nguyễn Văn A"                                |

---

## 🔧 4. SỬ DỤNG REGEX TRONG JAVA

### ✅ Cách đơn giản:

```java
String s = "abc123";
if (s.matches("[a-zA-Z0-9]+")) {
    System.out.println("Hợp lệ");
}
```

### ✅ Dùng `Pattern` và `Matcher` (chuyên sâu hơn):

```java
import java.util.regex.*;

String input = "user@example.com";
Pattern p = Pattern.compile("^\\w+@\\w+\\.com$");
Matcher m = p.matcher(input);

if (m.matches()) {
    System.out.println("Email hợp lệ");
}
```

---

## 📋 5. MẸO NHỚ NHANH

| Loại         | Regex                | Ghi nhớ        |
| ------------ | -------------------- | -------------- |
| Số           | `\\d+`               | digit          |
| Chữ cái      | `[a-zA-Z]`           | alphabet       |
| Tên có dấu   | `[a-zA-ZÀ-Ỹà-ỹ\\s]+` | tên tiếng Việt |
| Email        | `\\w+@\\w+\\.\\w+`   | đơn giản       |
| Khoảng trắng | `\\s+`               | space          |

---
