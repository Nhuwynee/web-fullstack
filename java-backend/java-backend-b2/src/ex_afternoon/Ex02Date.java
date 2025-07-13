package ex_afternoon;

import java.util.Scanner;

public class Ex02Date {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm (số nguyên): ");
        int year = sc.nextInt();

        if (year <= 0) {
            System.out.println("Năm không hợp lệ.");
            return;
        }

        System.out.print("Nhập tháng (số nguyên từ 1 - 12): ");
        int month = sc.nextInt();
        int day;

        if (month <= 12 && month > 0) {
            switch (month) {
                case 4,6,9,11:
                    day = 30;
                    break;
                case 2:
                    // tính ngày năm nhuận
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    day = 31;
                    break;
            }
            System.out.printf("Tháng %d năm %d có %d ngày.", month, year, day);
        } else {
            System.out.println("Số tháng không hợp lệ.");
        }
    }
}
