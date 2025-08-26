package model;

import core.MuonTra;
import exception.NgoaiLeHetSach;

public class Sach extends TaiLieu implements MuonTra {
    private String tacGia;
    private int soLuongConLai;
    private String isbn;

    // ===== Hoàn thiện code phần body cho constructor
    public Sach(String tieuDe, int namXuatBan, String tacGia, int soLuongConLai, String isbn) {
        super(tieuDe, namXuatBan);
        this.tacGia = tacGia;
        this.soLuongConLai = soLuongConLai;
        this.isbn = isbn;
    }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public int getSoLuongConLai() { return soLuongConLai; }
    public void setSoLuongConLai(int soLuongConLai) { this.soLuongConLai = soLuongConLai; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    // ===== Hoàn thiện code phần body cho các phương thức muon(), tra(), toString()
    @Override
    public void muon() throws NgoaiLeHetSach {
        if (soLuongConLai > 0) {
            soLuongConLai--;
            System.out.println("Mượn thành công sách: " + getTieuDe() +
                    ". Số lượng còn lại: " + soLuongConLai);
        } else {
            throw new NgoaiLeHetSach("Không còn sách '" + getTieuDe() + "' để mượn!");
        }
    }

    @Override
    public void tra() {
        soLuongConLai++;
        System.out.println("Trả thành công sách: " + getTieuDe() +
                ". Số lượng còn lại: " + soLuongConLai);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Sach{tacGia='" + tacGia + '\'' +
                ", soLuongConLai=" + soLuongConLai +
                ", isbn='" + isbn + '\'' +
                '}';
    }


}
