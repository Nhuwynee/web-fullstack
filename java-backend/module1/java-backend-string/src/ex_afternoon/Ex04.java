package ex_afternoon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập họ và tên nhân viên: ");
//        String employee = sc.nextLine();
        String test = "Luu Ngoc Yen Nhu";
        StringBuilder output = new StringBuilder();

        // input: Luu Ngoc Yen Nhu
        // output: LUUNNHU20250711 T HH:mm:ss

        String[] str = test.split(" ");
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                output.append(str[i].substring(0, 3).toUpperCase(Locale.ROOT));
            } else if (i == str.length - 1) {
                output.append(str[i].toUpperCase(Locale.ROOT));
            } else {
                output.append(str[i].substring(0,1).toUpperCase(Locale.ROOT));
            }
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        output.append(now.format(formatter));

        System.out.println("Mã nhân viên: " + output);
    }
}
