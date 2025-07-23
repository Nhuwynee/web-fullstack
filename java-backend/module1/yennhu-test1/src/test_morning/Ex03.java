package test_morning;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.print("Nhập chuỗi: ");
        String str = sc.nextLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                count++;
            }
        }
        System.out.println(count == 0 ? ("Chuỗi " + str + " không chứa ký tự in hoa.") : ("Chuỗi chứa " + count + " ký tự in hoa."));
    }
}
