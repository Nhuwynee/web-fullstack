public class Ex01 {
    public static void main(String[] args) {
        int[][] arr = {
                {10, 20},
                {30, 40},
                {50, 60}
        };
        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
