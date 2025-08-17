package ex_afternoon;

import java.util.Arrays;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {-1, 2, 3, -4, 0, 6, -7, 2, 8, 9};

        System.out.println("\n- Mảng ban đầu: " + Arrays.toString(arr));
        System.out.println("-------------------------------------------");
        System.out.println("a. Mảng sau khi thay đổi số nguyên tố thành 0: " + Arrays.toString(thayDoiSoNguyenTo(arr)));
        System.out.println("b. Mảng sau khi chèn số 0 sau số nguyên tố: " + Arrays.toString(insertZeroAfterPrime(arr)));
        System.out.print("c. Nhập giá trị k muốn xóa: ");
        int k = sc.nextInt();
        System.out.println("- Mảng sau khi xóa giá trị " + k + " là: " + Arrays.toString(xoaPhanTuBangK(arr, k)));
        System.out.println("d. Mảng sau khi xóa tất cả các số nguyên tố: " + Arrays.toString(xoaSoNguyenTo(arr)));

        System.out.print("e. Nhập giá trị muốn chèn: ");
        int value = sc.nextInt();
        System.out.print("e. Nhập vị trí muốn chèn: ");
        int x2 = sc.nextInt();

        System.out.println("- Mảng sau khi chèn: " + Arrays.toString(chenTaiViTriIndex(arr, x2 - 1, value)));

    }

    private static int[] thayDoiSoNguyenTo (int arr[]) {
        int[] arrNew = Arrays.copyOfRange(arr, 0, arr.length);
        for (int i = 0; i < arrNew.length; i++) {
            if (kiemTraSoNguyenTo(arrNew[i])) arrNew[i] = 0;
        }
        return arrNew;
    }

    private static boolean kiemTraSoNguyenTo (int n) {
        int temp = 0;
        if (n == 1 || n <= 0) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                temp += 1;
            }
        }
        return temp == 0;
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

        for (int i = 0; i < arr.length; i++) {
            newArr[newIndex++] = arr[i];
            if (kiemTraSoNguyenTo(arr[i])) {
                newArr[newIndex++] = 0;
            }
        }
        return newArr;
    }

    private static int[] xoaPhanTuBangK(int[] arr, int k) {
        int count = 0;
        for (int value : arr) {
            if (value != k) count++;
        }

        int[] result = new int[count];
        int index = 0;
        for (int value : arr) {
            if (value != k) {
                result[index++] = value;
            }
        }
        return result;
    }

    private static int[] xoaSoNguyenTo(int[] arr) {
        int count = 0;
        for (int value : arr) {
            if (!kiemTraSoNguyenTo(value)) count++;
        }

        int[] result = new int[count];
        int index = 0;
        for (int value : arr) {
            if (!kiemTraSoNguyenTo(value)) {
                result[index++] = value;
            }
        }
        return result;
    }

    private static int[] chenTaiViTriIndex (int[] arr, int index, int value) {
        if (index < 0 || index > arr.length) {
            System.out.println("Index không hợp lệ !");
            return arr;
        }

        int[] result = Arrays.copyOfRange(arr, 0, arr.length);

        for (int i = result.length - 1; i > index; i--) {
            result[i] = result[i - 1];
        }
        result[index] = value;

        return result;
    }

}
