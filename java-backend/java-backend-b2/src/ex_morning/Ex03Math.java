package ex_morning;

import java.util.Scanner;

public class Ex03Math {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập c: ");
        int c = scanner.nextInt();
        System.out.print("Nhập d: ");
        int d = scanner.nextInt();

        int max = Math.max(c, d);
        int min = Math.min(c, d);

        System.out.println("Số lớn nhất là " + max);
        System.out.println("Số nhỏ nhất là " + min);
    }
}
