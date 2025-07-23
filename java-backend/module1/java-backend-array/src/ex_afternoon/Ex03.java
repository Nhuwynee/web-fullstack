package ex_afternoon;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("1. Nhập số nhân viên: ");
        int n = Integer.parseInt(sc.nextLine());

        int[] id = new int[n];
        String[] names = new String[n];
        byte[] ages = new byte[n];
        String[] genders = new String[n];
        double[] salaries = new double[n];
        float[] gpas = new float[n];

        for (int i = 0; i < n; i++) {
            System.out.println("3. Nhập thông tin nhân viên thứ " + (i + 1));
            System.out.println("ID Nhân viên: " + (i + 1));
            id[i] = i + 1;

            System.out.print("Nhập tên:");
            names[i] = sc.nextLine();

            System.out.print("Nhập tuổi: ");
            ages[i] = Byte.parseByte(sc.nextLine());

            System.out.print("Nhập giới tính (Nam/Nữ): ");
            genders[i] = sc.nextLine();

            System.out.print("Nhập lương: ");
            salaries[i] = Double.parseDouble(sc.nextLine());

            System.out.print("Nhập điểm GPA: ");
            gpas[i] = Float.parseFloat(sc.nextLine());
        }

        System.out.print("4. Danh sách nhân viên");
        inDanhSachNhanVien(id, names, ages, genders, salaries, gpas);

        System.out.print("5. Nhập mã sinh viên muốn tìm: ");
        int maTim = Integer.parseInt(sc.nextLine());

        if (maTim < id[0] || maTim > id[id.length - 1]) {
            System.out.println("Vui lòng nhập mã nhân viên hợp lệ");
        } else {
            if (timViTriNhanVienTheoMaNV(id, maTim) == -1){
                System.out.println("Không tìm thấy nhân viên nào");
            } else {
                inNhanVien(names, ages, genders, salaries, gpas, timViTriNhanVienTheoMaNV(id, maTim));
            }
        }

        System.out.println("\n 6. Danh sách nhân viên sắp xếp theo tuổi tăng dần");
        sapXepNhanVienTheoTuoiTangDan(id, names, ages, genders, salaries, gpas);

    }

    private static void inDanhSachNhanVien(int[] id, String[] names, byte[] ages, String[] genders, double[] salaries, float[] gpas) {
        System.out.println("\n===== DANH SÁCH NHÂN VIÊN =====");

        for (int i = 0; i < id.length; i++) {
            System.out.println("Nhân viên " + (i + 1) + ": ID: " + id[i] + ", Name: " +
                    names[i] + ", Tuổi: " + ages[i] +
                    ", Giới tính: " + genders[i] +
                    ", Lương: " + String.format("%,.0f", salaries[i]) + " VND" +
                    ", GPA: " + gpas[i]);
        }
    }

    private static void inNhanVien(String[] names, byte[] ages, String[] genders, double[] salaries, float[] gpas, int i) {
        System.out.println("Nhân viên " + (i + 1) + ": " +
                names[i] + ", ID: " + (i + 1) + ", Tuổi: " + ages[i] +
                ", Giới tính: " + genders[i] +
                ", Lương: " + String.format("%,.0f", salaries[i]) + " VND" +
                ", GPA: " + gpas[i]);
    }

    private static int timViTriNhanVienTheoMaNV (int[] id, int value) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void sapXepNhanVienTheoTuoiTangDan (int[] id, String[] names, byte[] ages, String[] genders, double[] salaries, float[] gpas) {
            for (int i = 0; i < id.length; i++) {
                for (int j = 0; j < id.length - 1; j++) {
                if (ages[j] > ages[j + 1]) {
                    byte tempage= ages[j];
                    ages[j] = ages[j + 1];
                    ages[j + 1] = tempage;

                    // sort id
                    int tempid = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = tempid;

                    // sort name
                    String tempname = names[j];
                    names[j] = names[j + 1];
                    names[j+1] = tempname;

                    //sort gender
                    String tempgender = genders[j];
                    genders[j] = genders[j + 1];
                    genders[j + 1] = tempgender;

                    // salary
                    double tempsal = salaries[j];
                    salaries[j] = salaries[j + 1];
                    salaries[j + 1] = tempsal;

                    // gpa
                    float tempgpas = gpas[j];
                    gpas[j] = gpas[j + 1];
                    gpas[j + 1] = tempgpas;
                }
            }
        }
        inDanhSachNhanVien(id, names, ages, genders, salaries, gpas);
    }
}
