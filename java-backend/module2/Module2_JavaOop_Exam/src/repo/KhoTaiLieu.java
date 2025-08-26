package repo;

import model.TaiLieu;

import java.util.ArrayList;
import java.util.List;

public class KhoTaiLieu<T extends TaiLieu> {
    private final List<T> danhSach = new ArrayList<>();

    public List<T> layTatCa() {// trả về bản sao danh sách
        return new ArrayList<>(danhSach);
    }

    public void them(T taiLieu) {
        danhSach.add(taiLieu);
    }

    // ===== Hoàn thiện code phần body cho các phương thức xoaTheoMa(), timTheoMa(), timTheoTieuDe()
    public boolean xoaTheoMa(int ma) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaTaiLieu() == ma) {
                danhSach.remove(i);
                return true;
            }
        }
        return false;
    }

    public T timTheoMa(int ma) {
        for (T tl : danhSach) {
            if (tl.getMaTaiLieu() == ma) {
                return tl;
            }
        }
        return null;

    }

    public List<T> timTheoTieuDe(String tuKhoa) {
        List<T> ketQua = new ArrayList<>();
        for (T tl : danhSach) {
            if (tl.getTieuDe().toLowerCase().contains(tuKhoa.toLowerCase())) {
                ketQua.add(tl);
            }
        }
        return ketQua;
    }
}
