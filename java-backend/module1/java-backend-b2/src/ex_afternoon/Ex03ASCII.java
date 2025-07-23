package ex_afternoon;

import java.util.Scanner;

public class Ex03ASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập 1 ký tự (chữ cái): ");
        String input = sc.nextLine();
        char kyTu = input.charAt(0);

        if (kyTu <= 'A' || kyTu >= 'z' || input.length() != 1) {
            System.out.println("Nhập không đúng yêu cầu.");
            return;
        } else {
            if (kyTu >= 'Z') {
                kyTu -= 32;
            } else {
                kyTu += 32;
            }
        }
        System.out.println(input + " sau thay đổi: "+ kyTu);
    }
}
