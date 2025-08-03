package chieu;

import java.util.Scanner;

public class Lecturer extends Teacher {
    private String subject;

    public Lecturer() {
    }

    public Lecturer(String id, String fullName, int age, String email, double teachingHours, String subject) {
        super(id, fullName, age, email, teachingHours);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setId(String id) {
        super.setId("GV-00" + id);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập bộ môn giảng dạy: ");
            this.subject = sc.nextLine().trim();
            if (this.subject.matches("[a-zA-ZÀ-Ỹà-ỹ0-9\\s]+")) {
                break;
            } else {
                System.out.println("Tên môn học không hợp lệ! Không ký tự đặc biệt.\n");
            }
        }
    }

    @Override
    public double getSalary() {
        return this.getTeachingHours() * 200000 * 1.1;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Môn giảng dạy: " + this.subject + "\n";
    }
}
