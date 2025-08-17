package ex_morning;

import java.util.Scanner;

public class Ex02C2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập a: ");
        int a = scanner.nextInt();
        System.out.print("Nhập b: ");
        int b = scanner.nextInt();

        System.out.println("---> Ban đầu: a = " + a + " và b = " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("---> Sau khi đảo: a = " + a + " và b = " + b);
    }
}
