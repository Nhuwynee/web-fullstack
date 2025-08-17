package utils;

import java.util.ArrayList;
import java.util.Scanner;

/* Hàm trong String
- equals/
- length()
- concat(): nối 2 chuỗi thành 1 chuỗi mới
- charAt(index)
- replace()/replaceAll()
- substring()
- toLowerCase()/ toUpperCase()
- trim()
- split(String regex)
- String.format()
 */

public class Math {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    }

    private static boolean kiemTraSoNguyenTo (int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= java.lang.Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[][] hoanDoi2Cot (int[][] arr, int c1, int c2) {
        for (int j = 0; j < arr.length; j++) {
            int temp = arr[j][c1];
            arr[j][c1] = arr[j][c2];
            arr[j][c2] = temp;
        }
        return arr;
    }

    private static void hoanDoi2DongCuaMaTran(int[][] arr, int h1, int h2) {
        int[] temp = arr[h1];
        arr[h1] = arr[h2];
        arr[h2] = temp;
    }

    private static void inMang2Chieu(int[][] arr) {
        for (int[] row : arr) {
            for (int r : row) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }

    private static void nhapMang2Chieu(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("a[" + i + "][" + j + "]: ");
                arr[i][j] = sc.nextInt();
            }
            System.out.println();
        }
    }

    private static Integer findSecondMax(ArrayList<Integer> arr) {
        if (arr.size() < 2) {
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
            System.out.println("Không có phần tử lớn nhì (mọi phần tử giống nhau)");
            return null;
        }
        return secondMax;
    }

    private static int[] timPhanTu (int[] arr, int value) {
        int soLanXuatHien = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                soLanXuatHien++;
            }
        }
        int[] index = new int[soLanXuatHien];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                index[j++] = i;
            }
        }
        return index;

    }

    private static boolean kiemTraMangDoiXung(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void decimalToBinary(int n) {
        int[] binary = new int[30];
        int index = 0;
        while (n > 0) {
            binary[index++] = n % 2;
            n = n / 2;
        }
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(binary[i]);
        }
    }

    private static boolean kiemTraSoDoiXung(int n) {
        int number = n;
        int dao = 0;

        while (n > 0) {
            int digit = n % 10;
            dao = dao * 10 + digit;
            n /= 10;
        }

        return number == dao;
    }

    private static boolean soChinhPhuong (int n) {
        return java.lang.Math.sqrt(n) % 1 == 0;
    }

    private static int tongSoChinhPhuong(int n) {
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            if (soChinhPhuong(digit)) {
                sum += digit;
            }
            n /= 10;
        }
        return sum;
    }

    private static void fibonacci(int n) {
        int a = 0, b = 1;
        while (a <= n) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    private static int giaiThua(int n) {
        int gt = 1;
        for (int i = 1; i <= n; i++) {
            gt *= i;
        }
        return gt;
    }
}
