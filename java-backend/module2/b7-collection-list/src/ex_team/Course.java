package ex_team;

import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String id;
    private String name;
    private final ArrayList<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = String.format("C-00" + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.matches("[a-zA-Z0-9#+]+")) {
            throw new IllegalArgumentException(">>Error: Tên không được chứa ký tự đặt biệt!");
        }

        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(Student student) {
        this.students.add(student);
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập tên khóa học: ");
            try {
                setName(sc.nextLine().trim());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder studentOutput = new StringBuilder();

        for (Student student : students) {
            studentOutput.append("\t+").append(student.getId()).append(": ").append(student.getFullName()).append("\n");
        }

        return "Mã lớp học: " + this.id + "\n"
                + "Tên: " + this.name + "\n"
                + "Danh sách lớp học: \n"
                + studentOutput;
    }

}