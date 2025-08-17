import java.util.Scanner;

public class Ex02 {

    public static Scanner sc =  new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số hàng: ");
        int h = sc.nextInt();
        System.out.print("Nhập số cột: ");
        int c = sc.nextInt();
        int[][] arr = new int[h][c];
        nhapMang2Chieu(arr);
        inMang2Chieu(arr);
    }

    public static void inMang2Chieu(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void nhapMang2Chieu(int[][]arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("Nhập a[" + i + "][" + j + "]: ");
                arr[i][j] = sc.nextInt();
            }
            System.out.println();
        }
    }
}
