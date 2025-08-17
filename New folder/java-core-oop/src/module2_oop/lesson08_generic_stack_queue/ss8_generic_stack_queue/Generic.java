package ss8_generic_stack_queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Generic {
    public static void main(String[] args) {
        /// Ví dụ KHÔNG sử dụng Generic
        /// Hãy xem cách java tạo generic cho List
        List list = new ArrayList(); // -> không generic
        list.add(10);
        list.add("10"); // -> cũng cho phép add element dù khác kiểu dữ liệu
        // Vì không khai báo kiểu cho các element trong List
        // Vậy kiểu dữ liệu của các element thuộc list này là gì???
        // Java sẽ gán kiểu cho các element này là ...


        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
//            sum += list.get(i); // -> gặp lỗi không khớp kiểu dữ liệu -> cần ép kiểu
            sum += (Integer) list.get(i); // Nhưng nếu ép kiểu -> gặp lỗi runtime???
            // Vậy giải pháp để tránh lỗi???
            // -> cần kiểm tra gì???

        }

        System.out.println("Tổng: " + sum); // Kết quả???


        /// Ví dụ CÓ sử dụng Generic
        /// Nhờ đâu mà ta có thể khai báo kiểu cho List???
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
//        list1.add("10"); // -> Lập tức báo lỗi sai kiểu dữ liệu ở compile time

        for (int i = 0; i < list1.size(); i++) {
            sum += list1.get(i); // Không cần phải ép kiểu và kiểm tra lỗi phức tạp như trường hợp không dùng Generic
        }

        System.out.println("Tổng list1: " + list1);


        /// Hãy thử tạo lớp Generic đơn giản
        /// Sử dụng lớp Animal với kiểu generic tự tạo
        Animal<String> a1 = new Animal<>();
        a1.add("hello");
        a1.add("123");

        Animal<Integer> a2 = new Animal<>();
        a2.add(456);
        a2.add(789);

        PrintUtil.printArray(a1);
        PrintUtil.printArray(a2);
    }
}


/// Tự tạo một lớp Generic
class Animal<E> implements Iterable<E> {
    private final List<E> values = new ArrayList<>();

    public void add(E value) {
        values.add(value);
    }

    public List<E> getValues() {
        return values;
    }


    /// Vì đã implements interface Iterable<T> cho Animal<T>
    /// -> cần triển khai phương thức trừu tượng iterator() của interface Iterator
    @Override
    public Iterator<E> iterator() {
        return values.iterator();
    }
}


class PrintUtil {
    public static <E> void printArray(Animal<E> animal) {
//        for (E item : animal.getValues()) {
//            System.out.println(item);
//        }

        /// Có thể lặp cho đối tượng animal không???
        for (E item : animal) {// animal đang là kiểu Animal -> không phải 1 đối tượng có thể lặp (Iterable)
            // -> Cần implements interface Iterable<T> cho Animal<T>
            System.out.println(item);
        }
    }
}
