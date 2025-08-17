package module1_core.lesson03.ex_afternoon;

import java.util.Scanner;

public class TinhTienThoi {
    public static boolean validateData(int giaTien, int tongTien) {
        if (giaTien <= 0 || tongTien <= 0) {
            return false;
        }
        return tongTien >= giaTien;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] menhGia = {500000, 200000, 100000, 50000, 20000, 10000, 5000, 2000, 1000};

        System.out.print("Nhập vào giá tiền của món đồ: ");
        int giaTien = sc.nextInt();

        System.out.print("Nhập vào giá tiền khác đưa: ");
        int tongTien = sc.nextInt();

        if (!validateData(giaTien, tongTien)) {
            System.out.println("Dữ liệu nhập vào không chính xác!");
            return;
        }

        int tienThoi = tongTien - giaTien;

        if (tienThoi == 0) {
            System.out.println("Không cần thối tiền!");
            return;
        }

        for (int menh : menhGia) {
            int soTo = tienThoi / menh;
            if (soTo > 0) {
                System.out.println(soTo + " tờ " + menh + "đ");
                tienThoi -= soTo * menh;
            }
        }
    }
}
