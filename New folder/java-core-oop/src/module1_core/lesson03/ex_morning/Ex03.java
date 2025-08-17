package module1_core.lesson03.ex_morning;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = sc.nextInt();

        for (int i = 2; i <= n; i += 2) {
            if (i % 4 == 0) {
                System.out.println(-i);
            } else {
                System.out.println(i);
            }
        }
    }
}
