import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số thứ tự ngày: ");
        int ngay = scanner.nextInt();
        String ketQua = "";
        switch (ngay) {
            case 1:
                ketQua = "Chủ nhật";
                break;
            case 2, 3, 4, 5, 6, 7:
                ketQua = "Thứ " + ngay;
                break;
            default:
                ketQua = "Số thứ tự ngày không hợp lệ";
                break;
        }

        System.out.println("Tên ngày của stt " + ngay + " là: " + ketQua);
    }
}
