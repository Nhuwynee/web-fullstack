package ex_morning;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chuỗi 1: ");
        String str1 = sc.nextLine();
        System.out.println("Nhập chuỗi 2: ");
        String str2 = sc.nextLine();

        int result = str1.compareTo(str2);
        if (result == 0) {
            System.out.println("Chuỗi 1 = chuỗi 2");
        } else if (result > 0) {
            System.out.println("Chuỗi 1 > chuỗi 2");
        } else {
            System.out.println("Chuỗi 1 < chuỗi 2");
        }
    }

}
