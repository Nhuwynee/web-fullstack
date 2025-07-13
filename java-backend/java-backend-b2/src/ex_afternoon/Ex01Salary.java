package ex_afternoon;

import java.util.Scanner;

public class Ex01Salary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linh = 100;
        System.out.print("Nhập số tháng (số nguyên): ");
        int month = scanner.nextInt();
        double luongCanBan = 650000.0;
        double heSo = 4.5;

        System.out.println("Hệ số: " + heSo);
        if (month < 12) {
            heSo = 1.92;
            System.out.println("Hệ số: " + heSo);
        } else if (month < 36) {
            heSo = 2.34;
            System.out.println("Hệ số: " + heSo);
        } else if (month < 60) {
            heSo = 3;
            System.out.println("Hệ số: " + heSo);
        }
        double luong = heSo * luongCanBan;
        System.out.println("Lương của nhân viên: " + String.format("%,.0f", luong));
    }
}
