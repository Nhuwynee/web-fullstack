package ex_afternoon;

import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mật khẩu cần kiểm tra: ");
        String password = scanner.nextLine();

        kiemTraPassword(password);
    }

    // length , length(), size()
    // break, continue
    // .class, .java

    public static void kiemTraPassword(String pwd) {
        StringBuilder stringBuilder = new StringBuilder();

        if (pwd.length() < 8) {
            stringBuilder.append("Mật khẩu phải có ít nhất 8 ký tự.\n");
        }

        if (!pwd.matches(".*[A-Z].*")) {
            stringBuilder.append("Thiếu chữ in hoa.\n");
        }

        if (!pwd.matches(".*[a-z].*")) {
            stringBuilder.append("Thiếu chữ thường.\n");
        }

        if (!pwd.matches(".*\\d.*")) {
            stringBuilder.append("Thiếu chữ số.\n");
        }

        if (!pwd.matches(".*[@#$%!].*")) {
            stringBuilder.append("Thiếu ký tự đặc biệt (@, #, $, %, !).\n");
        }

        System.out.println((stringBuilder.isEmpty()) ? "Mật khẩu của bạn mạnh !!!" : stringBuilder);
    }
}

