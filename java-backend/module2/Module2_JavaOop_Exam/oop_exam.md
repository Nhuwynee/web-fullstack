# ĐỀ THI JAVA OOP (3 TIẾNG) – HỆ THỐNG QUẢN LÝ THƯ VIỆN

---

## Phần 1 — Thiết kế lớp theo OOP (20đ)

### A. Tạo abstract class `TaiLieu` (lớp trừu tượng):

- Thuộc tính `private`:
    - `maTaiLieu`: int (final – không thay đổi sau khi khởi tạo đối tượng)
    - `tieuDe`: String
    - `namXuatBan`: int

- Tạo `constructor` để cho phép khởi tạo đối tượng:
    - `Constructor` không nhận tham số `maTaiLieu`, chỉ nhận `tieuDe`, `namXuatBan`
    - Bên trong constructor: `this.maTaiLieu = ++dem`

- Tạo các `getter/setter` hợp lý (không tạo `setter` cho `maTaiLieu`).

- Override (ghi đè) `toString()` để in ra thông tin tài liệu.

- Dùng biến `dem` với từ khóa `static` để tự động tăng `maTaiLieu` bên trong constructor.

### B. Tạo interface `MuonTra`:

```java
public interface MuonTra {
    void muon();

    void tra();
}
```

### C. Tạo 2 lớp con kế thừa TaiLieu:

#### 1. Lớp `Sach`:

- Thuộc tính riêng: `tacGia: String`, `soLuongConLai: int`, `isbn: String`
- Gọi constructor lớp cha khi tạo constructor lớp `Sach`
- Override `toString()` từ lớp cha và in thêm thông tin các thuộc tính riêng của lớp `Sach`
- Implement (triển khai) interface `MuonTra`:
    - `muon()`: nếu `soLuongConLai > 0` thì giảm 1, ngược lại ném ngoại lệ `NgoaiLeHetSach`.
    - `tra()`: tăng `soLuongConLai` lên 1.

#### 2. Lớp `TapChi`:

- Thuộc tính: `soPhatHanh: int`, `thangPhatHanh: int`, `issn: String`
- Gọi constructor lớp cha khi tạo constructor lớp `TapChi`
- Override `toString()` từ lớp cha và in thêm thông tin các thuộc tính riêng của lớp `TapChi`
- Cũng implement (triển khai) `MuonTra` với logic đơn giản:
    - `muon()`: in ra tiêu đề tạp chí đã mượn.
    - `tra()`: in ra tiêu đề tạp chí đã trả.

### D. Viết lớp Main để test chương trình:

- Tạo ít nhất 1 đối tượng `Sach`, 1 đối tượng `TapChi`
- Gọi các phương thức `muon()`, `tra()` để kiểm tra logic
- In ra thông tin tài liệu bằng `System.out.println(...)`

```java
public class Main {
    public static void main(String[] args) {
        Sach sach1 = new Sach("Lập trình Java", 2020, "Nguyễn Văn A", 2, "9780134685991");
        TapChi tapChi1 = new TapChi("Công nghệ 4.0", 2024, 12, 8, "9771234567005");

        System.out.println(sach1);
        System.out.println(tapChi1);

        // Cần thêm xử lý try/catch bắt lỗi
        sach1.muon(); // còn sách
        sach1.muon(); // còn sách
        sach1.muon(); // hết sách

        sach1.tra();
        System.out.println(sach1);

        tapChi1.muon();
        tapChi1.tra();
    }
}
```

---

## Phần 2 — Thiết kế lớp `KhoTaiLieu` (Generic class + List) (20đ)

### A. Tạo lớp `KhoTaiLieu<T>` (Generic class)

- Tạo class `KhoTaiLieu<T>` với ràng buộc `T extends TaiLieu`, dùng `ArrayList<T>` để lưu danh sách tài liệu.
- Hãy viết các phương thức `them(...)`, `xoaTheoMa(...)`, `timTheoMa(...)`, `timTheoTieuDe(...)`:

