package module1_core.lesson03.ex_afternoon;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();
        System.out.println("Tổng = " + sumOfNumber(n));
    }

    private static double sumOfNumber (int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0/i;
        }
        return sum;
    }
}
