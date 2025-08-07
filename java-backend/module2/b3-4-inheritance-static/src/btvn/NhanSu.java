package btvn;

import java.util.Scanner;

public class NhanSu {
    protected String id;
    protected String name;
    protected int age;
    protected float soGioLam;

    public NhanSu() {

    }

    public NhanSu(String id, String name, int age, float soGioLam) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.soGioLam = soGioLam;
    }

    public void input(Scanner sc) {

        // Nhập tên
        while (true) {
            System.out.print("Nhập tên: ");
            this.name = sc.nextLine().trim();
            if (this.name.matches("[a-zA-ZÀ-Ỹà-ỹ\\s]+")) break;
            else System.out.println("Tên không hợp lệ! Không chứa số hoặc ký tự đặc biệt.");
        }

        // Nhập tuổi
        while (true) {
            System.out.print("Nhập tuổi: ");
            if (sc.hasNextInt()) {
                this.age = sc.nextInt();
                if (this.age < 0) {
                    System.out.println("❌ Tuổi không hợp lệ! Phải >= 0.");
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("❌ Tuổi không hợp lệ! Nhập số nguyên.");
                sc.nextLine();
            }
        }

        // Nhập số giờ làm
        while (true) {
            System.out.print("Nhập số giờ làm: ");
            if (sc.hasNextFloat()) {
                this.soGioLam = sc.nextFloat();
                if (this.soGioLam < 0) {
                    System.out.println("Số giờ làm không hợp lệ! Phải >= 0.");
                    continue;
                }
                sc.nextLine();
                break;

            } else {
                System.out.println("Số giờ làm không hợp lệ! Nhập số thực.");
                sc.nextLine();
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(float soGioLam) {
        this.soGioLam = soGioLam;
    }


    public double tinhLuong() {
        return 0;
    }

    public void hienThiThongTin() {
        System.out.printf("%-5s | %-20s | %-5d | %-10.1f | %.0f%n",
                id, name, age, soGioLam, tinhLuong());
    }
}

