package ex_afternoon.ex02;

import java.util.Scanner;

public class OXY {
    int x;
    int y;

    public void input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Nhập x: ");
                x = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập y: ");
                y = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }
    }

    public void output() {
        System.out.printf("(x, y) = (%d, %d)", x, y);
    }

    public static double distance(OXY o1, OXY o2) {
        return Math.sqrt(Math.pow(o2.x - o1.x, 2) + Math.pow(o2.y - o1.y, 2));
    }

}
