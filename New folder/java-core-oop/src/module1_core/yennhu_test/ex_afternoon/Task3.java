package ex_afternoon;

import java.util.ArrayList;
import java.util.Comparator;

public class Task3 {
    /*
        3*. Sắp xếp tên nhân viên theo độ dài giảm dần**
        Yêu cầu:**
        - Sắp xếp danh sách nhân viên theo độ dài tên giảm dần.
        - Nếu 2 tên cùng độ dài, sắp theo thứ tự alphabet.
        - Dùng cách sort thủ công với vòng lặp for đã học ở bài trước
        - Dùng Collection.sort với Comparator để đối chiếu kết quả sort thủ công
        Gợi ý:  Collection.sort với Comparator**
        - B1: Tạo class NameLengthComparator implements Comparator<String>
        - B2: Override method compare(String s1, String s2) với logic so sánh tự viết
        - B3: Gọi Collections.sort(list, new NameLengthComparator())
     */

    public static void main(String[] args) {
        ArrayList<String> nhanVien = new ArrayList<>();
        nhanVien.add("Nguyễn Văn An - Nam");
        nhanVien.add("Nguyễn Thị Bình - Nữ");
        nhanVien.add("Li Văn Cường - Nam");
        nhanVien.add("Linh Thị Minh - Nữ");
        nhanVien.add("Hoàng Văn Nhi - Nam");

        sapXepTenGiamDan(nhanVien);
    }

    private static void sapXepTenGiamDan(ArrayList<String> arr) {
        ArrayList<String> names = new ArrayList<>();

        for (String nv : arr) {
            String[] split = nv.split("-");
            String fullName = split[0].trim();
            String[] parts = fullName.split(" ");
            String name = parts[parts.length - 1];
            names.add(name);
        }

        // sx c1
        names.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o2.length() - o1.length();
            }
            return o1.compareTo(o2);
        });

        // sx c2
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                }
                return o1.compareTo(o2);
            }
        });

        System.out.println("Danh sách tên nhân viên sau khi sắp xếp: \n");
        for (String n : names) {
            System.out.print(n + " ");
        }
    }
}
