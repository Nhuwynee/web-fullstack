package chieu;

import java.util.ArrayList;
import java.util.Scanner;

public class TeachingAssistant extends Teacher {
    private int practiceSessions;
    private final ArrayList<Lecturer> lecturers = new ArrayList<>();

    public TeachingAssistant() {
    }

    public TeachingAssistant(String id, String fullName, int age, String email, double teachingHours, int practiceSessions) {
        super(id, fullName, age, email, teachingHours);
        this.practiceSessions = practiceSessions;
    }

    public int getPracticeSessions() {
        return practiceSessions;
    }

    public void setPracticeSessions(int practiceSessions) {
        this.practiceSessions = practiceSessions;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void addLecture(Lecturer lecturer) {
        this.lecturers.add(lecturer);
    }

    public void removeLecture(Lecturer lecturer) {
        this.lecturers.remove(lecturer);
    }

    @Override
    public void setId(String id) {
        super.setId("TG-00" + id);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập số buổi thực hành: ");
            if (sc.hasNextInt()) {
                this.practiceSessions = sc.nextInt();
                if (this.practiceSessions < 0) {
                    System.out.println("Số buổi thực hành không hợp lệ! Phải >= 0.");
                    sc.nextLine();
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Vui lòng nhập số nguyên!");
                sc.nextLine();
            }
        }
    }

    @Override
    public double getSalary() {
        return this.getTeachingHours() * 100000;
    }

    public String getIdTeacher() {
        StringBuilder output = new StringBuilder();

        for (Lecturer lecturer : lecturers) {
            output.append(lecturer.getId()).append(" ");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return super.toString()
                + "Giảng viên hỗ trợ: " + getIdTeacher() + "\n";
    }
}
