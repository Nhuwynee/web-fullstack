package ex_afternoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {

        ArrayList<String> nhanVien = new ArrayList<>();
        nhanVien.add("Nguyễn Văn An - Nam");
        nhanVien.add("Nguyễn Thị Bình - Nữ");
        nhanVien.add("Li Văn Cường - Nam");
        nhanVien.add("Linh Thị Minh - Nữ");
        nhanVien.add("Hoàng Văn Nhi - Nam");

        // task 2
        // c1
        System.out.println("\n===== Task 2: =====");
        separationEmplLastName(nhanVien);
        // c2
        // separationEmplLastName2(nhanVien);

        // task 3: Comparator
        System.out.println("\n===== Task 3: Compator =====");
        sapXepTenGiamDan(nhanVien);

        // task 4
        System.out.println("\n===== Task 4 =====");
        separationEmplGender(nhanVien);
    }

    // Task 1
    public static ArrayList<String> mergeList(ArrayList<String> kyThuat, ArrayList<String> keToan) {
        kyThuat.addAll(keToan);

        ArrayList<String> output = new ArrayList<>();

        for (String s : kyThuat) {
            if (!output.contains(s)) {
                output.add(s);
            }
        }
        return output;

    }

    // Task 2
    private static void separationEmplLastName(ArrayList<String> employee) {

        ArrayList<String> danhSachHo = new ArrayList<>();
        ArrayList<Integer> demSoLuong = new ArrayList<>();

        for (String nv : employee) {
            String[] parts = nv.split(" ");
            String ho = parts[0];

            int index = danhSachHo.indexOf(ho); // có trả về index >= 0, ko trả về -1

            if (index != -1) {
                demSoLuong.set(index, demSoLuong.get(index) + 1);
            } else {
                danhSachHo.add(ho);
                demSoLuong.add(1);
            }
        }

        System.out.println("Thống kê số lượng nhân viên theo họ:\n");
        for (int i = 0; i < danhSachHo.size(); i++) {
            System.out.println("Họ " + danhSachHo.get(i) + ": " + demSoLuong.get(i) + " nhân viên");
        }
    }

    private static void separationEmplLastName2(ArrayList<String> employee) {
        ArrayList<String> danhSachHo = new ArrayList<>();
        ArrayList<Integer> demSoLuong = new ArrayList<>();

        for (String nv : employee) {
            String[] parts = nv.split(" ");
            String ho = parts[0];

            boolean daTonTai = false;

            for (int i = 0; i < danhSachHo.size(); i++) {
                if (nv.startsWith(danhSachHo.get(i))) {
                    demSoLuong.set(i, demSoLuong.get(i) + 1);
                    daTonTai = true;
                    break;
                }
            }

            if (!daTonTai) {
                danhSachHo.add(ho);
                demSoLuong.add(1);
            }
        }

        System.out.println("Thống kê số lượng nhân viên theo họ:\n");
        for (int i = 0; i < danhSachHo.size(); i++) {
            System.out.println("Họ " + danhSachHo.get(i) + ": " + demSoLuong.get(i) + " nhân viên");
        }
    }

    // Task 3
    // Như
    private static void sapXepTenGiamDan(ArrayList<String> arr) {
        ArrayList<String> names = new ArrayList<>();

        for (String nv : arr) {
            String[] split = nv.split("-");
            String fullName = split[0].trim();
            String[] parts = fullName.split(" ");
            String name = parts[parts.length - 1];
            names.add(name);
        }

        // sx
        names.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o2.length() - o1.length();
            }
            return o1.compareTo(o2);
        });

        System.out.println("Danh sách tên nhân viên sau khi sắp xếp:");
        for (String n : names) {
            System.out.print(n + " ");
        }
    }

    // Thủy
    static void sortNameEmployeeByLength(ArrayList<String> employees) {
        System.out.print("Danh sách nhân viên ban đầu: ");
        System.out.println(employees);

        // Tách tên nhân viên trong ArrayList
        ArrayList<String> nameEmployees = new ArrayList<>();
        for (String employee : employees) {
            String[] temp = employee.split(" - ");
            String name = temp[0];
            nameEmployees.add(name);
        }
        System.out.println("Tên các nhân viên: " + nameEmployees);

        // Sắp xếp danh sách trong nameEmployees
        int n = nameEmployees.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String name1 = nameEmployees.get(j);
                String name2 = nameEmployees.get(j + 1);

                // So sánh độ dài giảm dần
                if (name1.length() < name2.length()) {
                    // Hoán đổi vị trí
                    Collections.swap(nameEmployees, j, j + 1);
                } else if (name1.length() == name2.length()) {
                    // Nếu cùng độ dài, sắp xếp theo thứ tự alphabet (tăng dần)
                    // String.compareTo() (Gía trị: name1 - name2):
                    // trả về số âm nếu name1 đứng trước name2
                    // trả về số dương nếu name1 đứng sau name2
                    // trả về 0 nếu bằng nhau

                    if (name1.compareTo(name2) > 0) {
                        // Hoán đổi vị trí nếu name1 đứng sau name2 theo alphabet
                        Collections.swap(nameEmployees, j, j + 1);
                    }
                }
            }
        }
        System.out.println("Tên nhân viên sau khi sắp xếp: " + nameEmployees);
    }

    // Task 4
    // Như
    private static void separationEmplGender(ArrayList<String> employee) {
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

    // Thủy
    static void separateEmployeesByGender(ArrayList<String> employees) {
        System.out.println("Danh sách nhân viên ban đầu: " + employees);
        Collections.sort(employees);
        ArrayList<String> nameEmployees, genderEmployees;
        nameEmployees = new ArrayList<>();
        genderEmployees = new ArrayList<>();

        for (String employee : employees) {
            String[] temp = employee.split(" - ");

            String name = temp[0];
            String gender = temp[1];

            nameEmployees.add(name);
            genderEmployees.add(gender);
        }

        // Danh sách tên nhân viên
        System.out.println("Danh sách nhân viên:");
        for (String name : nameEmployees) {
            System.out.println(name);
        }

        // Danh sách giới tính nhân viên
        System.out.println("\nDanh sách giới tính:");
        for (String gender : genderEmployees) {
            System.out.println(gender);
        }
    }


    // Task 5
    public static void countEmployeesByNameLength(ArrayList<String> employees) {
        int group1 = 0; // 3 - 5
        int group2 = 0; // 6 - 8
        int group3 = 0; // >= 8

        for (String employee : employees) {
            String name = employee.split("-")[0].trim();
            if (name.length() >= 3 && name.length() <= 5) {
                group1++;
            } else if (name.length() <= 8) {
                group2++;
            } else {
                group3++;
            }
        }

        System.out.println("1 - 5 characters: " + group1);
        System.out.println("6 - 8 characters: " + group2);
        System.out.println("Greater 8 characters: " + group3);
    }

    // Task 6
    public static List<String> findMinNames(ArrayList<String> employees, int k) {
        int len = 0;
        int bestIndex = -1;

        for (int i = 0; i < employees.size(); i++) {
            int count = 0;
            int sum = 0;

            for (int j = i; j < employees.size(); j++) {
                int lenName = employees.get(j).split("-")[0].trim().length();
                if (sum + lenName > k) {
                    break;
                }
                sum += lenName;
                count++;
            }
            if (count > len) {
                len = count;
                bestIndex = i;
            }
        }

        List<String> result = new ArrayList<>();

        if (bestIndex == -1) {
            System.out.println("Not found!");
            return result;
        }

        result = employees.subList(bestIndex, bestIndex + len);

        return result;
    }
}
