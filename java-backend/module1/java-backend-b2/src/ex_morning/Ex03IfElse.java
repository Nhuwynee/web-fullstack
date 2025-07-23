package ex_morning;

import java.util.Scanner;

public class Ex03IfElse {
    public static void main(String[] args) {
        // C2:
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập c: ");
        int c = scanner.nextInt();
        System.out.print("Nhập d: ");
        int d = scanner.nextInt();

        int max;
        int min;

        if (c > d) {
            max = c;
            min = d;
        } else {
            max = d;
            min = c;
        }
        System.out.println("Số lớn nhất là " + max);
        System.out.println("Số nhỏ nhất là " + min);
    }
}
