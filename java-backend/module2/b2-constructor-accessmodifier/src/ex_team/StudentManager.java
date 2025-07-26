package ex_team;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println(">> LỰA CHỌN CHỨC NĂNG <<");
        System.out.println("++ ----------------------------------------- ++");
        System.out.println("| 1. Thêm sinh viên mới                       |");
        System.out.println("| 2. Hiện danh sách tất cả sinh viên          |");
        System.out.println("| 3. Tìm sinh viên theo tên                   |");
        System.out.println("| 4. Hiện thị tổng số sinh viên               |");
        System.out.println("| 5. Sinh viên có điểm cao nhất               |");
        System.out.println("| 6. Sinh viên có điểm dưới trung bình ( < 5) |");
        System.out.println("| 7. Exit                                     |");
        System.out.println("++ ----------------------------------------- ++");
    }

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:

                    System.out.print("Nhập họ tên: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập tuổi: ");
                    int age = Integer.parseInt(sc.nextLine());

                    System.out.print("Nhập điểm trung bình: ");
                    double score = Double.parseDouble(sc.nextLine());

                    students.add(new Student(name, age, score));
                    System.out.println("=> Đã thêm sinh viên thành công!");
                    break;

                case 2:
                    if (isEmptyList()) break;

                    System.out.println(">> Danh sách tất cả sinh viên:");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    if (isEmptyList()) break;

                    System.out.print("1. Tìm chính xác theo tên\n2. Tìm theo từ khóa\nChọn kiểu tìm: ");
                    int searchType = Integer.parseInt(sc.nextLine());
                    if (searchType == 1) {
                        findByName();
                    } else {
                        findByText();
                    }
                    break;

                case 4:
                    System.out.println(">> Tổng số sinh viên hiện có: " + students.size());
                    break;

                case 5:
                    ArrayList<Student> top = findStudentMaxScore();
                    if (!top.isEmpty()) {
                        System.out.println(">> Sinh viên có điểm cao nhất:");
                        for (Student s : top) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 6:
                    ArrayList<Student> weak = findStudentAvgScore();
                    if (!weak.isEmpty()) {
                        System.out.println(">> Danh sách sinh viên có điểm < 5:");
                        for (Student s : weak) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Chọn sai, vui lòng chọn lại!");
                    break;
            }
        }
    }


    public static ArrayList<Student> findByName() {
        ArrayList<Student> list = new ArrayList<>();
        System.out.print("Nhập tên cần tìm: ");
        String x = sc.nextLine().trim();
        boolean found = false;

        for (Student t : students) {
            if (t.getName().trim().equalsIgnoreCase(x)) {
                System.out.println("=> Đã tìm thấy:");
                System.out.println(t);
                list.add(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sinh viên nào tên là " + x);
        }

        return list;
    }

    public static ArrayList<Student> findByText() {
        ArrayList<Student> list = new ArrayList<>();
        System.out.print("Nhập từ khóa cần tìm trong tên: ");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found2 = false;

        for (Student t : students) {
            String[] words = t.getName().toLowerCase().split(" ");
            for (String word : words) {
                if (word.startsWith(keyword)) {
                    System.out.println("=> Tìm thấy sinh viên:");
                    System.out.println(t);
                    found2 = true;
                    break;
                }
            }
        }

        if (!found2) {
            System.out.println("Không có sinh viên nào chứa từ '" + keyword + "' trong tên.");
        }
        return list;
    }


    private static boolean isEmptyList() {
        if (students.isEmpty()) {
            System.out.println("Chưa có học viên.");
            return true;
        }
        return false;
    }

    private static ArrayList<Student> findStudentMaxScore() {
        ArrayList<Student> result = new ArrayList<>();
        if (isEmptyList()) {
            return result;
        }

        double maxScore = students.get(0).getAvgScore();
        for (Student s : students) {
            if (s.getAvgScore() > maxScore) {
                maxScore = s.getAvgScore();
            }
        }

        for (Student s : students) {
            if (s.getAvgScore() == maxScore) {
                result.add(s);
            }
        }
        return result;
    }

    private static ArrayList<Student> findStudentAvgScore() {
        ArrayList<Student> result = new ArrayList<>();
        if (isEmptyList()) {
            return result;
        }

        for (Student s : students) {
            if (s.getAvgScore() < 5) {
                result.add(s);
            }
        }
        return result;
    }

}