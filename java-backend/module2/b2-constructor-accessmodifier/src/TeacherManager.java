import java.util.ArrayList;
import java.util.Scanner;

public class TeacherManager {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choose;
        ArrayList<Teacher> arrTeacher = new ArrayList<>();

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Thêm giảng viên mới");
            System.out.println("2. Hiển thị danh sách tất cả giảng viên");
            System.out.println("3. Tìm giảng viên theo tên (một phần)");
            System.out.println("4. Hiển thị tổng số giảng viên");
            System.out.println("5. Tìm giảng viên có số giờ dạy nhiều nhất");
            System.out.println("6. Liệt kê giảng viên có số giờ < 20/tháng");
            System.out.println("7. Thoát");

            System.out.print("Chọn chức năng: ");
            choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1:
                    arrTeacher = addTeacher(arrTeacher);
                    System.out.println("Thêm giảng viên thành công!");
                    break;
                case 2:
                    printTeacher(arrTeacher);
                    break;
                case 3:
                    System.out.println("Nhập tên giảng viên muốn tìm: ");
                    String value = sc.nextLine();
                    System.out.println("Giảng viên: ");
                    printTeacher(findTeacherName(arrTeacher, value));
                    break;
                case 4:
                    System.out.println("Tổng số giảng viên: " + Teacher.countTeacher);
                    break;
                case 5:
                    System.out.println("Số giảng viên có số giờ giảng dạy nhiều nhất là:");
                    printTeacher(findTeacherMaxHours(arrTeacher));
                    break;
                case 6:
                    System.out.println("Số giảng viên có số giờ dạy ít hơn 20h/tháng:");
                    printTeacher(findTeacherHours(arrTeacher));
                    break;
                case 7:
                    System.out.println("Thoát...");
                    break;
                default:
                    System.out.println("Chọn sai, vui lòng chọn lại!");
            }

        } while (choose != 7);
    }

    private static ArrayList<Teacher> addTeacher(ArrayList<Teacher> ts) {
        System.out.println("Nhập thông tin giảng viên: ");
        Teacher t = new Teacher();
        String name;
        boolean exist;

        do {
            System.out.print("Nhập tên giảng viên: ");
            name = sc.nextLine().trim();
            exist = false;

            if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("Tên chỉ được chứa chữ cái và khoảng trắng.");
                exist = true;
                continue;
            }

            for (Teacher teacher : ts) {
                if (teacher.getName().equalsIgnoreCase(name)) {
                    System.out.println("Tên giảng viên đã tồn tại, vui lòng nhập lại.");
                    exist = true;
                    break;
                }
            }
        } while (exist);
        t.setName(name);

        int age;
        do {
            try {
                System.out.print("Nhập tuổi: ");
                age = Integer.parseInt(sc.nextLine());
                if (age <= 0) {
                    System.out.println("Tuổi phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tuổi không hợp lệ. Nhập số nguyên dương.");
                age = -1;
            }
        } while (age <= 0);

        t.setAge(age);

        System.out.print("Nhập môn giảng dạy: ");
        t.setSubject(sc.nextLine());

        double hours;
        do {
            try {
                System.out.print("Nhập số giờ giảng dạy: ");
                hours = Double.parseDouble(sc.nextLine());
                if (hours < 0) {
                    System.out.println("Số giờ không được âm.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số giờ không hợp lệ. Nhập số không âm.");
                hours = -1;
            }
        } while (hours < 0);

        t.setTeachHours(hours);

        ts.add(t);
        return ts;
    }

    private static void printTeacher(ArrayList<Teacher> ts) {
        if (ts == null || ts.isEmpty()) {
            System.out.println("Không có giảng viên nào.");
        } else {
            for (Teacher t : ts) {
                System.out.println(t);
            }
        }
    }

    private static ArrayList<Teacher> findTeacherMaxHours(ArrayList<Teacher> ts) {
        ArrayList<Teacher> result = new ArrayList<>();

        if (ts.isEmpty()) {
            System.out.println("Chưa có giảng viên.");
            return null;
        }
        Teacher max = ts.get(0);
        for (Teacher t : ts) {
            if (t.getTeachHours() > max.getTeachHours()) {
                max = t;
            }
        }
        result.add(max);
        return result;
    }

    private static ArrayList<Teacher> findTeacherName(ArrayList<Teacher> ts, String value) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Teacher t : ts) {
            if (t.getName().toLowerCase().contains(value)) {
                result.add(t);
            }
        }
        return result;
    }

    private static ArrayList<Teacher> findTeacherHours(ArrayList<Teacher> ts) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Teacher t : ts) {
            if (t.getTeachHours() < 20) {
                result.add(t);
            }
        }
        return result;
    }
}