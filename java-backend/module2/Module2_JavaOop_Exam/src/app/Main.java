package app;

import exception.NgoaiLeHetSach;
import exception.NgoaiLeIsbnTrungLap;
import io.DocCsv;
import io.GhiBaoCao;
import model.*;
import service.ThuVien;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String PATH_DOCUMENT_FILE = "E:\\web-fullstack\\java-backend\\module2\\Module2_JavaOop_Exam\\data\\danh_sach_tai_lieu.csv";
    private static final String PATH_REPORT_FILE = "bao_cao_thu_vien.txt";

    // Queue & Stack toàn cục cho yêu cầu và undo
    private static final Queue<YeuCau> hangCho = new LinkedList<>();
    private static final Stack<YeuCau> lichSu = new Stack<>();

    public static void main(String[] args) {
        ThuVien tv = new ThuVien();

        // ===== Thêm sách demo =====
        try {
            Sach s1 = new Sach("Java Cơ Bản", 2021, "Luu Ngoc Yen Nhu", 4, "9771234567005");
            Sach s2 = new Sach("OOP Nâng Cao", 2022, "Luu Ngoc Yen Nhu", 1, "9771234567006");
            TapChi tc1 = new TapChi("Tạp chí CNTT", 2022, 12, 1, "ISSN123456");

            tv.themTaiLieu(s1);
            tv.themTaiLieu(s2);
            tv.themTaiLieu(tc1);
        } catch (NgoaiLeIsbnTrungLap e) {
            System.out.println("Lỗi khi thêm tài liệu: " + e.getMessage());
        }

        System.out.println("\n=== Danh sách tài liệu hiện tại (chưa read) ===");
        tv.inTatCaTaiLieu();

        // ===== Xóa theo mã =====
        if (tv.xoaTaiLieu(5)) System.out.println("\nXóa thành công tài liệu có mã 5");
        else System.out.println("\nKhông tìm thấy tài liệu có mã 5");

        // ===== Tìm theo tiêu đề =====
        List<TaiLieu> kq = tv.timTheoTieuDe("OOP");
        System.out.println("\n=== Kết quả tìm theo tiêu đề 'OOP' ===");
        if (kq.isEmpty()) System.out.println("Không tìm thấy!");
        else kq.forEach(System.out::println);

        // ===== Thêm yêu cầu chỉ cho sách =====
        System.out.println("\n==== Demo Theem Yêu Cầu === \n");
        themYeuCau(new YeuCau(LoaiYeuCau.MUON, "9771234567005", "KH001"), tv);
        themYeuCau(new YeuCau(LoaiYeuCau.MUON, "9771234567006", "KH002"), tv);
        themYeuCau(new YeuCau(LoaiYeuCau.TRA, "9771234567005", "KH001"), tv);
        themYeuCau(new YeuCau(LoaiYeuCau.MUON, "9771234567005", "KH003"), tv);

        // ===== Xử lý yêu cầu =====
        xuLyYeuCau(tv);

        // ===== Undo hành động cuối cùng =====
        undo(tv);

        // ===== Đọc CSV =====
        try {
            DocCsv.doc(PATH_DOCUMENT_FILE, tv);
            System.out.println("\nĐọc file CSV thành công!");
            System.out.println("\n=== Danh sách tài liệu sau khi đọc CSV ===");
            tv.inTatCaTaiLieu();
        } catch (IOException | NgoaiLeIsbnTrungLap e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }

        // ===== Ghi báo cáo =====
        try {
            GhiBaoCao.ghi(PATH_REPORT_FILE, tv);
            System.out.println("\nĐã ghi báo cáo ra " + PATH_REPORT_FILE);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // ===== Hàm thêm yêu cầu chỉ khi ISBN là sách =====
    private static void themYeuCau(YeuCau yc, ThuVien tv) {
        if (tv.timSachTheoIsbn(yc.getIsbn()) != null) {
            hangCho.offer(yc);
            System.out.println("Đã thêm yêu cầu: " + yc);
        } else {
            System.out.println("Bỏ qua yêu cầu, ISBN không phải sách: " + yc);
        }
    }

    // ===== Xử lý Queue =====
    private static void xuLyYeuCau(ThuVien tv) {
        System.out.println("\n=== Xử lý yêu cầu (chỉ cho sách) ===");
        while (!hangCho.isEmpty()) {
            YeuCau yc = hangCho.poll();
            Sach sach = tv.timSachTheoIsbn(yc.getIsbn());

            if (sach == null) {
                System.out.println("Không tìm thấy sách ISBN: " + yc.getIsbn());
                continue;
            }

            try {
                if (yc.getLoaiYeuCau() == LoaiYeuCau.MUON) {
                    sach.muon();
                    lichSu.push(yc);
                    System.out.println("Khách " + yc.getMaKhach() + " mượn thành công: " + sach.getTieuDe());
                } else if (yc.getLoaiYeuCau() == LoaiYeuCau.TRA) {
                    sach.tra();
                    lichSu.push(yc);
                    System.out.println("Khách " + yc.getMaKhach() + " trả thành công: " + sach.getTieuDe());
                }
            } catch (NgoaiLeHetSach e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    // ===== Undo hành động cuối cùng =====
    private static void undo(ThuVien tv) {
        System.out.println("\n=== Undo hành động cuối cùng (chỉ cho sách) ===");
        if (!lichSu.isEmpty()) {
            YeuCau yc = lichSu.pop();
            Sach sach = tv.timSachTheoIsbn(yc.getIsbn());
            if (sach != null) {
                try {
                    if (yc.getLoaiYeuCau() == LoaiYeuCau.MUON) {
                        sach.tra();
                        System.out.println("UNDO: Hoàn tác mượn → trả lại sách: " + sach.getTieuDe());
                    } else if (yc.getLoaiYeuCau() == LoaiYeuCau.TRA) {
                        sach.muon();
                        System.out.println("UNDO: Hoàn tác trả → mượn lại sách: " + sach.getTieuDe());
                    }
                } catch (NgoaiLeHetSach e) {
                    System.out.println("UNDO thất bại: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Không có thao tác nào để UNDO.");
        }
    }
}
