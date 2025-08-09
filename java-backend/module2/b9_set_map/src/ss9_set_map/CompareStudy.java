package ss9_set_map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CompareStudy {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Nguyễn Văn A", 9.2);
        Student s2 = new Student(2, "Nguyễn Văn B", 9.5);

        // Dev cần tự định nghĩa việc so sánh, điểm lớn hay id lớn v.v. ở class Student
        // -> cần implements interface Comparable cho class Student

//        if (s1.compareTo(s2) > 0) {
//            System.out.println("s1 > s2");
//        } else if (s1.compareTo(s2) < 0) {
//            System.out.println("s1 < s2");
//        } else {
//            System.out.println("s1 = s2");
//        }


        /// Sử dụng cho việc sắp xếp
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Nguyễn Văn A", 7.4));
        students.add(new Student(2, "Nguyễn Văn B", 6.2));
        students.add(new Student(3, "Nguyễn Văn C", 7.4));
        students.add(new Student(4, "Nguyễn Văn D", 8.6));
        students.add(new Student(5, "Nguyễn Văn E", 5.5));

         /// Hãy sắp xếp theo điểm tăng dần
        System.out.println("=== Trước khi sắp xếp ===");
        for (Student student : students) {
            System.out.println(student);
        }

        Collections.sort(students);
        students.sort(Collections.reverseOrder()); // giảm dần
        System.out.println("=== Sau khi sắp xếp ===");
        for (Student student : students) {
            System.out.println(student);
        }


        /// Trường hợp cần sử dụng Comparator
        // Tạo Menu:
        // 1. Sắp xếp theo điểm tăng dần
        // 2. Sắp xếp theo điểm tăng dần, nếu điểm bằng nhau thì sắp xếp theo id giảm dần
        Scanner sc = new Scanner(System.in);
        System.out.println("===== Lựa chọn sắp xếp =====");
        System.out.println("1. Sắp xếp theo điểm tăng dần");
        System.out.println("2. Sắp xếp theo điểm tăng dần, nếu điểm bằng nhau thì sắp xếp theo id giảm dần");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choose = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 1:
                Collections.sort(students);
                break;
            case 2:
//                Collections.sort(students, new ScoreASCIdDESCComparator());
                students.sort(new ScoreASCIdDESCComparator());
                break;
        }

        System.out.println("=== Sau khi sắp xếp ===");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
