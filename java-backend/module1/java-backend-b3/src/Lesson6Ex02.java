import java.util.Scanner;

public class Lesson6Ex02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = scanner.nextInt();
        kiemTraSoNguyenTo(n);
    }

    private static void kiemTraSoNguyenTo (int n) {
        int temp = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                temp += 1;
            }
        }
        if (temp == 0) {
            System.out.println(n + " là số nguyên tố");
        } else {
            System.out.println(n + " ko phải là số nguyên tố");
        }
    }
}
