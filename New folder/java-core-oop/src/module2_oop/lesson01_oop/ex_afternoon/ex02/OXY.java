package ex_afternoon.ex02;

import java.util.IllegalFormatConversionException;
import java.util.Scanner;

public class OXY {
    double x;
    double y;

    public void input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Nhập x: ");
                x = Double.parseDouble(sc.nextLine());
                break;
            } catch (IllegalFormatConversionException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập y: ");
                y = Double.parseDouble(sc.nextLine());
                break;
            } catch (IllegalFormatConversionException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }
    }

    public void output() {
        System.out.printf("(x, y) = (%.2f, %.2f)", x, y);
    }

    public static double distance(OXY o1, OXY o2) {
        return Math.sqrt(Math.pow(o2.x - o1.x, 2) + Math.pow(o2.y - o1.y, 2));
    }

}
