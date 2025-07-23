import java.util.ArrayList;

public class StudentManager {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Lưu Ngọc Yến Như";
        s1.age = 20;
        s1.hobby = " Coding";

        System.out.println("Thông tin sinh viên 1");
        System.out.println("name: " + s1.name);
        System.out.println("age: " + s1.age);
        System.out.println("hobby: " + s1.hobby);

        Student s2 = new Student();
        s2.input();
        System.out.println("Thông tin sinh viên 2");
        s2.output();

        ArrayList<Student> stds = new ArrayList<>();

        int n = 2;
        Student student;

        for (int i = 0; i < n; i++) {
            System.out.printf("Nhập vào thông tin sinh viên thứ %d: \n", i + 1);
            student = new Student();
            student.input();
            stds.add(student);
        }

        for (int i = 0; i < stds.size(); i++) {
            System.out.printf("Thông tin sinh viên thứ %d: \n", i + 1);
            stds.get(i).output();
        }
    }
}
