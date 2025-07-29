package ex_afternoon;

import java.util.Scanner;

public class Student extends Person {
    double diemTrungBinh;

    public Student() {}

    public Student(String id, String name, int age, String email, double diemTrungBinh) {
        super(id, name, age, email);
        this.diemTrungBinh = diemTrungBinh;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | DiemTB: %.2f", diemTrungBinh);
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        // Nhập điểm trung bình
        while (true) {
            System.out.print("Nhập điểm trung bình: ");
            if (sc.hasNextDouble()) {
                this.diemTrungBinh = Double.parseDouble(sc.nextLine());
                if (this.diemTrungBinh < 0 || this.diemTrungBinh > 10) {
                    System.out.println("❌ Điểm trung bình không hợp lệ! Phải từ 0 - 10.");
                    continue;
                }
                break;
            } else {
                System.out.println("❌ Điểm trung bình không hợp lệ! Nhập số nguyên.");
            }
        }
    }

    public void xepLoai() {
        System.out.println("Xếp loại.");
    }
}
