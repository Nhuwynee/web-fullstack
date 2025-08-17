package ex_morning;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = "Hello World";
        // a.
        System.out.println("a. " + str.substring(6));
        // b.
        System.out.println("b. " + str.replace("o", "f"));
        // c.
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'l') {
                count++;
            }
        }
        System.out.println("c. Có " + count + " chữ l trong chuỗi.");
        // d.
        System.out.println("d. Vị trí đầu tiền của chữ l: " + (str.indexOf('l') + 1));
        System.out.println("d. Vị trí cuối cùng của chữ l: " + (str.lastIndexOf('l') + 1));
        // e.
        System.out.println("e. " + str.replace(" ", ""));
        // f
        System.out.println("f. " + str.trim());
        // g
        System.out.println("g. ");
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
        // h.
        System.out.println();
        String str2 = "sQC";
        String strNew = (str2.concat(" ")).concat(str);
        System.out.println("h." + strNew);

        // i
        System.out.println("i. " + strNew.toUpperCase());

        // k
        System.out.println("i. " + strNew.toLowerCase());

        // l
        System.out.println("l. ");
        System.out.print("Nhập index n: ");
        int n = sc.nextInt();
        System.out.print("Nhập index m: ");
        int m = sc.nextInt();
        System.out.println("l. " + strNew.substring(n - 1, m));
    }
}
