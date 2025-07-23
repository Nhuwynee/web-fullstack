package ex_morning;

import java.util.Scanner;

public class Ex05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập lựa chọn câu: ");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                System.out.print("a. Nhập 1 ký tự (chữ cái): ");
                char kyTu = sc.next().charAt(0);
                formatCharacter(kyTu);
                break;
            case 2:
                System.out.println("b. Phương trình bậc 1:");
                System.out.print("Nhập a: ");
                double a = sc.nextDouble();
                System.out.print("Nhập b: ");
                double b = sc.nextDouble();
                phuongTrinhBacNhat(a, b);
                break;

            case 3:
                System.out.println("c. Phương trình bậc 2.");
                System.out.print("Nhập a: ");
                double c = sc.nextDouble();
                System.out.print("Nhập b: ");
                double d = sc.nextDouble();
                System.out.print("Nhập c: ");
                double e = sc.nextDouble();
                phuongTrinhBac2(c, d, e);
                break;
            case 4:
                System.out.print("Nhập lần lượt 4 số nguyên: ");
                int one = sc.nextInt();
                int two = sc.nextInt();
                int three = sc.nextInt();
                int four = sc.nextInt();
                System.out.println("Số nhỏ nhất: " + minNumber(one, two, three, four));
                break;
        }

    }
    // a.
    private static void formatCharacter (char c) {
        if (c < 'A' || c > 'z') {
            System.out.println("Nhập không đúng yêu cầu.");
        } else {
            if (c >= 'Z') {
                c -= 32;
            } else {
                c += 32;
            }
            System.out.println(c);
        }
    }

    // b.
    private static void phuongTrinhBacNhat (double a, double b) {
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

    // c.
    private static void phuongTrinhBac2 (double a, double b, double c) {
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

    private static int minNumber (int a, int b, int c, int d) {
        return minNumber(a, b, c, d);
    }
}
