package model;

import exception.NgoaiLeHetSach;
import service.ThuVien;

public class HanhDong {
    private final LoaiYeuCau loai; // MUON/TRA
    private final String isbn;

    public HanhDong(LoaiYeuCau loai, String isbn) {
        this.loai = loai;
        this.isbn = isbn;
    }

    // Đảo ngược: nếu đã MUON thành công -> TRA; nếu đã TRA -> MUON (không kiểm tra khách)
    public void undo(ThuVien tv) throws NgoaiLeHetSach {
        Sach s = tv.timSachTheoIsbn(isbn);

        if (s == null) return;

        if (loai == LoaiYeuCau.MUON) {
            s.tra();
        } else if (loai == LoaiYeuCau.TRA) {
            s.muon();
        }
    }
}
