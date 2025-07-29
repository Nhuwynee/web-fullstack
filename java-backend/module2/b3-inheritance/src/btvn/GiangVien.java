package btvn;

import java.util.Scanner;

class GiangVien extends NhanSu {
    private String monGiangDay;

    public GiangVien() {
        super();
    }

    public GiangVien(String id, String name, int age, float soGioLam, String monGiangDay) {
        super(id, name, age, soGioLam);
        this.monGiangDay = monGiangDay;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        while (true) {
            System.out.print("Nhập môn giảng dạy: ");
            this.monGiangDay = sc.nextLine().trim();
            if (!monGiangDay.isEmpty()) {
                break;
            }
            else {
                System.out.println("Môn giảng dạy không được để trống!");
            }
        }
    }

    @Override
    public double tinhLuong() {
        return soGioLam * 200000;
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Giảng viên: %-5s | %-20s | %-5d | %-10.1f | Môn giảng dạy:%-15s | Lương: %.0f%n",
                id, name, age, soGioLam, monGiangDay, tinhLuong());
    }
}
