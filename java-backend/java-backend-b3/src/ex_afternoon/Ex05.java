package ex_afternoon;

import java.util.Scanner;

 // sum = 1 + 1/3! + 1/5! + ... +1/n!
public class Ex05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();
        double sum = 0.0;
//        for (int i = 1; i <= n; i++) {
//            sum += 1.0/giaiThua(2*i - 1);
//        }
        int i = 1;
        while (i > n) {
            sum += 1.0/giaiThua(2*i - 1);
            i++;
        }
        System.out.println("Tổng là: " + sum);
    }

    private static int giaiThua(int n) {
        int gt = 1;
        for (int i = 1; i <= n; i++) {
            gt *= i;
        }
        return gt;
    }
}
