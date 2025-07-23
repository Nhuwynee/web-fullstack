package ex_morning;

import java.util.Arrays;
import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập n phần tử: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + " : ");
            arr[i] =  sc.nextInt();
        }

        System.out.print("- 1. ");
        printArray(arr);
        System.out.println("\n- 2a. " + (kiemTraToanChan(arr) ? "Mảng toàn chẵn." : "Mảng không toàn chẵn."));
        System.out.println("- 2b. " + (kiemTraToanSoNgTo(arr) ? "Mảng toàn số nguyên tố." : "Mảng không tòàn số nguyên tố."));
        System.out.println("- 2c. " + (kiemTraToanSoTangDan(arr) ? "Mảng toàn số tăng dần." : "Mảng không tòàn số tăng dần."));

        System.out.println("- 3a. Số lượng các số dương lẻ: " + tinhSoLuongLe(arr));
        System.out.println("- 3b. Tổng các số dương lẻ: " + tinhTongLe(arr));
        System.out.println("- 3c. Tổng các số chia hết cho 4 nhưng khong chia hết cho 5: " + tinhSoLuongSoYeuCau(arr));
        System.out.println("- 3d. Tổng các số nguyên tố: " + tinhTongCacSoNguyenTo(arr));

        System.out.print("Nhập số x muốn tìm: ");
        int x = sc.nextInt();
        System.out.println("- 4a. Vị trí cuối cùng của " + x + " là: " + ((timViTriCuoi(arr, x) == 0) ? "Không tìm thấy x" : timViTriCuoi(arr, x)));
        System.out.println("- 4b. Vị trí đầu tiên cuẩ số nguyên tố: "  + ((timViTriDauCuaSoNguyenTo(arr) == 0) ? "Không có số nguyên tố trong mảng" : timViTriDauCuaSoNguyenTo(arr)));
        System.out.println("- 4c. Số nhỏ nhất trong mảng: " + timSoNhoNhat(arr));

        System.out.print("- 4d. Nhập số k muốn tìm: ");
        int k = sc.nextInt();

        if (timPhanTu(arr, k).length != 0) {
            System.out.println("- 4d. Vị trí xuất hiện của " + k + " là: " + Arrays.toString(timPhanTu(arr, k)));
        } else {
            System.out.println("- 4d. " + k + " không có trong mảng.");
        }
        System.out.println("- 4e. Số nhỏ nhất và lớn nhất trong mảng: " + timSoNhoNhat(arr) + " và " + timSoLonNhat(arr));
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

    private static boolean kiemTraToanSoNgTo (int[] arr) {
        for (int i = 0;  i < arr.length; i++) {
            if (!kiemTraSoNguyenTo(arr[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean kiemTraToanSoTangDan (int[] arr) {
        for (int i = 0;  i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
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

    private static int tinhSoLuongLe (int[] arr) {
        int sum = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                sum ++;
            }
        }
        return sum;
    }

    private static int tinhSoLuongSoYeuCau (int[] arr) {
        int sum = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] % 4 == 0 && arr[i] % 5 != 0) {
                sum ++;
            }
        }
        return sum;
    }

    private static int tinhTongCacSoNguyenTo (int[] arr) {
        int sum = 0;
        for (int i = 0;  i < arr.length; i++) {
            if (kiemTraSoNguyenTo(arr[i])) {
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

    private static int timViTriDauCuaSoNguyenTo (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (kiemTraSoNguyenTo(arr[i])) {
                return i + 1;
            }
        }
        return 0;
    }

    private static int timSoNhoNhat (int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i < min) {
                min = arr[i];
            }
        }
        return min;
    }

    private static int timSoLonNhat (int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = arr[i];
            }
        }
        return max;
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
}

