package ss9_set_map;

import java.util.HashSet;
import java.util.Set;


public class HashSetStudy {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        Set<Integer> integerSet = new HashSet<>(); // Mặc định -> initialCapacity: 16, loadFactor: 0.75
        Set<Integer> integerSet1 = new HashSet<>(20, 2.5F);

        integerSet.add(10);
        integerSet.add(20);
        integerSet.add(30);
        integerSet.add(40);

        System.out.println(integerSet); // Hãy cùng xem thứ tự trong mảng???


        /// Để duyệt các phần tử trong HashSet thì có thể dùng for truyền thống được ko???
        for (int i = 0; i < integerSet.size(); i++) {
//            System.out.println(integerSet.get(i));
        }

        for (Integer item : integerSet) {
            System.out.println(item);
        }


        /// add thêm phần tử trùng lặp thì sao???
        integerSet.add(20);
        integerSet.add(20);
        System.out.println(integerSet);
        System.out.println("Size: " + integerSet.size()); // Kết quả???


        /// Thử kiểu dữ liệu Student do chúng ta tự thiết kế ra
        /// với 2 đối tượng new student có thông tin giống nhau
        Set<Student> studentSet = new java.util.HashSet<>();

        studentSet.add(new Student(1, "Nguyễn Văn A", 9.5));
        studentSet.add(new Student(1, "Nguyễn Văn A", 9.5));

        for (Student item : studentSet) {
            System.out.println(item);
            System.out.println(item.hashCode());
        }
        System.out.println("Kích thước: " + studentSet.size()); // Kết quả??? và Tại sao???
        // Cần xem lại lưu ý đối với kiểu dữ liệu Student do chúng ta tự tạo ra
    }
}
