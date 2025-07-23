import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();

        System.out.print("a. Nhập n: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            arr.add(sc.nextInt());
        }
        System.out.print("b. Mảng sau khi nhập: ");
        printArrayList(arr);
        System.out.println();
        System.out.println("c. Số phần tử lẻ trong mảng: " + demPhanTuLe(arr));
        System.out.println("d. Tổng số dương lẻ: " + tongDuongLe(arr));

        System.out.print("e. Nhập k muốn tìm: ");
        Integer k = sc.nextInt();
        findIndexAndElement(arr, k);

        System.out.printf("f. Mảng sau khi sắp xếp tăng dần: ");
        Collections.sort(arr);
        printArrayList(arr);

        System.out.println("\ng. Mảng sau khi đảo ngược thứ tự: ");
        Collections.reverse(arr);
        printArrayList(arr);

        System.out.print("\nh. Nhập giá trị x muốn xóa: ");
        Integer x = sc.nextInt();
        System.out.println("Mảng sau khi xóa x: " + deleteElement(arr, x));

        System.out.print("Nhập lần lượt giá trị và vị trí muốn chèn: ");
        Integer value = sc.nextInt();
        int index = sc.nextInt();

        insertAndDeleteElement(arr, value, index);

        System.out.println("j. Giá trị lớn nhất: " + findMax(arr));
        System.out.println("j. Giá trị nhỏ nhất: " + findMin(arr));

        System.out.println("k. Giá trị lớn nhì: " + findSecondMax(arr));

    }

    private static void printArrayList (ArrayList<Integer> arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static int demPhanTuLe (ArrayList<Integer> arr) {
        int count = 0;
        for (int i : arr) {
            if (i % 2 != 0) {
                count++;
            }
        }
        return count;
    }

    private static int tongDuongLe (ArrayList<Integer> arr) {
        int sum = 0;
        for (int i : arr) {
            if (i % 2 != 0 && i > 0) {
                sum += i;
            }
        }
        return sum;
    }

    private static void findIndexAndElement (ArrayList<Integer> arr, Integer k) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (k.equals(arr.get(i))) {
                index.add(i);
            }
        }
        if (!index.isEmpty()) {
            System.out.println("Phần tử có " + k + " xuất hiện trong mảng !");
            System.out.println("Vị trí xuất hiện của " + k + " là: " + index);
        } else {
            System.out.println("Phần tử " + k + " không xuất hiện trong mảng !");
        }
    }

    private static void insertAndDeleteElement (ArrayList<Integer> arr, Integer value, int index) {
        if (index >= arr.size() || index < 0) {
            System.out.println("Index nhập vào ko hợp lệ!");
        } else {
            arr.add(index, value);
            arr.remove(arr.size() - 1);
            System.out.println("Mảng sau khi chèn và xóa phần tử cuối cùng: ");
            printArrayList(arr);
        }
    }
    private static int findMax (ArrayList<Integer> arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private static int findMin (ArrayList<Integer> arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        return min;
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


    private static ArrayList<Integer> deleteElement (ArrayList<Integer> arr, Integer value) {
        arr.removeIf(i -> i.equals(value));
        return arr;
    }
}
