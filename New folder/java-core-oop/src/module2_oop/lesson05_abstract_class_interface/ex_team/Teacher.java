package ex_team;

import java.math.BigDecimal;
import java.util.Scanner;

public class Teacher extends Person implements ITeacher {
    private String boMon;
    private Double soGioDay;
    final double heSoLuong = 200000;
    Scanner sc = new Scanner(System.in);

    public Teacher() {
    }

    public Teacher(String id, String name, int age, String email, String boMon, Double soGioDay) {
        super(id, name, age, email);
        this.boMon = boMon;
        this.soGioDay = soGioDay;
    }

    public BigDecimal luong() {
        BigDecimal soGio = BigDecimal.valueOf(this.soGioDay);
        BigDecimal heSo = BigDecimal.valueOf(this.heSoLuong);
        return soGio.multiply(heSo);
    }

    public Double getSoGioDay() {
        return soGioDay;
    }

    @Override
    public void input(Scanner sc) {

        super.input(sc); // gọi phương thức input của lớp cha (Person)

        // Nhập bộ môn
        System.out.print("Nhập bộ môn giảng dạy: ");
        this.boMon = sc.nextLine().trim();

        // Nhập số giờ dạy
        while (true) {
            System.out.print("Nhập số giờ dạy: ");
            if (sc.hasNextDouble()) {
                this.soGioDay = sc.nextDouble();
                if (this.soGioDay < 0) {
                    System.out.println("❌ Số giờ không hợp lệ! Phải >= 0.");
                    sc.nextLine(); // clear buffer
                    continue;
                }
                sc.nextLine(); // clear buffer
                break;
            } else {
                System.out.println("❌ Vui lòng nhập số thực!");
                sc.nextLine(); // clear buffer
            }
        }
    }

    public void printHeader() {
        System.out.printf("| %10s | %-20s | %-8s | %-25s | %-15s | %-12s | %-15s |\n",
                "ID", "Họ tên", "Tuổi", "Email", "Bộ môn", "Số giờ", "Lương");
        System.out.println("---------------------------------------------------------------------------------------------"
                + "----------------------------------");
    }


    @Override
    public String toString() {
        return super.toString() + String.format(" | %-15s | %-12.2f | %-15.2f |", boMon, soGioDay, luong());
    }

    @Override
    public double getSalary() {
        return 0;
    }
}
