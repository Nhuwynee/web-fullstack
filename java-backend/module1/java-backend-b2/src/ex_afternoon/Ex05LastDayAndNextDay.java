package ex_afternoon;

import java.util.Scanner;

public class Ex05LastDayAndNextDay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập năm (số nguyên > 0): ");
        int year = sc.nextInt();
        if (year <= 0) {
            System.out.println("Năm không hợp lệ.");
            return;
        }

        System.out.print("Nhập tháng (1 - 12): ");
        int month = sc.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Tháng không hợp lệ.");
            return;
        }

        System.out.print("Nhập ngày: ");
        int day = sc.nextInt();

        int maxDay = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else if (month == 2) {
            // kiểm tra năm nhuận
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        }

        if (day < 1 || day > maxDay) {
            System.out.println("Ngày không hợp lệ theo tháng.");
            return;
        }

        // Nếu hợp lệ, tính ngày trước và ngày sau
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;

        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        // Xử lý ngày hôm trước
        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }

            // Tính số ngày của tháng trước đó
            if (prevMonth == 2) {
                if ((prevYear % 4 == 0 && prevYear % 100 != 0) || prevYear % 400 == 0) {
                    prevDay = 29;
                } else {
                    prevDay = 28;
                }
            } else if (prevMonth == 4 || prevMonth == 6 || prevMonth == 9 || prevMonth == 11) {
                prevDay = 30;
            } else {
                prevDay = 31;
            }
        }

        // Xử lý ngày hôm sau
        if (nextDay > maxDay) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }

        System.out.printf("Ngày hôm trước: %02d/%02d/%04d\n", prevDay, prevMonth, prevYear);
        System.out.printf("Ngày hôm sau: %02d/%02d/%04d\n", nextDay, nextMonth, nextYear);
    }
}
