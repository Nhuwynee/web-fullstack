package ex_afternoon;

import java.util.Scanner;

public class Ex01 {

    /*
        ** 📝 Bài 1: Tìm hình vuông con có tổng lớn nhất **
        * Mô tả:
        Cho một ma trận số nguyên kích thước MxN. Viết chương trình tìm hình vuông con có kích thước k×k
        (với k do người dùng nhập vào, k ≤ min(M, N)) sao cho tổng các phần tử trong hình vuông đó là lớn nhất.

        * Yêu cầu:
        - Xuất tổng lớn nhất và tọa độ của hình vuông con tương ứng (dòng, cột bắt đầu).

        * Gợi ý:
        - Với mỗi ô [i][j], bạn hãy xem xét hình vuông con có kích thước k×k bắt đầu tại [i][j].
        - Duyệt qua tất cả các vị trí có thể đặt hình vuông k×k (tức là i ≤ M - k, j ≤ N - k).
        - Tính tổng các phần tử trong hình vuông k×k này bằng cách lặp lồng trong k dòng và k cột.

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k;
        int[][] arr = {
                {1,  2, -3,  4},
                {4,  8,  9,  2},
                {6,  0,  2, -5},
                {7,  8, 11,  3},
                {4, -5,  5,  4}
        };

        System.out.println("Mảng ban đầu: ");
        inMang2Chieu(arr);

        while (true) {
            System.out.print("Nhập k: ");
            k = sc.nextInt();
            if (k <= 0 || k > arr.length || k > arr[0].length) {
                System.out.print("k nhập vào không hợp lệ. Nhập lại! ");
            } else {
                break;
            }
        }

        int row = -1;
        int col = -1;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i <= arr.length - k; i++) {
            for (int j = 0; j <= arr[i].length - k; j++) {
                int sum = 0;
                for (int a = 0; a < k; a++) {
                    for (int b = 0; b < k; b++) {
                        sum += arr[i + a][j + b];
                    }
                }
                if (sum > max) {
                    max = sum;
                    row = i;
                    col = j;
                }
            }
        }

        if (row == -1 || col == -1) {
            System.out.println("Không tìm thấy hình vuông con hợp lệ.");
            return;
        }

        System.out.println("Tổng lớn nhất: " + max);
        System.out.println("Ví trí bắt đầu của mảng mới kxk là: hàng: " + row + ", cột: " + col);

    }

    private static void inMang2Chieu(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }
    }

}
