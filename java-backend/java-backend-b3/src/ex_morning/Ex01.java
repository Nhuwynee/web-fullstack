package ex_morning;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = sc.nextInt();

        System.out.println("Số chẵn từ 2 - 2n: ");
        int temp = 2*n;
        for (int i = 2; i <= temp; i += 2) {
                System.out.print(i + " ");
        }
    }
}
