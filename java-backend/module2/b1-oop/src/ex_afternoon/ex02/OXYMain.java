package ex_afternoon.ex02;

import ex_afternoon.ex01.Number;

public class OXYMain {

    public static void main(String[] args) {
        OXY o1 = new OXY();
        OXY o2 = new OXY();

        System.out.println("===== Nhập tọa độ điểm thứ nhất =====");
        o1.input();

        System.out.println("===== Nhập tọa độ điểm thứ hai =====");
        o2.input();

        System.out.println("\n - Tọa độ điểm thứ nhất =====");
        o1.output();

        System.out.println("\n - Tọa độ điểm thứ hai =====");
        o2.output();

        System.out.printf("Khoảng cách giữa 2 điểm: %.2f\n", OXY.distance(o1, o2));
    }

}
