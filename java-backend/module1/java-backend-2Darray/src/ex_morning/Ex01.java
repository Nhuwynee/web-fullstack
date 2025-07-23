package ex_morning;

import java.util.Scanner;

public class Ex01 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số hàng: ");
        int h = sc.nextInt();
        System.out.print("Nhập số cột: ");
        int c = sc.nextInt();
        int[][] arr = new int[h][c];
        System.out.println("a. -----------------");
        nhapMang2Chieu(arr);
        System.out.println("Mảng sau khi nhập: ");
        inMang2Chieu(arr);

        System.out.println("b. ------------------");
        System.out.println(tichCacSoBoi3Dong1(arr[0]) == 1 ?
                "Không có số nào là bội của 3 ở dòng đầu tiên."
                : ("Tích các số là bội của 3 ở dòng đầu tiên là: " + tichCacSoBoi3Dong1(arr[0])));

        int[] x = findMaxOfRow(arr);
        System.out.println("c. ---------- Mảng X:");
        for (int i : x) {
            System.out.print(i + " ");
        }

        System.out.println("\nd. ------------------");
        System.out.println(tongCacSoBoi5DongCuoi(arr[arr.length - 1]) == 0 ?
                "Không có số nào là bội của 5 ở dòng cuối."
                : ("Tổng các số là bội của 5 ở dòng cuối là: " + tongCacSoBoi5DongCuoi(arr[arr.length - 1])));

        System.out.println("e. Số lớn nhất ở đường chéo chính là: " + maxDuongCheoChinh(arr));
        System.out.println("f. Số nhỏ nhất ở đường chéo phụ là: " + minDuongCheoPhu(arr));

        System.out.println("g. Số lượng số nguyên tố trong ma trận là: " + countPrimeNumberOfArray(arr));

        System.out.println("\nh. Nhập 2 hàng muốn hoán đổi vị trí: ");
        int h1 = sc.nextInt();
        int h2 = sc.nextInt();
        if (h1 < 0 || h1 >= arr.length || h2 < 0 || h2 >= arr.length) {
            System.out.println("Nhập quá số hàng của mảng.");
            return;
        } else {
            System.out.println("\nMảng sau khi hoán đổi: ");
            hoanDoi2DongCuaMaTran(arr, h1, h2);
            inMang2Chieu(arr);
        }

        System.out.println("\n------- i. Nhập 2 cột muốn hoán đổi vị trí: ");
        int c1 = sc.nextInt();
        int c2 = sc.nextInt();
        if (c1 < 0 || c1 >= arr.length || c2 < 0 || c2 >= arr.length) {
            System.out.println("Nhập quá số cột của mảng.");
            return;
        } else {
            System.out.println("\nMảng sau khi hoán đổi cột: ");
            hoanDoi2Cot(arr, c1, c2);
            inMang2Chieu(arr);
        }
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

    private static boolean kiemTraMangToanChan(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] findMaxOfRow (int[][] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = arr[i][0];
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
            result[i] = max;
        }
        return result;
    }

    private static void hoanDoi2DongCuaMaTran(int[][] arr, int h1, int h2) {
        int[] temp = arr[h1];
        arr[h1] = arr[h2];
        arr[h2] = temp;
    }

    private static int countPrimeNumberOfArray (int[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (kiemTraSoNguyenTo(arr[i][j])) {
                    count++;
                }
            }
        }
        return count;
    }


    private static boolean kiemTraSoNguyenTo (int n) {
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

    private static int tichCacSoBoi3Dong1 (int[] arr) {
        int tich = 1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] % 3 == 0) {
                tich *= arr[j];
            }
        }
        return tich;
    }

    private static int tongCacSoBoi5DongCuoi (int[] arr) {
        int tong = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] % 5 == 0) {
                tong += arr[j];
            }
        }
        return tong;
    }

    private static int maxDuongCheoChinh (int[][] arr) {
        int max = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][i] > max) {
                max = arr[i][i];
            }
        }
        return max;
    }

    /*
            1  22 33 44
            55 66 33 44
            77 88 99 22
            33 44 22 33
     */
    private static int minDuongCheoPhu (int[][] arr) {
        int min = arr[0][arr.length - 1];
        for(int i = 0; i < arr.length; i++) {
            if (arr[i][arr.length - 1 - i] < min) {
                min = arr[i][arr.length - 1 - i];
            }
        }
        return min;
    }

    private static int[][] hoanDoi2Cot (int[][] arr, int c1, int c2) {
        for (int j = 0; j < arr.length; j++) {
            int temp = arr[j][c1];
            arr[j][c1] = arr[j][c2];
            arr[j][c2] = temp;
        }
        return arr;
    }
}
