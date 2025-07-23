package ex_afternoon;

import java.util.ArrayList;
import java.util.Collections;

public class Task4 {
    /*  Task 4
        Tách nhân viên theo giới tính

        Yêu cầu:
        Công ty có ArrayList<String> chứa tên nhân viên với định dạng "Tên - Nam" hoặc "Tên - Nữ".
        Hãy tách thành 2 danh sách: nhanVienNam và nhanVienNu.
        Sắp xếp mỗi danh sách theo bảng chữ cái và in ra.
        Gợi ý:
        Dùng split("-") để tách tên và giới tính.
     */
    public static void main(String[] args) {

        ArrayList<String> nhanVien = new ArrayList<>();
        nhanVien.add("Nguyễn Văn A - Nam");
        nhanVien.add("Trần Thị B - Nữ");
        nhanVien.add("Lê Văn C - Nam");
        nhanVien.add("Phạm Thị D - Nữ");
        nhanVien.add("Hoàng Văn E - Nam");

        separationEmplGender(nhanVien);
    }

    private static void separationEmplGender (ArrayList<String> employee) {
        ArrayList<String> emplMale = new ArrayList<>();
        ArrayList<String> emplFemale = new ArrayList<>();

        for (String nv : employee) {
            String[] parts = nv.split("-");
            String name = parts[0].trim();
            String gender = parts[1].trim();

            if (gender.equalsIgnoreCase("Nam")) {
                emplMale.add(name);
            } else if (gender.equalsIgnoreCase("Nữ")) {
                emplFemale.add(name);
            }
        }

        Collections.sort(emplMale);
        Collections.sort(emplFemale);

        System.out.println("Danh sách nhân viên Nam:");
        for (String ten : emplMale) {
            System.out.println(ten);
        }

        System.out.println("\nDanh sách nhân viên Nữ:");
        for (String ten : emplFemale) {
            System.out.println(ten);
        }
    }
}
