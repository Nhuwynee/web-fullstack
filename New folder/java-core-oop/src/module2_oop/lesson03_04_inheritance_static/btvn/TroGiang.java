package btvn;

import java.util.Scanner;

class TroGiang extends NhanSu {
    private String boMonPhuTrach;

    public TroGiang() {
        super();
    }

    public TroGiang(String id, String name, int age, float soGioLam, String boMonPhuTrach) {
        super(id,name, age, soGioLam);
        this.boMonPhuTrach = boMonPhuTrach;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        while (true) {
            System.out.print("Nhập bộ môn phụ trách: ");
            this.boMonPhuTrach = sc.nextLine().trim();
            if (!boMonPhuTrach.isEmpty()) {
                break;
            }
            else {
                System.out.println("Bộ môn không được để trống!");
            }
        }
    }

    @Override
    public double tinhLuong() {
        return soGioLam * 100000;
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Trợ giảng: %-5s | %-20s | %-5d | %-10.1f | Môn phụ trach:%-15s | Lương: %.0f%n",
                id, name, age, soGioLam, boMonPhuTrach, tinhLuong());
    }
}