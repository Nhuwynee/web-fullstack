package io;

import exception.NgoaiLeIsbnTrungLap;
import model.Sach;
import model.TaiLieu;
import model.TapChi;
import service.ThuVien;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DocCsv {
    public static void doc(String duongDan, ThuVien tv) throws IOException, NgoaiLeIsbnTrungLap {
        File file = new File(duongDan);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String loai = data[0].trim().toUpperCase();

                if ("SACH".equals(loai)) {
                    String tieuDe = data[1].trim();
                    int namXuatBan = Integer.parseInt(data[2].trim());
                    String tacGia = data[3].trim();
                    int soLuongConLai = Integer.parseInt(data[4].trim());
                    String isbn = data[5].trim();

                    Sach sach = new Sach(tieuDe, namXuatBan, tacGia, soLuongConLai, isbn);
                    tv.themTaiLieu(sach);
                } else if ("TAPCHI".equals(loai)) {
                    String tieuDe = data[1].trim();
                    int namXuatBan = Integer.parseInt(data[2].trim());
                    int soPhatHanh = Integer.parseInt(data[3].trim());
                    int thangPhatHanh = Integer.parseInt(data[4].trim());
                    String issn = data[5].trim();

                    TapChi tapChi = new TapChi(tieuDe, namXuatBan, soPhatHanh, thangPhatHanh, issn);
                    tv.themTaiLieu(tapChi);
                }
            }
        }
    }
}
