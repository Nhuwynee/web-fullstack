package model;

import core.MuonTra;

public class TapChi extends TaiLieu implements MuonTra {
    private int soPhatHanh;
    private int thangPhatHanh;
    private String issn;

    // ===== Hoàn thiện code phần body cho constructor =====
    public TapChi(String tieuDe, int namXuatBan, int soPhatHanh, int thangPhatHanh, String issn) {
        // Hoàn thiện code phần body
        super(tieuDe, namXuatBan);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
        this.issn = issn;
    }

    public int getSoPhatHanh() {
        return soPhatHanh;
    }

    public int getThangPhatHanh() {
        return thangPhatHanh;
    }

    public String getIssn() {
        return issn;
    }

    @Override
    public void muon() {
        System.out.println("Mượn thành công tạp chí: " + getTieuDe() +
                " (Số " + soPhatHanh + ", Tháng " + thangPhatHanh + ")");
    }

    @Override
    public void tra() {
        System.out.println("Trả thành công tạp chí: " + getTieuDe() +
                " (Số " + soPhatHanh + ", Tháng " + thangPhatHanh + ")");
    }

    @Override
    public String toString() { return super.toString() +
            "TapChi { soPhatHanh=" + soPhatHanh +
            ", thangPhatHanh=" + thangPhatHanh +
            ", issn='" + issn + '\'' +
            '}';
    }
}
