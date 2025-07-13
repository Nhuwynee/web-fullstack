package ex_afternoon;

import java.util.Scanner;

import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mật khẩu cần kiểm tra: ");
        String password = scanner.nextLine();

        boolean isValid = kiemTraPassword(password);
        System.out.println("Kết quả: " + isValid);
    }

    public static boolean kiemTraPassword(String pwd) {
        if (pwd.length() < 8) {
            System.out.println("Mật khẩu phải có ít nhất 8 ký tự.");
            return false;
        }

        if (!pwd.matches(".*[A-Z].*")) {
            System.out.println("Thiếu chữ in hoa.");
            return false;
        }

        if (!pwd.matches(".*[a-z].*")) {
            System.out.println("Thiếu chữ thường.");
            return false;
        }

        if (!pwd.matches(".*\\d.*")) {
            System.out.println("Thiếu chữ số.");
            return false;
        }

        if (!pwd.matches(".*[@#$%!].*")) {
            System.out.println("Thiếu ký tự đặc biệt (@, #, $, %, !).");
            return false;
        }

        return true;
    }
}

