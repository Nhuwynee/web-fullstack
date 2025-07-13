package ex_afternoon;

import java.util.Scanner;

public class Ex04Sqrt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập 1 số nguyên dương: ");
        int number = sc.nextInt();
        String result;
        if (number < 0) {
            System.out.println("Nhập sai yêu cầu.");
            return;
        } else {
            result = (Math.sqrt(number) % 1 == 0 ? " là số chính phương" : " không phải số chính phương");
        }
        System.out.println(number + result);
    }
}
