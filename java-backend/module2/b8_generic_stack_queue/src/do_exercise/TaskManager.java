package do_exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TaskManager<E> {
    Stack<E> tasksStack = new Stack<>();
    Queue<E> tasksQueue = new LinkedList<>();
    Stack<E> redoStack = new Stack<>();

    // 1. Tạo các method pushToStack(), peekStack(), popFromStack(), isStackEmpty()

    public void pushToStack(E element) {
        tasksStack.push(element);
        redoStack.clear();
    }

    public E peekStack() {
        return tasksStack.peek();
    }

    public E popFromStack() {
        E task = tasksStack.pop();
        redoStack.push(task);
        return task;
    }

    public boolean isStackEmpty() {
        return tasksStack.isEmpty();
    }

    // 2. Tạo các method addToQueue(), peekQueue(), pollFromQueue(), isQueueEmpty()
    public void addToQueue(E element) {
        tasksQueue.add(element);
    }

    public E peekQueue() {
        return tasksQueue.peek();
    }

    public E pollFromQueue() {
        return tasksQueue.poll();
    }

    public boolean isQueueEmpty() {
        return tasksQueue.isEmpty();
    }

    /*
    3. Tạo thêm chức năng khôi phục công việc đã xử lý (Redo)
     - Gợi ý:
        * Dùng thêm một Stack<T> redoStack trong TaskManager<T>.
        * Kiểm tra rỗng trước khi redo.
     */

    public E redoTask() {
        if (!redoStack.isEmpty()) {
            E task = redoStack.pop();
            tasksStack.push(task);
            return task;
        } else {
            System.out.println("Không có công việc để khôi phục.");
            return null;
        }
    }

}
