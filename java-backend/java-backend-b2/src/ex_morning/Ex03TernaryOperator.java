package ex_morning;

import java.util.Scanner;

public class Ex03TernaryOperator {
    public static void main(String[] args) {
        // 1.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập a: ");
        int a = scanner.nextInt();

        System.out.print("Nhập b: ");
        int b = scanner.nextInt();

        String result = (a > b ? a + " là số lớn nhất" : b + " là số nhỏ nhất");
        System.out.println(result);

    }
}
