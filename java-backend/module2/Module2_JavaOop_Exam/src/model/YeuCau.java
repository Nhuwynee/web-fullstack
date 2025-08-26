package model;

public class YeuCau {
    private final LoaiYeuCau loaiYeuCau;
    private final String isbn;
    private final String maKhach; // chưa dùng sâu, để mở rộng

    public YeuCau(LoaiYeuCau loaiYeuCau, String isbn, String maKhach) {
        this.loaiYeuCau = loaiYeuCau;
        this.isbn = isbn;
        this.maKhach = maKhach;
    }

    public LoaiYeuCau getLoaiYeuCau() {
        return loaiYeuCau;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMaKhach() {
        return maKhach;
    }

    @Override
    public String toString() {
        return "YeuCau{" +
                "loaiYeuCau=" + loaiYeuCau +
                ", isbn='" + isbn + '\'' +
                ", maKhach='" + maKhach + '\'' +
                '}';
    }
}