```java
public class KhoTaiLieu<T extends TaiLieu> {
    private List<T> danhSach = new ArrayList<>();

    // Phương thức lấy tất tài liệu trong danh sách
    public List<T> layTatCa() {
        return new ArrayList<>(danhSach);
    }

    // Phương thức thêm tài liệu

    // Phương thức xóa tài liệu theo mã 

    // Phương thức tìm tài liệu theo mã

    // Phương thức tìm tài liệu theo tiêu đề

}
```

### B. Tạo lớp `ThuVien` để quản lý `KhoTaiLieu<T>`

- Tạo class ThuVien có thuộc tính `kho`
- Tạo các phương thức sử dụng `kho`

```java
public class ThuVien {
    private KhoTaiLieu<TaiLieu> kho;

    public ThuVien() {
        this.kho = new KhoTaiLieu<>();
    }

    public void themTaiLieu(TaiLieu tl) {
        kho.them(tl);
    }

    public boolean xoaTaiLieu(int ma) {
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
}
```

### C. Sử dụng lớp Main để test

---

## Phần 3 — Phân tích dữ liệu (Set/Map) (20đ)

### A. Đảm bảo ISBN của sách không trùng:

- Dùng HashSet<String> để lưu các ISBN đã tồn tại.
- Kiểm tra trùng ISBN khi thêm mới `Sach` vào `ThuVien` → nếu trùng thì ném `NgoaiLeIsbnTrungLap`.

### B. Thống kê bằng `Map`:

- Tạo Map<String, Integer> để đếm số sách theo tác giả.
- Tạo Map<Integer, Long> để đếm số tài liệu theo năm xuất bản.
- Tìm 3 tác giả có nhiều sách nhất (nếu đồng hạng, ưu tiên theo năm xuất bản mới nhất)

---

## Phần 4 — Đọc và ghi file (20đ)

### A. Đọc file danh_sach_tai_lieu.csv:

#### Tạo lớp `DocCsv` để đọc dữ liệu từ file bằng `FileReader` và `BufferedReader`

```
SACH,Lập trình Java,2020,Nguyễn Văn A,3,9780134685991
TAPCHI,Công nghệ 4.0,2024,12,8,9771234567005
```

Dữ liệu file CSV tuân theo thứ tự sau:
`SACH, tieuDe, namXuatBan, tacGia, soLuongConLai, isbn`
`TAPCHI, tieuDe, namXuatBan, soPhatHanh, thangPhatHanh, issn`

### B. Ghi vào file bao_cao_thu_vien.txt:

#### Tạo lớp `GhiBaoCao` để ghi dữ liệu vào file bằng `FileWriter` và `BufferedWriter`

- Tổng số tài liệu
- Tổng số sách
- Tổng số tạp chí
- Top 3 tác giả nhiều sách

---

## Phần 5 — Quy trình mượn/trả (Queue + Stack) (10đ)

### A. Tạo lớp `YeuCau`

- Lớp `YeuCau` gồm: loaiYeuCau, isbn, maKhach

### B. Đưa từng yêu cầu vào `Queue<YeuCau>` (FIFO) và xử lý lần lượt:

- Nếu `MUON`: gọi muon() nếu còn sách.
- Nếu `TRA`: gọi tra().

### C. Mỗi hành động thành công được đưa vào `Stack` để UNDO lại hành động cuối cùng:

- Chỉ push vào `Stack` nếu hành động `MUON/TRA` thành công.
- Đảo ngược thao tác trên 1 phần tử gần nhất

---

## Phần 6 — Xử lý ngoại lệ tùy chỉnh (custom exception) (10đ)

### Tạo các lớp ngoại lệ tùy chỉnh:

- NgoaiLeIsbnTrungLap extends Exception (checked)
- NgoaiLeHetSach extends RuntimeException (unchecked)

### Sử dụng throws và try-catch hợp lý. In thông báo rõ ràng khi có lỗi.

- `NgoaiLeHetSach` có thể không cần khai báo throws, nhưng cần `try-catch` ở Main để in thông báo cho người dùng