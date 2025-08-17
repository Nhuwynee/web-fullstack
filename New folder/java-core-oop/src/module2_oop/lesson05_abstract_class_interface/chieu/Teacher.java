package chieu;

import java.util.Scanner;

public abstract class Teacher extends Person {
    private double teachingHours;

    public Teacher() {
    }

    public Teacher(String id, String fullName, int age, String email, double teachingHours) {
        super(id, fullName, age, email);
        this.teachingHours = teachingHours;
    }

    public double getTeachingHours() {
        return teachingHours;
    }

    public void setTeachingHours(double teachingHours) {
        this.teachingHours = teachingHours;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        while (true) {
            System.out.print("Nhập số giờ dạy: ");
            if (sc.hasNextDouble()) {
                this.teachingHours = sc.nextDouble();
                if (this.teachingHours < 0) {
                    System.out.println("Số giờ dạy không hợp lệ! Phải >= 0.\n");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("Số giờ dạy không hợp lệ! Nhập số nguyên.\n");
                sc.nextLine();
            }
        }
    }

    public abstract double getSalary();

    @Override
    public String toString() {
        return super.toString()
                + "Số giờ dạy: " + String.format("%.2f", this.teachingHours) + "\n"
                + "Lương: " + String.format("%.2fVND", this.getSalary()) + "\n";
    }
}
