package ex_afternoon;

import java.util.Scanner;

public class Ex06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số n: ");
        int n = sc.nextInt();
        System.out.println("Tổng các chữ số = " + tongChuSo(n));
        System.out.println("Tích các chữ số = " + tichChuSo(n));
    }

    private static int tongChuSo (int n) {
        int sum = 0;
        while (n != 0){
            int digit = n % 10;
            sum += digit;
            n /= 10;
        }
        return sum;
    }

    private static int tichChuSo (int n) {
        int sum = 1;
        while (n != 0){
            int digit = n % 10;
            sum *= digit;
            n /= 10;
        }
        return sum;
    }
}
