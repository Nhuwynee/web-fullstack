package ex_afternoon;

import java.util.Scanner;

public class Ex01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập đoạn văn: ");
        String para = sc.nextLine();
        phanLoaiCau(para);
    }

    public static void phanLoaiCau(String text) {
        text = text.replace(" ", "");

        int cauHoi = 0;
        int camThan = 0;
        int tranThuat = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '?') {
                cauHoi++;
            } else if (c == '!') {
                camThan++;
            } else if (c == '.') {
                tranThuat++;
            }
        }
        int total = cauHoi + camThan + tranThuat;
        System.out.println("Tổng số câu: " + total);
        System.out.println("Số câu hỏi (?): " + cauHoi);
        System.out.println("Số câu cảm thán (!): " + camThan);
        System.out.println("Số câu trần thuật (.): " + tranThuat);
    }
}
