package ex_team;

import ex_team.custom_exception.NullOrEmptyException;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Course {
    private String id;
    private String name;
    private final Set<Student> students = new LinkedHashSet<>();
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    public Course() {
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
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

    public Set<Student> getStudents() {
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
            try {
                studentOutput.append("\t+").append(student.getId()).append(": ").append(student.getFullName()).append("\n");
            } catch (NullOrEmptyException e) {
                System.out.println(e.getMessage());
            }
        }

        StringBuilder lecturersOutput = new StringBuilder();

        for (Lecturer lecturer : lecturers) {
            try {
                studentOutput.append("\t+").append(lecturer.getId()).append(": ").append(lecturer.getFullName()).append("\n");
            } catch (NullOrEmptyException e) {
                System.out.println(e.getMessage());
            }
        }

        return "Mã lớp học: " + this.id + "\n"
                + "Tên: " + this.name + "\n"
                + "Danh sách lớp học: \n"
                + studentOutput
                + "Danh sách giảng viên: \n"
                + lecturersOutput;
    }

}