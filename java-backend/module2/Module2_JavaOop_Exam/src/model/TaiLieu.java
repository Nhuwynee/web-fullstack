package model;

import java.util.Objects;

public abstract class TaiLieu {
    private static int dem = 0;
    private final int maTaiLieu;
    private String tieuDe;
    private int namXuatBan;

    protected TaiLieu(String tieuDe, int namXuatBan) {
        this.maTaiLieu = ++dem;
        this.tieuDe = tieuDe;
        this.namXuatBan = namXuatBan;
    }

    // ===== Hãy tạo getter và setter =====


    public static int getDem() {
        return dem;
    }

    public static void setDem(int dem) {
        TaiLieu.dem = dem;
    }

    public int getMaTaiLieu() {
        return maTaiLieu;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }


    // ===== Hoàn thiện code phần body cho các phương thức toString(), equals(), hashCode() =====

    @Override
    public String toString() {
        return "TaiLieu{" +
                "maTaiLieu=" + maTaiLieu +
                ", tieuDe='" + tieuDe + '\'' +
                ", namXuatBan=" + namXuatBan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaiLieu taiLieu = (TaiLieu) o;
        return maTaiLieu == taiLieu.maTaiLieu && namXuatBan == taiLieu.namXuatBan && Objects.equals(tieuDe, taiLieu.tieuDe);
    }


    @Override
    public int hashCode() {
        return Objects.hash(maTaiLieu, tieuDe, namXuatBan);
    }
}
