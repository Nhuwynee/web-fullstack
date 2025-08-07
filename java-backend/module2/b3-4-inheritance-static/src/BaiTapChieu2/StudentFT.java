package BaiTapChieu2;
import java.util.Scanner;
public class StudentFT extends Student {
    int soDuAnThamGia;

    public StudentFT() {
    }

    public StudentFT(String id, String name, int age, String email, double diemTrungBinh, int soDuAnThamGia) {
        super(id, name, age, email, diemTrungBinh);
        this.soDuAnThamGia = soDuAnThamGia;
    }

    public int getSoDuAnThamGia() {
        return soDuAnThamGia;
    }

    public void setSoDuAnThamGia(int soDuAnThamGia) {
        this.soDuAnThamGia = soDuAnThamGia;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" %-20s |", soDuAnThamGia);
    }

    public void printHeaderSFT() {
        System.out.printf("| %-10s | %-20s | %-8s | %-25s | %-10s | %-20s |\n",
                "ID", "Họ tên", "Tuổi", "Email", "Điểm TB", "Số dự án tham gia");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        // Nhập số dự án tham gia
        while (true) {
            System.out.print("Nhập số dự án tham gia: ");
            if (sc.hasNextInt()) {
                this.soDuAnThamGia = Integer.parseInt(sc.nextLine());
                if (this.soDuAnThamGia < 0) {
                    System.out.println("❌ Không hợp lệ! Phải >= 0.");
                    continue;
                }
                break;
            } else {
                System.out.println("❌ Không hợp lệ! Nhập số nguyên.");
            }
        }
    }

    @Override
    public void xepLoai() {
        if (this.soDuAnThamGia >= 2 && this.diemTrungBinh >= 8) {
            System.out.println("Giỏi");
        } else if (this.diemTrungBinh >= 6.5) {
            System.out.println("Khá");
        } else {
            System.out.println("Trung bình");
        }
    }
}

