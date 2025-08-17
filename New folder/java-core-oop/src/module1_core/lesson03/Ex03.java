package module1_core.lesson03;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = scanner.nextInt();
        int i = 1;
        int sum = 0;

        while (i <= n) {
            if (i % 2 == 0) {
                sum += i;
            }
            i++;
        }
        System.out.println("Tổng các số chẵn từ 1 đến " + n + " là: " + sum);
    }
}
