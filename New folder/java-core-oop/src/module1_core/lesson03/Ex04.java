package module1_core.lesson03;

import java.util.Scanner;

public class Lesson6Ex01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số nguyên n: ");
        int n = scanner.nextInt();
        System.out.println("Giai thừa của n: " + giaiThua(n));

    }

    private static int giaiThua(int n) {
        int gt = 1;
        for (int i = 1; i <= n; i++) {
            gt *= i;
        }
        return gt;
    }
}
