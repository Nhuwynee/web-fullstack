package module1_core.lesson03.ex_morning;

import java.util.Scanner;

public class Ex06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập n: ");
        int n = sc.nextInt();
        System.out.println("a. Số đảo của " + n + " là: " + reserveNumber(n));

        // b.
        if (kiemTraSoDoiXung(n)) {
            System.out.println("b. " + n + " là số đối xứng.");
        } else {
            System.out.println("b. " + n + " không phải số đối xứng.");
        }

        // c.
        if (soChinhPhuong(n)) {
            System.out.println("c. " + n + " là số chính phương.");
        } else {
            System.out.println("c. " + n + " không phải số chính phương.");
        }

        // d.
        if (kiemTraSoNguyenTo(n)) {
            System.out.println("b. " + n + " là số nguyên tố.");
        } else {
            System.out.println("b. " + n + " không phải số nguyên tố.");
        }

        // e.
        System.out.println("e. Tổng các chữ số lẻ từ 1 - n: " + tongSoLe(n));

        // f.
        System.out.println("f. Tổng các chữ số nguyên tố: " + tongSoNguyenTo(n));

        // g.
        System.out.println("g. Tổng các chữ số chính phương: " + tongSoChinhPhuong(n));
    }

    private static int reserveNumber (int n) {
        int dao = 0;

        while (n > 0) {
            int digit = n % 10;
            dao = dao * 10 + digit;
            n /= 10;
        }
        return dao;
    }

    private static boolean kiemTraSoNguyenTo (int n) {
        int temp = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                temp += 1;
            }
        }
        return temp == 0;
    }

    private static boolean soChinhPhuong (int n) {
        return Math.sqrt(n) % 1 == 0;
    }

    private static int tongSoLe (int n) {
        int sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }

    private static int tongSoNguyenTo (int n) {
        int sum = 0;
        while (n != 0){
            int digit = n % 10;
            if (kiemTraSoNguyenTo(digit)) {
                sum += digit;
            }
            n /= 10;
        }
        return sum;
    }

    private static int tongSoChinhPhuong(int n) {
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            if (soChinhPhuong(digit)) {
                sum += digit;
            }
            n /= 10;
        }
        return sum;
    }


    private static boolean kiemTraSoDoiXung(int n) {
        int number = n;
        int dao = 0;

        while (n > 0) {
            int digit = n % 10;
            dao = dao * 10 + digit;
            n /= 10;
        }

        return number == dao;
    }

}
