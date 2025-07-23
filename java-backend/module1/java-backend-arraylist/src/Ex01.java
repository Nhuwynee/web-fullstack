import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.print("Nhập n: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            arr.add(sc.nextInt());
        }
        System.out.print("1. Mảng sau khi nhập: ");
        printArrayList(arr);
        System.out.println();
        System.out.println(kiemTraSoChan(arr) ? "2. Mảng chứa số chẵn." : "2. Mảng không chứa số chẵn.");
        sumAndMax(arr);
        System.out.println("4. Mảng sau khi sắp xếp: " + sapXepTangDan(arr));
    }

    private static void printArrayList (ArrayList<Integer> arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static boolean kiemTraSoChan (ArrayList<Integer> arr) {
        for (int i : arr) {
            if (i % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    private static void sumAndMax (ArrayList<Integer> arr) {
        int sum = 0;
        int max = arr.get(0);
        for (int i : arr) {
            sum += 0;
            if (i > max) {
                max = i;
            }
        }
        System.out.println("3. Tổng các phần tử trong mảng: " + sum);
        System.out.println("3. Phần tử max trong mảng là: " + max);
    }

    private static ArrayList<Integer> sapXepTangDan (ArrayList<Integer> arr) {
//        Collections.sort(arr);

        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) > arr.get(j)) {
                    int temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
        return arr;
    }
}
