package ex_afternoon;

import java.util.Scanner;

public class Ex02 {

    /*
        📝 Bài 2: Xoay ma trận 90 độ theo chiều kim đồng hồ
        Mô tả:
        Cho ma trận vuông n×n. Viết hàm xoay ma trận theo chiều kim đồng hồ 90 độ.

        Yêu cầu:
        Nhập ma trận n×n.
        In ra ma trận sau khi xoay.

        💥 Lưu ý:
        Không sử dụng thư viện hỗ trợ ma trận, chỉ dùng mảng 2 chiều thuần túy.

        Nâng cao:
        Để tối ưu hoá bộ nhớ -> KHÔNG tạo mảng mới mà thực hiện xoay trên mảng gốc
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k;
        int[][] arr = {
                {0, 1, 2, 7},
                {1, 2, 3, 8},
                {5, 6, 7, 6}
        };

        System.out.println("Mảng ban đầu: ");
        inMang2Chieu(arr);


        System.out.println("Mảng sau khi xoay: ");
        inMang2Chieu(rotateMatrix90(arr));

    }

    public static int[][] rotateMatrix90(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                newMatrix[row][col++] = matrix[j][i];
                if (col == matrix[i].length) {
                    col = 0;
                    row++;
                }
            }
        }
        return newMatrix;
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
