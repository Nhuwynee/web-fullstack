package ex_morning;

import java.util.Arrays;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập n phần tử: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + " : ");
            arr[i] =  sc.nextInt();
        }
        int[] arrZero = insertZeroAfterPrime(arr);
        System.out.println("1. Mảng sau khi chèn: " + Arrays.toString(arrZero));

        System.out.print("Nhập giá trị muốn chèn: ");
        int value = sc.nextInt();
        System.out.print("Nhập vị trí muốn chèn: ");
        int x2 = sc.nextInt();

        int[] arr2 = chenTaiViTriIndex(arrZero, x2, value);
        System.out.println("Mảng sau khi chèn: " + Arrays.toString(arr2));

        System.out.print("Nhập vị trí muốn xóa: ");
        int x1 = sc.nextInt();
        int[] arr3 = xoaTaiViTriIndex(arr2, x1);

        System.out.println("Mảng sau khi xóa: " + Arrays.toString(arr3));
    }

    private static int[] chenSo (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (kiemTraSoNguyenTo(arr[i])) {
                arr[i] *= 10;
            }
        }
        return arr;
    }

    private static boolean kiemTraSoNguyenTo (int n) {
        int temp = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                temp += 1;
            }
        }
        if (temp == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int[] chenTaiViTriIndex (int[] arr, int index, int value) {
        if (index < 0 || index > arr.length) {
            System.out.println("Index không hợp lệ !");
            return arr;
        }

        int[] result = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        for (int i = result.length - 1; i > index; i--) {
            result[i] = result[i - 1];
        }
        result[index] = value;

        return result;

    }

    private static int[] xoaTaiViTriIndex (int[] arr, int index) {
        if (index < 0 || index > arr.length) {
            System.out.println("Index không hợp lệ !");
            return arr;
        }

        int[] result = Arrays.copyOfRange(arr, 0, arr.length);

        for (int i = index; i < result.length - 1; i++) {
            result[i] = result[i + 1];
        }
        return Arrays.copyOfRange(result, 0, result.length - 1);

    }

    private static int[] insertZeroAfterPrime (int arr[]) {
        // đếm số lượng số nguyên tố
        int primeCount = 0;
        for (int num : arr) {
            if (kiemTraSoNguyenTo(num)) primeCount++;
        }

        // tạo mảng mới với kích thước tăng thêm primeCount
        int[] newArr = new int[arr.length + primeCount];
        int newIndex = 0;

        // Chèn số 0 sau mỗi số nguyên tố
        for (int i = 0; i < arr.length; i++) {
            newArr[newIndex++] = arr[i];
            if (kiemTraSoNguyenTo(arr[i])) {
                newArr[newIndex++] = 0;
            }
        }
        return newArr;
    }

}
