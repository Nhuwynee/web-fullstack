package test_morning;

import java.util.Scanner;

public class Ex05 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        System.out.println("Mảng sau khi nhập: ");
        printArrayList(arr);

        System.out.println();
        System.out.println("Phần tử lớn nhì trong mảng: " + findSecondMax(arr));

    }

    private static void printArrayList(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static Integer findSecondMax(int[] arr) {
        if (arr.length < 2) {
            System.out.println("Không đủ phần tử để tìm max thứ 2");
            return null;
        }
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                secondMax = max;
                max = i;
            } else if (i > secondMax && i < max) {
                secondMax = i;
            }
        }
        if (secondMax == Integer.MIN_VALUE) {
            System.out.println("Không tìm thấy số lớn nhì.");
            return null;
        }
        return secondMax;
    }
}
