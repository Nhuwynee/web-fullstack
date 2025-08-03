package BaiTapChieu2;
import java.util.Scanner;
public class StudentBE extends Student{
    String ngonNguLapTrinh;

    public StudentBE() {
    }

    public StudentBE(String id, String name, int age, String email, double diemTrungBinh, String ngonNguLapTrinh) {
        super(id, name, age, email, diemTrungBinh);
        this.ngonNguLapTrinh = ngonNguLapTrinh;
    }

    public String getNgonNguLapTrinh() {
        return ngonNguLapTrinh;
    }

    public void setNgonNguLapTrinh(String ngonNguLapTrinh) {
        this.ngonNguLapTrinh = ngonNguLapTrinh;
    }
    @Override
    public String toString() {
        return super.toString() + String.format("  %-20s|", ngonNguLapTrinh);
    }

    public void printHeaderSBE() {
        System.out.printf("| %10s | %-20s | %-8s | %-25s | %-10s | %-20s |\n",
                "ID", "Họ tên", "Tuổi", "Email", "Điểm TB", "Ngôn ngữ lập trình");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        System.out.print("Nhập ngôn ngữ lập trình: ");
        this.ngonNguLapTrinh = sc.nextLine().trim();
    }

    @Override
    public void xepLoai() {
        if (this.diemTrungBinh >= 7.5) {
            System.out.println("Giỏi");
        } else if (this.diemTrungBinh >= 5) {
            System.out.println("Khá");
        } else {
            System.out.println("Trung bình");
        }
    }
}
