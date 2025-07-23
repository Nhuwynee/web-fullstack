package ex_morning;

import java.util.Scanner;

public class Ex01SwitchCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số (1 - 10): ");
        int number = scanner.nextInt();
        String result = "";

        switch (number) {
            case 1:
                result = "One";
                break;
            case 2:
                result = "Two";
                break;
            case 3:
                result = "Three";
                break;
            case 4:
                result = "Four";
                break;
            case 5:
                result = "Five";
                break;
            case 6:
                result = "Six";
                break;
            case 7:
                result = "Seven";
                break;
            case 8:
                result = "Eight";
                break;
            case 9:
                result = "Nine";
                break;
            case 10:
                result = "Ten";
                break;
            default:
                result = "Số không hợp lệ";
                break;
        }
        System.out.println("Tiếng anh của số " + number + " là: " + result);
    }
}
