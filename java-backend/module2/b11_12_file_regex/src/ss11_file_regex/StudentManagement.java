package ss11_file_regex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentManagement {
    private static final Logger logger = Logger.getLogger(StudentManagement.class.getName());

    private static final String PATH_STUDENT_FILE = "src/ss11_file_regex/data/student.csv";

    public static void main(String[] args) {
        showList();
        create();
        showList();
    }

    public static List<Student> readFile(String path) {
        File file = new File(path);
        List<Student> students = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine(); // Đọc ra để loại bỏ dòng header

            Student student;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                student = new Student();
                student.setId(data[0]);
                student.setName(data[1]);
                student.setScore(Double.parseDouble(data[2]));

                students.add(student);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi đọc file", e);
        }

        return students;
    }

    public static void showList() {
        List<Student> students = readFile(PATH_STUDENT_FILE);
        for (Student s : students) {
            System.out.println("\nThông tin sinh viên với ID: " + s.getId());
            s.output();
        }
    }

    private static void writeFile(List<Student> students) {
        StringBuilder stringBuilder = new StringBuilder("id,name,score");
        for (Student s : students) {
            stringBuilder.append("\n").append(s.toString());
        }

        File file = new File(PATH_STUDENT_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, ">>>>> Lỗi khi ghi file", e);
        }
    }

    public static void create() {
        List<Student> students = readFile(PATH_STUDENT_FILE);

        Student student = new Student();
        student.input();
        students.add(student);

        writeFile(students);
    }


    /// Hãy viết phương thức cập nhật dữ liệu một sinh viên theo ID
    public static void update() {

    }

    /// Hãy viết phương thức xóa sinh viên theo ID
    public static void delete() {

    }
}
