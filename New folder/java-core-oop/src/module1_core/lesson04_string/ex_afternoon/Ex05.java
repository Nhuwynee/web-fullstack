package ex_afternoon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Ex05 {
    /*
            Viết chương trình tạo mã hóa đơn tự động gồm:
            Prefix theo loại hóa đơn (ví dụ: "SALE", "REFUND",…)
            Thời gian hiện tại theo định dạng yyyyMMdd_HHmmss
            Random 4 chữ cái A-Z
            Ví dụ:
            Input: Loại hóa đơn = "SALE"
            Output: SALE_20250711_123015_XKDM
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập loại hóa đơn: ");
        String loaiHD = sc.nextLine();
        StringBuilder output = new StringBuilder();

        if (loaiHD.trim().isEmpty()) {
            System.out.println("Loại hóa đơn không được để trống!");
            return;
        }
        output.append(loaiHD.toUpperCase(Locale.ROOT)).append("_");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_");
        output.append(now.format(formatter));

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rd = new Random();

        for (int i = 0; i < 4; i++) {
            int index = rd.nextInt(letters.length());
            output.append(letters.charAt(index));
        }

        System.out.println("Hóa đơn: " + output);
    }
}
