package do_exercises;

import java.util.Scanner;

public class Ex02ThrowThrows {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int a = enterIntegerNumber();
        int b = enterIntegerNumber();
        double result = 0;
        try {
            result = divide(a, b);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Kết quả phép chia: " + result);

    }

    private static double divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException ("Không thể chia cho 0.");
        } else {
            return (double) a / b;
        }
    }

    private static int enterIntegerNumber() {
        int number;
        while (true) {
            try {
                System.out.print("Nhập số: ");
                number = Integer.parseInt(sc.nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException("Không được nhập số âm!");
                }
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return number;
    }

    private static int enterIntegerNumber2() throws ArithmeticException {
        int number;
        while (true) {
            try {
                System.out.print("Nhập số b: ");
                number = Integer.parseInt(sc.nextLine());
                if (number < 0) {
                    throw new IllegalArgumentException("Không được nhập số âm!");
                }
                if (number == 0) {
                    throw new ArithmeticException("Không thể chia cho 0");
                }
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return number;
    }
}
