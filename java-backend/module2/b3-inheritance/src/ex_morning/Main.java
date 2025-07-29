package ex_morning;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    /*
    Chương trình quản lý nhân viên của 1 công ty
        + Nhân viên quản lý:
            - Mã (QLYYY, với Y là 1 số => phải tự động tăng)
            - Họ tên
            - Ngày sinh
            - Địa chỉ
            - Lương cơ bản
            - Hệ số lương
        + Nhân viên sản xuất:
            - Mã (QLYYY, với Y là 1 số => phải tự động tăng)
            - Họ tên
            - Ngày sinh
            - Địa chỉ
            - Số sản phẩm



    CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN
        1. Thêm mới
            1.1. Nhân viên quản lý
            1.2. Nhân viên sản xuất
            1.3. Trở về menu chính
        2. Cập nhật thông tin
        3. Xem danh sách
            3.1. Nhân viên quản lý
            3.2. Nhân viên sản xuất
            3.3. Tất cả nhân viên
            3.4. Trở về menu chính
        4. Thoát
     */


    static Scanner sc = new Scanner(System.in);

    static ArrayList<ManagementEmployee> managementEmployees = new ArrayList<>();
    static ArrayList<ProductionEmployee> productionEmployees = new ArrayList<>();

    public static void main(String[] args) {
        int choose;

        /// Tại sao dùng while (true) & làm sao dừng được nó
        while (true) {
            do {
                System.out.println("===== Màn Hình Menu Chính =====\nCHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN");
                System.out.println("1. Thêm mới");
                System.out.println("2. Cập nhật thông tin");
                System.out.println("3. Xem danh sách");
                System.out.println("4. Thoát");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1:
                        menuAddNew();
                        break;
                    case 2:
                        menuUpdate();
                        break;
                    case 3:
                        menuShowList();
                        break;
                    case 4:
                        //
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 4);
        }
    }


    private static void menuAddNew() {
        int choose;
        while (true) {
            do {
                System.out.println("===== Màn Hình 1 =====\nTHÊM MỚI NHÂN VIÊN");
                System.out.println("1. Nhân viên quản lý");
                System.out.println("2. Nhân viên sản xuất");
                System.out.println("3. Trở về menu chính");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1:
                        ManagementEmployee managementEmployee = new ManagementEmployee();
                        managementEmployee.input();
                        managementEmployee.setId(getIdManagement());
                        managementEmployees.add(managementEmployee);
                        System.out.println("Thêm nhân viên quản lý mới thành công");
                        break;
                    case 2:
                        // logic thêm mới nhân viên sản xuất
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 3);
        }
    }

    private static String getIdManagement() {
        if (managementEmployees.isEmpty()) {
            return "QL001";
        }


        int max = Integer.parseInt(managementEmployees.get(0).getId().substring(2));

        for (ManagementEmployee managementEmployee : managementEmployees) {
            int id = Integer.parseInt(managementEmployee.getId().substring(2));
            if (max < id) {
                max = id;
            }
        }

        return String.format("QL%3d", max + 1).replace(" ", "0");
    }

    private static void menuUpdate() {
        System.out.println("===== Màn Hình 2 =====\nCẬP NHẬT THÔNG TIN NHÂN VIÊN");
        System.out.println("Nhập vào ID muốn cập nhật thông tin");
        String id = sc.nextLine();

        if (id.startsWith("QL")) {
            boolean isExistEmployee = false;
            for (int i = 0; i < managementEmployees.size(); i++) {
                if (managementEmployees.get(i).getId().equals(id)) {
                    isExistEmployee = true;
                    managementEmployees.get(i).input();
                    System.out.println("Cập nhật thành công");
                    break;
                }

                /// Có thể sử dụng for-each để sửa thông tin bên trong đối tượng
                /// Nhưng for-each KHÔNG có tác dụng thay đổi lại đối tượng gốc
                /// Tham khảo ví dụ line 210 để hiểu rõ hơn
//                for (ManagementEmployee employee : managementEmployees) {
//                    if (employee.getId().equals(id)) {
//                        employee = new ManagementEmployee("QL000", "test", "1/1/2000", "test", 1000, 10);
//                        managementEmployees.add(employee);
//                        break;
//                    }
//                }
//                if (managementEmployees.get(i).getId().equals(id)) {
//                    ManagementEmployee updatedEmp = new ManagementEmployee("QL000", "test", "1/1/2000", "test", 1000, 10);
//                    managementEmployees.set(i, updatedEmp);
//                    break;
//                }
            }

            if (!isExistEmployee) {
                System.out.println("Không tìm thấy ID muốn cập nhật!");
            }
        } else if (id.startsWith("SX")) {
            // logic cập nhật thông tin cho nhân viên sản xuất
        } else {
            System.out.println("ID không hợp lệ!");
        }
    }

    private static void menuShowList() {
        int choose;
        while (true) {
            do {
                System.out.println("===== Màn Hình 3 =====\nDANH SÁCH NHÂN VIÊN");
                System.out.println("1. Nhân viên quản lý");
                System.out.println("2. Nhân viên sản xuất");
                System.out.println("3. Tất cả nhân viên");
                System.out.println("4. Trở về menu chính");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1:
                        for (int i = 0; i < managementEmployees.size(); i++) {
                            System.out.println("Thông tin nhân viên thứ " + (i + 1));
                            managementEmployees.get(i).output();
                        }
                        break;
                    case 2:
                        // logic show danh sách nhân viên sản xuất
                        break;
                    case 3:
                        // logic show danh sách cả 2 loại nhân viên
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 4);
        }
    }


    /// for-each KHÔNG có tác dụng thay đổi giá trị phần từ của mảng gốc
//    public static void main(String[] args) {
//        int[] arr = {1, 2, 3};
//
////        for (int i = 0; i < arr.length; i++) {
////            arr[i] = arr[i] * 2;
////        }
//
//        for (int num : arr) {
//            num = num * 2;
//        }
//
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }
//    }
}
