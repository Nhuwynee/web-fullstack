package do_exercises;

import java.util.Scanner;

public class Ex01TryCatchFinally {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        sqrtNumber();
    }

    private static void sqrtNumber() {
        int number;
        while (true) {
            try {
                System.out.print("Nhập số nguyên: ");
                number = Integer.parseInt(sc.nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException("Không được nhập số âm!");
                }
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Chương trình kết thúc.");
            }
        }
        System.out.println("Căn bậc 2 của " + number + " là: " + Math.sqrt(number));
    }
}
