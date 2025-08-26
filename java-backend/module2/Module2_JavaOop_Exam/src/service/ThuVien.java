package service;

import exception.NgoaiLeIsbnTrungLap;
import io.DocCsv;
import model.*;
import repo.KhoTaiLieu;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ThuVien {
    private final KhoTaiLieu<TaiLieu> kho;
    private final Set<String> tapIsbn = new HashSet<>(); // đảm bảo ISBN duy nhất cho Sách



    public ThuVien() {
        this.kho = new KhoTaiLieu<>();
    }

    public void themTaiLieu(TaiLieu tl) throws NgoaiLeIsbnTrungLap {
        if (tl instanceof Sach sach) {
            String isbn = sach.getIsbn();
            if (tapIsbn.contains(isbn)) {
                throw new NgoaiLeIsbnTrungLap("ISBN đã tồn tại: " + isbn);
            }
            tapIsbn.add(isbn);
        }
        kho.them(tl);
    }

    public boolean xoaTaiLieu(int ma) {
        TaiLieu tl = kho.timTheoMa(ma);
        if (tl instanceof Sach sach) {
            tapIsbn.remove(sach.getIsbn());
        }
        return kho.xoaTheoMa(ma);
    }

    public TaiLieu timTheoMa(int ma) {
        return kho.timTheoMa(ma);
    }

    public List<TaiLieu> timTheoTieuDe(String tuKhoa) {
        return kho.timTheoTieuDe(tuKhoa);
    }

    public List<TaiLieu> layTatCaTaiLieu() {
        return kho.layTatCa();
    }

    public void inTatCaTaiLieu() {
        for (TaiLieu tl : layTatCaTaiLieu()) {
            System.out.println(tl);
        }
    }

    // Hỗ trợ Phần 5: tìm sách theo ISBN
    public Sach timSachTheoIsbn(String isbn) {
        for (TaiLieu tl : kho.layTatCa()) {
            if (tl instanceof Sach sach && sach.getIsbn().equals(isbn)) {
                return sach;
            }
        }
        return null;
    }

    // Thống kê: tác giả -> số sách
    public Map<String, Integer> demSachTheoTacGia() {
        Map<String, Integer> ketQua = new HashMap<>();
        for (TaiLieu tl : kho.layTatCa()) {
            if (tl instanceof Sach sach) {
                ketQua.put(sach.getTacGia(),
                        ketQua.getOrDefault(sach.getTacGia(), 0) + 1);
            }
        }
        return ketQua;
    }

    // Thống kê: năm xuất bản -> số tài liệu
    public Map<Integer, Long> demTaiLieuTheoNam() {
        Map<Integer, Long> ketQua = new HashMap<>();
        for (TaiLieu tl : kho.layTatCa()) {
            int nam = tl.getNamXuatBan();
            ketQua.put(nam, ketQua.getOrDefault(nam, 0L) + 1);
        }
        return ketQua;
    }

    // Top 3 tác giả nhiều sách, tie-break theo năm xuất bản mới nhất
    public List<String> top3TacGia() {
        Map<String, Integer> soSach = new HashMap<>();
        Map<String, Integer> namMoiNhat = new HashMap<>();

        // ===== Hoàn thiện code cho 3 bước sau =====
        // Bước 1: Đếm số sách & tìm năm xuất bản mới nhất của từng tác giả
        for (TaiLieu tl : kho.layTatCa()) {
            if (tl instanceof Sach sach) {
                String tacGia = sach.getTacGia();
                soSach.put(tacGia, soSach.getOrDefault(tacGia, 0) + 1);
                namMoiNhat.put(tacGia,
                        Math.max(namMoiNhat.getOrDefault(tacGia, 0), sach.getNamXuatBan()));
            }
        }

        // Bước 2: Gom thông tin lại thành danh sách để sắp xếp
        List<Map.Entry<String, Integer>> ds = new ArrayList<>(soSach.entrySet());
        ds.sort((a, b) -> {

            // số sách giảm dần
            int cmp = Integer.compare(b.getValue(), a.getValue());
            if (cmp == 0) {
                // tie-break sort năm xuất bản mới nhất giảm dần.
                return Integer.compare(namMoiNhat.get(b.getKey()), namMoiNhat.get(a.getKey()));
            }
            return cmp;
        });

        // Bước 3: Lấy 3 người đầu tiên
        List<String> ketQua = new ArrayList<>();
        for (int i = 0; i < Math.min(3, ds.size()); i++) {
            ketQua.add(ds.get(i).getKey());
        }
        return ketQua;
    }

}
