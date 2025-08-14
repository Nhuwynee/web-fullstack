### ✅ **Xây dựng hàm `add(int index, int value)`, `addFirst(int value)` và `addLast(int value)`. Hàm `remove(), removeFirst(), removeLast()` trong LinkedList**

```java
package ss7_list;

public class MyLinkedList {

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(int value) {
        if (head == null) {
            addFirst(value);
        } else {
            Node newNode = new Node(value);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Lỗi: Ngoài phạm vi!");
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node temp = head;

            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }

            Node newNode = new Node(value);
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }

    public Integer removeFirst() {
        if (head == null) {
            return null;
        }

        if (size == 1) {
            head = null;
            tail = null;
        }

        Node temp = head;
        head = head.next;
        size--;
        return temp.value;
    }

    public Integer removeLast() {
        if (head == null) {
            return null;
        }

        if (size == 1) {
            head = null;
            tail = null;
        }

        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }

        int value = tail.value;
        tail = temp;
        tail.next = null;

        size--;
        return value;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node temp = head;
        for (int i = 0; i < size; i++) {
            stringBuilder.append(temp.value).append("\t");
            temp = temp.next;
        }
        return stringBuilder.toString();
    }
}
```
---
## ❓Tại sao next phải là kiểu node ?

```java
private static class Node {
    private final int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }
}
```

Biến `next` **phải là kiểu `Node`** vì lý do sau:

---

## ✅ `next` là **liên kết tới nút tiếp theo** trong cấu trúc dữ liệu dạng **danh sách liên kết (Linked List)**.

### 📌 Giải thích:

* Một **Linked List** là chuỗi các phần tử (nút – node) được **nối với nhau bằng con trỏ**.
* Mỗi `Node` thường chứa 2 phần:

  1. **Giá trị (`value`)**
  2. **Tham chiếu tới node kế tiếp (`next`)**

### 👉 Vì vậy, `next` phải là **kiểu `Node`**, để nó có thể **trỏ tới một node khác**.

---

### 🔁 Mô hình hóa một danh sách liên kết đơn giản:

```
Node1 → Node2 → Node3 → null
```

Trong đó:

* `Node1.next` → trỏ tới `Node2`
* `Node2.next` → trỏ tới `Node3`
* `Node3.next` → `null` (kết thúc)

---

## ❓ Nếu `next` không phải là `Node` thì chuyện gì xảy ra?

* Ví dụ bạn viết `private int next;` → bạn **chỉ có thể lưu số**, **không thể nối các nút lại với nhau**.
* Mất hoàn toàn khả năng **xây dựng chuỗi liên kết động**.

---

## 💡 Tổng kết:

| Thành phần | Kiểu dữ liệu | Ý nghĩa                  |
| ---------- | ------------ | ------------------------ |
| `value`    | `int`        | Dữ liệu lưu trong nút    |
| `next`     | `Node`       | Liên kết tới nút kế tiếp |

> 👉 `next` là kiểu `Node` để **cho phép cấu trúc danh sách liên kết được tiếp nối linh hoạt**, chứ không cố định như mảng.

---