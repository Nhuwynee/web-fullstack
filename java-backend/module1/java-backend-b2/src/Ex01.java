import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        // 1.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số: ");
        int number = scanner.nextInt();

        String result = (number % 2 == 0? "Số chẵn" : "Số lẻ");
        System.out.println(result);
    }
}
