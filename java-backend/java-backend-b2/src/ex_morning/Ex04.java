package ex_morning;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        // ax + b = 0
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập b: ");
        double b = scanner.nextDouble();

        if (a == 0) {
            if (b == 0) {
                System.out.println("Phương trình vô số nghiệm.");
            } else {
                System.out.println("Phương trình vô nghiệm.");
            }
        } else {
            System.out.println("Nghiệm của phương trình: x = " + (-b/a));
        }
    }
}
