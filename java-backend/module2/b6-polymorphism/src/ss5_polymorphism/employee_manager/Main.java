package ss5_polymorphism.employee_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /// Đặt break point debug ở đầu các method:
    /// menuAddNew() -> khi add nhân viên quản lý & add nhân viên sản xuất
    /// menuShowList() -> case 1 & case 3

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
        4. Sắp xếp theo lương
            4.1. Nhân viên quản lý
                - Tăng dần
                - Giảm dần
            4.2. Nhân viên sản xuất
                - Tăng dần
                - Giảm dần
            4.3. Tất cả nhân viên
                - Tăng dần
                - Giảm dần
            4.4. Trở về menu chính
        5. Thoát
     */


    static Scanner sc = new Scanner(System.in);

//    static ArrayList<ManagementEmployee> managementEmployees = new ArrayList<>();
//    static ArrayList<ProductionEmployee> productionEmployees = new ArrayList<>();

    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        int choose;

        /// Tại sao dùng while (true) & làm sao dừng được nó
        while (true) {
            do {
                System.out.println("===== Màn Hình Menu Chính =====\nCHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN");
                System.out.println("1. Thêm mới");
                System.out.println("2. Cập nhật thông tin");
                System.out.println("3. Xem danh sách");
                System.out.println("4. Sắp xếp theo lương");
                System.out.println("5. Thoát");

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
                        menuSortBySalary();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                        break;
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
                        managementEmployee.setId(getIdentityId("QL", ManagementEmployee.class));
                        employees.add(managementEmployee);
                        System.out.println("Thêm nhân viên quản lý mới thành công");
                        break;
                    case 2:
                        ProductionEmployee productionEmployee = new ProductionEmployee();
                        productionEmployee.input();
                        productionEmployee.setId(getIdentityId("SX", ProductionEmployee.class));
                        employees.add(productionEmployee);
                        System.out.println("Thêm nhân viên sản xuất mới thành công");
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 3);
        }
    }

    private static <T extends Employee> ArrayList<T> filterEmployee(Class<T> type) {
        ArrayList<T> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (type.isInstance(employee)) {
                result.add(type.cast(employee));
            }
        }
        return result;
    }

    private static String getIdentityId(String prefix, Class<?> clazz) {
        int max = 0;

        for (Employee e : employees) {
            if (clazz.isInstance(e)) {
                String idStr = e.getId().substring(prefix.length());
                int id = Integer.parseInt(idStr);
                if (id > max) {
                    max = id;
                }
            }
        }
        return String.format("%s%03d", prefix, max + 1);
    }

    private static void menuUpdate() {
        System.out.println("===== Màn Hình 2 =====\nCẬP NHẬT THÔNG TIN NHÂN VIÊN");
        System.out.print("Nhập vào ID muốn cập nhật thông tin: ");
        String id = sc.nextLine().trim();

        boolean isFound = false;

        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                isFound = true;

                    employee.input();
                    System.out.println("Cập nhật thành công!");

                break;
            }
        }

        if (!isFound) {
            System.out.println("Không tìm thấy ID muốn cập nhật!");
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
                        int count = 1;
                        for (Employee employee : employees) {
                            if (employee instanceof ManagementEmployee) {
                                System.out.println("Thông tin nhân viên thứ " + count++);
                                employee.output();
                            }
                        }
                        break;
                    case 2:
                        int counte = 1;
                        for (Employee employee : employees) {
                            if (employee instanceof ManagementEmployee) {
                                System.out.println("Thông tin nhân viên thứ " + counte++);
                                employee.output();
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < employees.size(); i++) {
                            System.out.println("Thông tin nhân viên thứ " + (i + 1));
                            employees.get(i).output();
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 4);
        }
    }

    private static void menuSortBySalary() {
        int choose;
        while (true) {
            do {
                System.out.println("===== Màn Hình 4 =====\nSẮP XẾP THEO LƯƠNG");
                System.out.println("1. Nhân viên quản lý");
                System.out.println("2. Nhân viên sản xuất");
                System.out.println("3. Tất cả nhân viên");
                System.out.println("4. Trở về menu chính");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1:
                        ArrayList<ManagementEmployee> managementEmployees = filterEmployee(ManagementEmployee.class);
                        sortEmployees(managementEmployees);
                        break;
                    case 2:
                        ArrayList<ProductionEmployee> productionEmployees = filterEmployee(ProductionEmployee.class);
                        sortEmployees(productionEmployees);
                        break;
                    case 3:
                        sortAllEmployees();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 4);
        }
    }

    private static void sortEmployees(List<? extends Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("Danh sách rỗng! Không có gì để sắp xếp.");
            return;
        }

        System.out.println("1. Tăng dần theo lương");
        System.out.println("2. Giảm dần theo lương");
        System.out.print("Chọn cách sắp xếp: ");
        int choose;
        try {
            choose = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ! Phải là số.");
            return;
        }

        boolean ascending;
        if (choose == 1) {
            ascending = true;
        } else if (choose == 2) {
            ascending = false;
        } else {
            System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
            return;
        }

        sortByFor(employees, ascending);

        System.out.println("----- Danh sách sau khi sắp xếp theo lương -----");
        int count = 1;
        for (Employee employee : employees) {
            System.out.println("Thông tin nhân viên thứ " + count++);
            employee.output();
            System.out.printf("Lương: %.2f\n\n", employee.getSalary());
        }
    }

    private static void sortAllEmployees() {
        System.out.println("1. Tăng dần theo lương");
        System.out.println("2. Giảm dần theo lương");
        System.out.print("Chọn cách sắp xếp: ");
        int choose = Integer.parseInt(sc.nextLine());

        if (choose == 1) {
            sortByFor(employees, true);
//            employees.sort((a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        } else if (choose == 2) {
            sortByFor(employees, false);
//            employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        } else {
            System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
            return;
        }

        System.out.println("----- Danh sách sau khi sắp xếp theo lương -----");
        int count = 1;
        for (Employee employee : employees) {
            System.out.println("Thông tin nhân viên thứ " + count++);
            employee.output();
            System.out.println("Lương: " + employee.getSalary());
            System.out.println();
        }
    }

    private static <T extends Employee> void sortByFor(List<T> list, boolean ascending) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double salaryI = list.get(i).getSalary();
                double salaryJ = list.get(j).getSalary();

                boolean needSwap = ascending
                        ? salaryI > salaryJ
                        : salaryI < salaryJ;

                if (needSwap) {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
