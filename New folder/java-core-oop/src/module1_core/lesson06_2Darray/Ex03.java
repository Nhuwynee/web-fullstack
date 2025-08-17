import java.util.Scanner;

public class Ex03 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số hàng: ");
        int h = sc.nextInt();
        System.out.print("Nhập số cột: ");
        int c = sc.nextInt();
        int[][] arr = new int[h][c];
        nhapMang2Chieu(arr);
        System.out.println("Mảng sau khi nhập: ");
        inMang2Chieu(arr);
        System.out.println(kiemTraMangToanChan(arr) ? "Mảng toàn chẵn." : "Mảng không toàn chẵn.");
        int[] x = findMaxOfRow(arr);
        System.out.println("Mảng X:");
        for (int i : x) {
            System.out.print(i + " ");
        }

        System.out.println("\nNhập 2 hàng muốn hoán đổi vị trí: ");
        int h1 = sc.nextInt();
        int h2 = sc.nextInt();
        if (h1 < 0 || h1 >= arr.length || h2 < 0 || h2 >= arr.length) {
            System.out.println("Nhập hàng nhập quá số hàng của mảng.");
            return;
        } else {
            System.out.println("\nMảng sau khi hoán đổi: ");
            hoanDoi2DongCuaMaTran(arr, h1, h2);
            inMang2Chieu(arr);
        }
    }

    private static void inMang2Chieu(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void nhapMang2Chieu(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("Nhập a[" + i + "][" + j + "]: ");
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
}
