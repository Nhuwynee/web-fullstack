package ex_afternoon;

import ex_morning.ManagementEmployee;

import java.util.ArrayList;
import java.util.Scanner;

public class MainManager {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Person> persons = new ArrayList<>();


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

        for (Person p : persons) {
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
                    case 1:
                        addPerson();
                        break;
                    case 2:
                        // printPerson();
                        break;
                    case 3:
                        // logic show danh sách cả 2 loại nhân viên
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 7);
        }
    }

    private static void addPerson() {
        System.out.println("Bạn muốn thêm thành viên nào?");
        System.out.println("1. Học viên Backend");
        System.out.println("2. Học viên Fullstack");
        System.out.println("3. Giảng viên");
        int choose;
        do {
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    add(StudentBE.class);
                    break;
                case 2:
                    add(StudentFT.class);
                    break;
                case 3:
                    add(Teacher.class);
                    break;
                default:
                    System.out.println("Bạn đã nhập sai ! Vui lòng chọn 1 - 3.");
                    break;
            }
        } while (choose < 1 || choose > 3);
    }

    private static void add(Class<?> clazz) {
        String id = getIdentityId(clazz);
        System.out.println("Thêm thành viên với id #" + id);

        Person person = null;

        if (clazz == StudentBE.class) {
            person = new StudentBE();
        } else if (clazz == StudentFT.class) {
            person = new StudentFT();
        } else if (clazz == Teacher.class) {
            person = new Teacher();
        }

        if (person != null) {
            person.setId(id);
            person.input(sc);
            persons.add(person);
            System.out.println("Thêm thành công !!!");
        } else {
            System.out.println("Không xác định loại thành viên.");
        }
    }


}

//    private static String getIdStudentBE2() {
//        int max = 0;
//
//        for (Person p : persons) {
//            if (p instanceof StudentBE) {
//                String idStr = p.getId().substring(3);
//                int id = Integer.parseInt(idStr);
//                if (id > max) {
//                    max = id;
//                }
//            }
//        }
//
//        if (max == 0) {
//            return "SBE00001";
//        }
//
//        return String.format("SBE%05d", max + 1);
//    }