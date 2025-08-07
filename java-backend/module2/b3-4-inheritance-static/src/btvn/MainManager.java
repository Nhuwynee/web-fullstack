package btvn;

import java.util.ArrayList;
import java.util.Scanner;

public class MainManager {
        private static ArrayList<NhanSu> danhSachNhanSu = new ArrayList<>();
        private static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Thêm nhân sự");
                System.out.println("2. Hiển thị danh sách nhân sự");
                System.out.println("3. Tìm nhân sự theo tên");
                System.out.println("4. Hiển thị tổng số nhân sự");
                System.out.println("5. Tìm nhân sự có số giờ làm nhiều nhất");
                System.out.println("6. Liệt kê nhân sự có số giờ làm < 20 giờ/tháng");
                System.out.println("7. Hiển thị lương của từng nhân sự");
                System.out.println("8. Thoát chương trình");
                System.out.print("Chọn chức năng: ");
                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số nguyên!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        themNhanSu();
                        break;
                    case 2:
                        hienThiDanhSach();
                        break;
                    case 3:
                        timNhanSuTheoTen();
                        break;
                    case 4:
                        hienThiTongSoNhanSu();
                        break;
                    case 5:
                        timNhanSuGioLamNhieuNhat();
                        break;
                    case 6:
                        lietKeNhanSuItGioLam();
                        break;
                    case 7:
                        hienThiLuong();
                        break;
                    case 8:
                        System.out.println("Thoát chương trình.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        }

        private static void themNhanSu() {
            System.out.print("Chọn loại nhân sự (1: Giảng viên, 2: Trợ giảng): ");
            int loai;
            try {
                loai = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên!");
                return;
            }

            NhanSu nhanSu;
            if (loai == 1) {
                String id = getIdGiangVien();
                nhanSu = new GiangVien();
                nhanSu.setId(id);
            } else if (loai == 2) {
                String id = getIdTroGiang();
                nhanSu = new TroGiang();
                nhanSu.setId(id);
            } else {
                System.out.println("Loại nhân sự không hợp lệ!");
                return;
            }

            nhanSu.input(sc);
            danhSachNhanSu.add(nhanSu);
            System.out.println("Thêm nhân sự thành công!");
        }

        private static void hienThiDanhSach() {
            if (danhSachNhanSu.isEmpty()) {
                System.out.println("Danh sách nhân sự trống!");
                return;
            }
            System.out.println("--------DANH SÁCH NHÂN SỰ--------");
            for (NhanSu ns : danhSachNhanSu) {
                ns.hienThiThongTin();
            }
        }

        private static void timNhanSuTheoTen() {
            System.out.print("Nhập tên cần tìm: ");
            String tenCanTim = sc.nextLine().trim().toLowerCase();

            boolean timThay = false;
            for (NhanSu ns : danhSachNhanSu) {
                if (ns.getName().toLowerCase().contains(tenCanTim)) {
                    ns.hienThiThongTin();
                    timThay = true;
                }
            }

            if (!timThay) {
                System.out.println("Không tìm thấy nhân sự nào có tên chứa \"" + tenCanTim + "\".");
            }
        }


        private static void hienThiTongSoNhanSu() {
            System.out.println("Tổng số nhân sự: " + danhSachNhanSu.size());
        }


        private static void timNhanSuGioLamNhieuNhat() {
            if (danhSachNhanSu.isEmpty()) {
                System.out.println("Danh sách nhân sự trống!");
                return;
            }

            NhanSu maxGio = danhSachNhanSu.get(0);
            for (NhanSu ns : danhSachNhanSu) {
                if (ns.getSoGioLam() > maxGio.getSoGioLam()) {
                    maxGio = ns;
                }
            }

            System.out.println("Nhân sự có số giờ làm việc nhiều nhất:");
            maxGio.hienThiThongTin();
        }


        private static void lietKeNhanSuItGioLam() {
            boolean coNguoi = false;
            for (NhanSu ns : danhSachNhanSu) {
                if (ns.getSoGioLam() < 20) {
                    ns.hienThiThongTin();
                    coNguoi = true;
                }
            }

            if (!coNguoi) {
                System.out.println("Không có nhân sự nào làm dưới 20 giờ/tháng.");
            }
        }


        private static void hienThiLuong() {
            if (danhSachNhanSu.isEmpty()) {
                System.out.println("Danh sách nhân sự trống!");
                return;
            }

            System.out.println("Lương của từng nhân sự:");
            for (NhanSu ns : danhSachNhanSu) {
                System.out.printf("Tên: %-20s | Lương: %.0f VND%n", ns.getName(), ns.tinhLuong());
            }
        }

        private static String getIdGiangVien() {
        int max = 0;
        for (NhanSu p : danhSachNhanSu) {
            if (p instanceof GiangVien) {
                String idStr = p.getId().substring(3);
                int id = Integer.parseInt(idStr);
                if (id > max) {
                    max = id;
                }
            }
        }

        if (max == 0) {
            return "GV00001";
        }

        return String.format("GV%05d", max + 1);
    }

    private static String getIdTroGiang() {
        int max = 0;
        for (NhanSu p : danhSachNhanSu) {
            if (p instanceof TroGiang) {
                String idStr = p.getId().substring(3);
                int id = Integer.parseInt(idStr);
                if (id > max) {
                    max = id;
                }
            }
        }

        if (max == 0) {
            return "TG00001";
        }

        return String.format("TG%05d", max + 1);
    }
}