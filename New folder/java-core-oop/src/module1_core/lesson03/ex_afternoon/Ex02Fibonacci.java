package module1_core.lesson03.ex_afternoon;

import java.util.Scanner;

public class Ex02Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();
        fibonacci(n);
    }

    public static void fibonacci(int n) {
        int a = 0, b = 1;
        while (a <= n) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }
}
