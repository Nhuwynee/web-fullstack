# Bài tập: Trình quản lý công việc (Task Manager)

## Yêu cầu

### Viết chương trình quản lý các công việc (Task) của người dùng, bao gồm 2 chức năng:

#### 1. Lưu trữ và xử lý công việc theo dạng Stack (các công việc xử lý gần nhất nằm trên đầu - như undo).

* **Hướng dẫn:** Tạo các method pushToStack(), peekStack(), popFromStack(), isStackEmpty()

#### 2. Lưu trữ và xử lý công việc theo dạng Queue (các công việc được xử lý theo thứ tự thêm vào - như job queue).

* **Hướng dẫn:** Tạo các method addToQueue(), peekQueue(), pollFromQueue(), isQueueEmpty()

#### 3. Tạo thêm chức năng khôi phục công việc đã xử lý (Redo)

* **Gợi ý:**
    * Dùng thêm một Stack<T> redoStack trong TaskManager<T>.
    * Kiểm tra rỗng trước khi redo.

* **Hướng làm:**
  Dưới đây là **hướng làm chức năng Redo**:

---

#### 4. Tìm và xử lý công việc ưu tiên cao nhất trong Queue

* **Gợi ý:**
    * Duyệt qua toàn bộ Queue để tìm task có priority nhỏ nhất.
    * Sử dụng thêm Queue<T> tạm thời để giữ lại các phần tử còn lại.

#### Lưu ý:

    Sử dụng Generic để viết class quản lý dữ liệu TaskManager<T>, cho phép xử lý bất kỳ loại dữ liệu nào, không chỉ riêng Task.

**File cho trước:** Task.java