package ex_team;

import java.util.Scanner;

public abstract class Student extends Person {
    private double avgScore;
    private int sessionNumber;

    public Student() {
    }

    public Student(String id, String fullName, int age, String email, double avgScore, int sessionNumber) {
        super(id, fullName, age, email);
        this.avgScore = avgScore;
        this.sessionNumber = sessionNumber;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập điểm trung bình: ");
            if (sc.hasNextDouble()) {
                this.avgScore = sc.nextDouble();
                if (this.avgScore < 0) {
                    System.out.println("Điểm trung bình không hợp lệ! Phải >= 0.\n");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Điểm trung bình không hợp lệ! Nhập số thực.\n");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Nhập số buổi học: ");
            if (sc.hasNextInt()) {
                this.sessionNumber = sc.nextInt();
                if (this.sessionNumber < 0) {
                    System.out.println("Số buổi học không hợp lệ! Phải >= 0.\n");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Số buổi học không hợp lệ! Nhập số nguyên.\n");
                sc.nextLine();
            }
        }
    }

    public abstract String getClassify();

    public abstract double tuitionFee();

    @Override
    public String toString() {
        return super.toString()
                + "Điểm TB: " + this.avgScore + "\n"
                + "Số buổi học: " + this.sessionNumber + "\n"
                + "Xếp loại: " + this.getClassify() + "\n"
                + "Học phí: " + String.format("%.2fVND", this.tuitionFee()) + "\n";
    }
}
