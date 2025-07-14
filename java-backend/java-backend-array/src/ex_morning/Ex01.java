package ex_morning;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập n phần tử: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + " : ");
            arr[i] =  sc.nextInt();
        }

        System.out.println("a. ");
        printArray(arr);
        System.out.println("b. " + ((kiemTraToanChan(arr)) == true ? "Mảng toàn chẵn." : "Mảng không tòàn chẵn."));
        System.out.println("c. Tổng các số dương lẻ: " + tinhTongLe(arr));

        System.out.println("Nhập số x muốn tìm: ");
        int x = sc.nextInt();
        System.out.println("d. Vị trí cuối cùng của " + x + " là: " + ((timViTriCuoi(arr, x) == 0) ? "Không tìm thấy x" : timViTriCuoi(arr, x)));
    }

    private static void printArray (int[] arr) {
        for (int i = 0;  i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static boolean kiemTraToanChan (int[] arr) {
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    private static int tinhTongLe (int[] arr) {
        int sum = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                sum += arr[i];
            }
        }
        return sum;
    }

    private static int timViTriCuoi (int[] arr, int x) {
        int viTri = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] == x) {
                viTri = i + 1;
            }
        }
        return viTri;
    }
}
