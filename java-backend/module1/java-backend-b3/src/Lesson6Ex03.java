import java.util.Scanner;

public class Lesson6Ex03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập a: ");
        int a = scanner.nextInt();
        System.out.print("Nhập b: ");
        int b = scanner.nextInt();

        System.out.println("Số lớn hơn là: " + maxNumber(a, b));
    }

    private static int maxNumber (int a, int b) {
        return (a > b ? a : b);
    }
}
