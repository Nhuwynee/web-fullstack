package test_morning;

import java.util.Scanner;

public class Ex04 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + " : ");
            arr[i] =  sc.nextInt();
        }

        System.out.println("Mảng nhập vào: ");
        printArray(arr);

        System.out.println("\nSố nguyên tố trong mảng: ");
        for (int i : arr) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
