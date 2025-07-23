package ex_afternoon;

import java.util.ArrayList;

public class Task2 {
    /*
            Đếm số lượng nhân viên theo họ

            Yêu cầu:
            Thống kê số lượng nhân viên theo từng họ .
            In danh sách kết quả.
            Gợi ý:
            Tách họ bằng split(" ") → lấy phần tử đầu tiên.
            Sử dụng startWith khi đếm họ
     */
    public static void main(String[] args) {
        ArrayList<String> nhanVien = new ArrayList<>();
        nhanVien.add("Nguyễn Văn A - Nam");
        nhanVien.add("Nguyễn Thị B - Nữ");
        nhanVien.add("Li Văn C - Nam");
        nhanVien.add("Linh Thị D - Nữ");
        nhanVien.add("Hoàng Văn E - Nam");

        separationEmplLastName2(nhanVien);
    }

    private static void separationEmplLastName(ArrayList<String> employee) {

        ArrayList<String> danhSachHo = new ArrayList<>();
        ArrayList<Integer> demSoLuong = new ArrayList<>();

        for (String nv : employee) {
            String[] parts = nv.split(" ");
            String ho = parts[0];

            int index = danhSachHo.indexOf(ho); // có trả về index >= 0, ko trả về -1

            if (index != -1) {
                demSoLuong.set(index, demSoLuong.get(index) + 1);
            } else {
                danhSachHo.add(ho);
                demSoLuong.add(1);
            }
        }

        System.out.println("Thống kê số lượng nhân viên theo họ:\n");
        for (int i = 0; i < danhSachHo.size(); i++) {
            System.out.println("Họ " + danhSachHo.get(i) + ": " + demSoLuong.get(i) + " nhân viên");
        }
    }

    private static void separationEmplLastName2(ArrayList<String> employee) {
        ArrayList<String> danhSachHo = new ArrayList<>();
        ArrayList<Integer> demSoLuong = new ArrayList<>();

        for (String nv : employee) {
            String[] parts = nv.split(" ");
            String ho = parts[0];

            boolean daTonTai = false;

            for (int i = 0; i < danhSachHo.size(); i++) {
                if (nv.startsWith(danhSachHo.get(i) + " ")) {
                    demSoLuong.set(i, demSoLuong.get(i) + 1);
                    daTonTai = true;
                    break;
                }
            }

            if (!daTonTai) {
                danhSachHo.add(ho);
                demSoLuong.add(1);
            }
        }

        System.out.println("Thống kê số lượng nhân viên theo họ:\n");
        for (int i = 0; i < danhSachHo.size(); i++) {
            System.out.println("Họ " + danhSachHo.get(i) + ": " + demSoLuong.get(i) + " nhân viên");
        }
    }

}