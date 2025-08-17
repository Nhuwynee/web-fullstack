package ss9_set_map;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;


public class TreeSetStudy {
    public static void main(String[] args) {
        Set<Integer> integerTreeSet = new TreeSet<>();

        integerTreeSet.add(10);
        integerTreeSet.add(40);
        integerTreeSet.add(20);
        integerTreeSet.add(20);
        integerTreeSet.add(20);
        integerTreeSet.add(30);

        System.out.println(integerTreeSet);


        /// Nếu muốn đảo ngược thứ tự thì sao???
        Set<Integer> integerTreeSet1 = new TreeSet<>(Collections.reverseOrder());

        integerTreeSet1.add(10);
        integerTreeSet1.add(40);
        integerTreeSet1.add(20);
        integerTreeSet1.add(20);
        integerTreeSet1.add(20);
        integerTreeSet1.add(30);

        System.out.println("Đảo ngược: " + integerTreeSet1);


        /// Lưu ý khi sử dụng TreeSet đối với kiểu dữ liệu Student do chúng ta tự tạo ra
        /// -> nếu KHÔNG implements Comparable cho Student hoặc KHÔNG sử dụng Comparator
        /// -> gặp lỗi khi sử dụng TreeSet
//        Set<Student> students = new TreeSet<>(new ScoreASCIdDESCComparator());
        Set<Student> students = new TreeSet<>();
        students.add(new Student(1, "Nguyễn Văn A", 7.4));
        students.add(new Student(2, "Nguyễn Văn B", 6.2));
        students.add(new Student(3, "Nguyễn Văn C", 7.4));
        students.add(new Student(4, "Nguyễn Văn D", 8.6));
        students.add(new Student(5, "Nguyễn Văn E", 5.5));

        for (Student student : students) {
            System.out.println(student);
        }


        /// Cần lưu ý khi compareTo() hoặc compare() trả về 0
        /// -> TreeSet sẽ xem là 2 đối tượng giống nhau -> loại bỏ, không thêm vào Set
    }
}
