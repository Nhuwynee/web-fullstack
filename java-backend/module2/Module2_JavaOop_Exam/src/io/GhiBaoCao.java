package io;

import model.Sach;
import model.TaiLieu;
import model.TapChi;
import service.ThuVien;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GhiBaoCao {
    public static void ghi(String duongDan, ThuVien tv) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(duongDan));

        List<TaiLieu> all = tv.layTatCaTaiLieu();

        // Đếm số sách và tạp chí
        int soSach = 0, soTapChi = 0;
        for (TaiLieu t : all) {
            if (t instanceof Sach) soSach++;
            else if (t instanceof TapChi) soTapChi++;
        }

        bw.write("Tổng số tài liệu: " + all.size());
        bw.newLine();
        bw.write("Tổng số sách: " + soSach);
        bw.newLine();
        bw.write("Tổng số tạp chí: " + soTapChi);
        bw.newLine();
        bw.write("Top 3 tác giả nhiều sách: " + tv.top3TacGia());
        bw.newLine();

        bw.close();
    }
}
