package ex_afternoon;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();
        doubleNum(n);

    }

    public static void doubleNum(int n) {
        int a = 1;
        while (a <= n) {
            System.out.print(a + " ");
            int temp = 2*a + 1;
            a = temp;
        }
    }
}
