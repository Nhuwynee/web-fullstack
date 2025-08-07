package ex_afternoon;

import java.math.BigDecimal;
import java.util.Scanner;

public class Teacher extends  Person{
    private String boMon;
    private Double soGioDay;
    final double heSoLuong = 200000;
    Scanner sc = new Scanner(System.in);

    public Teacher() {
    }

    public Teacher(String id,String name, int age, String email, String boMon, Double soGioDay) {
        super(id,name, age, email);
        this.boMon = boMon;
        this.soGioDay = soGioDay;
    }
    public BigDecimal luong(double soGioDay){
        BigDecimal luongGiangVien = BigDecimal.valueOf(soGioDay * heSoLuong);
        return luongGiangVien;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);

        System.out.print("Nhập bộ môn giảng dạy: ");
        this.boMon = sc.nextLine().trim();

        while (true) {
            System.out.print("Nhập số giờ dạy: ");
            if (sc.hasNextDouble()) {
                this.soGioDay = sc.nextDouble();
                if (this.soGioDay < 0) {
                    System.out.println("❌ Số giờ không hợp lệ! Phải >= 0.");
                    sc.nextLine();
                    continue;
                }
                sc.nextLine();
                break;
            } else {
                System.out.println("❌ Vui lòng nhập số thực!");
                sc.nextLine();
            }
        }
    }
    public  void printHeader() {
        System.out.printf("| %-6s | %-20s | %-8s | %-25s | %-15s | %-12s | %-15s |\n",
                "ID", "Họ tên", "Tuổi", "Email", "Bộ môn", "Số giờ", "Lương");
        System.out.println("---------------------------------------------------------------------------------------------"
                + "----------------------------------");
    }


    @Override
    public String toString() {
        return String.format(
                "%-5s | %-20s | %-5d | %-25s | %-15s | %-10.2f | %-15s",
                getId(), getName(), getAge(), getEmail(), boMon, soGioDay, luong(soGioDay).toString()
        );
    }


}

