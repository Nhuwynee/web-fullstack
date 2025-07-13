package ex_morning;

import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        // PT bậc 2: ax2 + bx + c = 0
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhập c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phương trình vô số nghiệm");
                } else {
                    System.out.println("Phương trình vô nghiệm");
                }
            } else {
                System.out.println("Nghiệm kép của phương trình: x = " + (-c/b));
            }
        } else {
            double delta = b*b - 4*a*c;
            if (delta < 0) {
                System.out.println("Phương trình vô nghiệm");
            } else if (delta == 0) {
                System.out.println("Nghiệm kép của pt là: " + (-b/(2*a)));
            } else {
                System.out.println("Nghiệm x1 = " + ((-b + Math.sqrt(delta)) / (2*a)));
                System.out.println("Nghiệm x2 = " + ((-b - Math.sqrt(delta)) / (2*a)));
            }
        }
    }
}
