package BaiTapChieu2;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Person2> Person2s = new ArrayList<>();


    private static String getIdentityId(Class<?> clazz) {
        int max = 0;
        String prefix = "";

        if (clazz == StudentBE.class) {
            prefix = "SBE";
        } else if (clazz == StudentFT.class) {
            prefix = "SFT";
        } else if (clazz == Teacher.class) {
            prefix = "T";
        }

        for (Person2 p : Person2s) {
            if (clazz.isInstance(p)) {
                String idStr = p.getId().substring(prefix.length());
                int id = Integer.parseInt(idStr);
                if (id > max) {
                    max = id;
                }
            }
        }

        int numDigits = 10 - prefix.length();
        return String.format("%s%0" + numDigits + "d", prefix, max + 1);

    }


    public static void main(String[] args) {
        int choose;
        while (true) {
            do {
                System.out.println("\n===== Màn Hình =====\nDANH SÁCH THÀNH VIÊN");
                System.out.println("1. Thêm thành viên (học viên Backend / Fullstack hoặc giảng viên.)");
                System.out.println("2. Hiển thị danh sách thành viên");
                System.out.println("3. Tìm kiếm thành viên theo tên hoặc email");
                System.out.println("4. Thống kê:");
                System.out.println("    Số học viên Backend");
                System.out.println("    Số học viên Fullstack");
                System.out.println("    Số học viên Fullstack có ≥ 3 dự án");
                System.out.println("    Số giảng viên có hơn 30 giờ dạy");
                System.out.println("5. Hiển thị học viên giỏi nhất của từng loại");
                System.out.println("6. Tính tổng lương của toàn bộ giảng viên");
                System.out.println("7. Thoát...");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1 -> addPerson2();

                    case 2 -> printPerson2(Person2s);

                    case 3 -> findPeson(sc, Person2s);
                    case 4 -> showStudentStatistics();
                    case 5 -> showTopStudent(Person2s);
                    case 6 -> total();
                    case 7 -> {
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 7);
        }
    }

    private static void addPerson2() {
        System.out.println("Bạn muốn thêm thành viên nào?");
        System.out.println("1. Học viên Backend");
        System.out.println("2. Học viên Fullstack");
        System.out.println("3. Giảng viên");
        int choose;
        do {
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1 -> add(StudentBE.class);
                case 2 -> add(StudentFT.class);
                case 3 -> add(Teacher.class);
                default -> System.out.println("Bạn đã nhập sai ! Vui lòng chọn 1 - 3.");
            }
        } while (choose < 1 || choose > 3);
    }

    //1 them moi
    private static void add(Class<?> clazz) {
        String id = getIdentityId(clazz);
        System.out.println("Thêm thành viên với id #" + id);

        Person2 Person2 = null;

        if (clazz == StudentBE.class) {
            Person2 = new StudentBE();
        } else if (clazz == StudentFT.class) {
            Person2 = new StudentFT();
        } else if (clazz == Teacher.class) {
            Person2 = new Teacher();
        }

        if (Person2 != null) {
            Person2.setId(id);
            Person2.input(sc);
            Person2s.add(Person2);
            System.out.println("Thêm thành công !!!");
        } else {
            System.out.println("Không xác định loại thành viên.");
        }
    }

    //2 in ra
    private static void printPerson2(ArrayList<Person2> Person2s) {
        boolean hasTeacher = false;
        boolean hasStudentFT = false;
        boolean hasStudentBE = false;

        // Kiểm tra trước
        for (Person2 p : Person2s) {
            if (p instanceof Teacher) hasTeacher = true;
            else if (p instanceof StudentFT) hasStudentFT = true;
            else if (p instanceof StudentBE) hasStudentBE = true;
        }

        if (hasTeacher) {
            System.out.println("============================================ Danh sách giảng viên ===========================================");
            new Teacher().printHeader();
            for (Person2 p : Person2s) {
                if (p instanceof Teacher) {
                    System.out.println(p);
                }
            }
            System.out.println("==================================================================================================");
        }

        if (hasStudentFT) {
            System.out.println("\n===================================== Danh sách học viên khóa FullStack =====================================");
            new StudentFT().printHeaderSFT();
            for (Person2 p : Person2s) {
                if (p instanceof StudentFT) {
                    System.out.println(p);
                }
            }
            System.out.println("==================================================================================================");
        }

        if (hasStudentBE) {
            System.out.println("\n===================================== Danh sách học viên khóa BackEnd =====================================");
            new StudentBE().printHeaderSBE();
            for (Person2 p : Person2s) {
                if (p instanceof StudentBE) {
                    System.out.println(p);
                }
            }
            System.out.println("==================================================================================================");
        }

        if (!hasTeacher && !hasStudentFT && !hasStudentBE) {
            System.out.println("❌ Không có dữ liệu để hiển thị.");
        }
    }

    //3
    public static void findPeson(Scanner sc, ArrayList<Person2> listMembers) {
        System.out.print("Nhập từ khóa cần tìm (họ tên hoặc email): ");
        String keyword = sc.nextLine().trim().toLowerCase();

        // Kiểm tra là email hay tên
        if (isValidEmail(keyword)) {
            ArrayList<Person2> ketQua = findByEmail(keyword, listMembers);
            if (ketQua.isEmpty()) {
                System.out.println("❌ Không tìm thấy thành viên nào với email \"" + keyword + "\"");
            } else {
                System.out.println("✅ Kết quả tìm kiếm theo email:");

                printPerson2(ketQua);
            }
        } else if (isValidName(keyword)) {
            ArrayList<Person2> ketQua = findByName(keyword, listMembers);
            if (ketQua.isEmpty()) {
                System.out.println("❌ Không tìm thấy thành viên nào với tên \"" + keyword + "\"");
            } else {
                System.out.println("✅ Kết quả tìm kiếm theo tên:");
                printPerson2(ketQua);
            }
        } else {
            System.out.println("❌ Từ khóa không hợp lệ. Vui lòng nhập tên hoặc email đúng định dạng.");
        }
    }

    // Kiểm tra keyword có phải là tên (chỉ chứa chữ và khoảng trắng)
    public static boolean isValidName(String keyword) {
        return keyword.matches("[a-zA-Z\\s]+");
    }

    // Kiểm tra keyword có phải là email
    public static boolean isValidEmail(String keyword) {
        return keyword.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$");
    }

    public static ArrayList<Person2> findByName(String keyword, ArrayList<Person2> list) {
        ArrayList<Person2> dsTimTheoTen = new ArrayList<>();
        for (Person2 p : Person2s)
            if (p.getName().trim().toLowerCase().contains(keyword)) {
                dsTimTheoTen.add(p);
            }
        ;
        return dsTimTheoTen;
    }

    public static ArrayList<Person2> findByEmail(String keyword, ArrayList<Person2> list) {
        ArrayList<Person2> dsTimTheoEmail = new ArrayList<>();
        for (Person2 p : Person2s)
            if (p.getEmail().trim().toLowerCase().contains(keyword)) {
                dsTimTheoEmail.add(p);
            }
        ;
        return dsTimTheoEmail;
    }


    //4
    private static void showStudentStatistics() {
        int countStudentBE = 0;
        int countStudentFT = 0;
        int countFTOver3Projects = 0;
        int countTeacherOver30h = 0;

        for (Person2 p : Person2s) {
            if (p instanceof StudentBE) {
                countStudentBE++;
            } else if (p instanceof StudentFT) {
                StudentFT ft = (StudentFT) p;
                countStudentFT++;
                if (ft.soDuAnThamGia >= 3) {
                    countFTOver3Projects++;
                }
            } else if (p instanceof Teacher) {
                Teacher t = (Teacher) p;
                if (t.getSoGioDay() > 30) {
                    countTeacherOver30h++;
                }
            }
        }

        System.out.println("Số học viên Backend: " + countStudentBE);
        System.out.println("Số học viên Fullstack: " + countStudentFT);
        System.out.println("Số học viên Fullstack có từ 3 dự án trở lên: " + countFTOver3Projects);
        System.out.println("Số giảng viên có hơn 30 giờ dạy: " + countTeacherOver30h);
    }

    //5
    public static void showTopStudent(ArrayList<Person2> list) {
        StudentBE gioiNhatBackend = null;
        StudentFT gioiNhatFullstack = null;

        for (Person2 p : list) {
            if (p instanceof StudentBE) {
                StudentBE hv = (StudentBE) p;
                if (gioiNhatBackend == null || hv.getDiemTrungBinh() > gioiNhatBackend.getDiemTrungBinh()) {
                    gioiNhatBackend = hv;
                }
            } else if (p instanceof StudentFT) {
                StudentFT hv = (StudentFT) p;

                if (gioiNhatFullstack == null || hv.getDiemTrungBinh() / hv.getSoDuAnThamGia() > gioiNhatFullstack.getDiemTrungBinh() / gioiNhatFullstack.getSoDuAnThamGia()) {
                    gioiNhatFullstack = hv;
                }
            }
        }

        System.out.println("\n🏆 Học viên Backend giỏi nhất:");
        if (gioiNhatBackend != null) {
            new StudentBE().printHeaderSBE();
            System.out.println(gioiNhatBackend);
        } else {
            System.out.println("Không có học viên Backend.");
        }

        System.out.println("\n🏆 Học viên Fullstack giỏi nhất:");
        if (gioiNhatFullstack != null) {
            new StudentFT().printHeaderSFT();
            System.out.println(gioiNhatFullstack);
        } else {
            System.out.println("Không có học viên Fullstack.");
        }
    }

    //6
    private static void total() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Person2 p : Person2s) {
            if (p instanceof Teacher) {
                sum = sum.add(((Teacher) p).luong());
            }
        }
        System.out.println("Tổng lương của toàn bộ giảng viên là: " + sum.setScale(2, RoundingMode.HALF_UP) + " VND");
    }


    //id tang dan
    //id teacher
//    private static String getIdTeacher() {
//        int max = 0;
//
//        for (Person2 p : Person2s) {
//            if (p instanceof Teacher) {
//                String id = p.getId();//vd T006
//                String number = id.substring(1);//006
//                int num = Integer.parseInt(number);//6
//                if (num > max) max = num;//
//
//            }
//        }
//
//        return String.format("T%07d", max + 1); // VD: T0000006
//    }
//
//    //id StudentBE
//    private static String getIdStudentBE() {
//        int max = 0;
//
//        for (Person2 p : Person2s) {
//            if (p instanceof StudentBE) {
//                String id = p.getId();
//                String number = id.substring(3);
//                int num = Integer.parseInt(number);
//                if (num > max) max = num;
//            }
//        }
//
//        return String.format("SBE%05d", max + 1); // VD: T0000006
//    }//id StudentFT
//
//    private static String getIdStudentFT() {
//        int max = 0;
//
//        for (Person2 p : Person2s) {
//            if (p instanceof StudentFT) {
//                String id = p.getId();
//                String number = id.substring(3);
//                int num = Integer.parseInt(number);
//                if (num > max) max = num;
//            }
//        }
//
//        return String.format("SFT%05d", max + 1); // VD: T0000006
//    }

}
