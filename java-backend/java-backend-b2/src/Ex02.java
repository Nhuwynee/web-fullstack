import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập điểm (0 - 10): ");
        double diem = scanner.nextDouble();

        String giaTri = "Giỏi";
        if (diem < 8 && diem >= 6.5) {
            giaTri = "Khá";
        } else if (diem >= 5) {
            giaTri = "Trung bình";
        } else if (diem < 5) {
            giaTri = "Yếu";
        }
        System.out.println(giaTri);
    }
}
