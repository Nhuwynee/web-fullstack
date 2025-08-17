package ex_team;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class MainManager {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Person> persons = new ArrayList<>();

//    // 3 - Như
    public static void findPerson(Scanner sc, ArrayList<Person> listMembers) {
        System.out.print("Nhập từ khóa cần tìm (họ tên hoặc email): ");
        String keyword = sc.nextLine().trim();
        String lowerKeyword = keyword.toLowerCase();

        ArrayList<Person> ketQua = new ArrayList<>();
        String kieuTimKiem = "";

        if (isValidEmail(keyword)) {
            for (Person p : listMembers) {
                if (p.getEmail().equalsIgnoreCase(keyword)) {
                    ketQua.add(p);
                }
            }
            kieuTimKiem = "email";
        } else if (isValidName(keyword)) {
            for (Person p : listMembers) {
                if (p.getName().toLowerCase().equals(lowerKeyword)) {
                    ketQua.add(p);
                }
            }
            kieuTimKiem = "tên";
        } else {
            System.out.println("Từ khóa không hợp lệ. Vui lòng nhập tên hoặc email đúng định dạng.");
            return;
        }

        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy thành viên nào với " + kieuTimKiem + " \"" + keyword + "\"");
        } else {
            System.out.println("Kết quả tìm kiếm theo " + kieuTimKiem + ":");
    // Bổ sung code MQ       printPerson(ketQua);
        }
    }


    public static boolean isValidName(String keyword) {
        return keyword.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidEmail(String keyword) {
        return keyword.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$");
    }


    public static ArrayList<Person> findByEmail(String keyword) {
        ArrayList<Person> dsTimTheoEmail = new ArrayList<>();
        for (Person p : persons)
            if(p.getEmail().trim().toLowerCase().contains(keyword)){
                dsTimTheoEmail.add(p);
            }
        return dsTimTheoEmail;
    }

    public static ArrayList<Person> findByName(String keyword) {
        ArrayList<Person> dsTimTheoTen = new ArrayList<>();
        for (Person p : persons)
            if(p.getName().trim().toLowerCase().contains(keyword)){
                dsTimTheoTen.add(p);
            }
        return dsTimTheoTen;
    }

    private static void menuUpdate() {
        System.out.println("===== Màn Hình 2 =====");
        System.out.print("Nhập vào ID muốn cập nhật thông tin: ");
        String id = sc.nextLine().trim();

        boolean isFound = false;

        for (Person person : persons) {
            if (person.getId().equalsIgnoreCase(id)) {
                isFound = true;

                System.out.println("Chọn thông tin cần cập nhật:");
                System.out.println("1. Tên");
                System.out.println("2. Tuổi");
                System.out.println("3. Email");
                System.out.print("Lựa chọn của bạn (1-3): ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên mới: ");
                        String newName = sc.nextLine().trim();
                        person.setName(newName);
                        System.out.println("Đã cập nhật tên thành công!");
                        break;
                    case 2:
                        System.out.print("Nhập tuổi mới: ");
                        try {
                            int newAge = Integer.parseInt(sc.nextLine().trim());
                            person.setAge(newAge);
                            System.out.println("Đã cập nhật tuổi thành công!");
                        } catch (NumberFormatException e) {
                            System.out.println("Tuổi không hợp lệ!");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập email mới: ");
                        String newEmail = sc.nextLine().trim();
                        person.setEmail(newEmail);
                        System.out.println("Đã cập nhật email thành công!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                        break;
                }

                break;
            }
        }

        if (!isFound) {
            System.out.println("Không tìm thấy ID muốn cập nhật!");
        }
    }

    // 5 - như
    private static void menuDelete() {
        System.out.println("===== Màn Hình 2 =====\nXÓA THÔNG TIN THÀNH VIÊN");
        System.out.print("Nhập vào ID muốn xóa: ");
        String id = sc.nextLine().trim();

        Person personToDelete = null;

        for (Person person : persons) {
            if (person.getId().equalsIgnoreCase(id)) {
                personToDelete = person;
                break;
            }
        }

        if (personToDelete == null) {
            System.out.println("Không tìm thấy ID.");
            return;
        }

        if (personToDelete instanceof Teacher) {
            for (Person p : persons) {
                if (p instanceof TeacherSupport ts) {
                    ts.getTeachers().removeIf(t -> t.getId().equalsIgnoreCase(id));
                }
            }
        }
        persons.remove(personToDelete);

        System.out.println("Đã xóa thành viên thành công.");
    }

    // 2 - Như
//    public static void findPerson2(Scanner sc, ArrayList<Person> listMembers) {
//        System.out.print("Nhập từ khóa cần tìm (họ tên hoặc email): ");
//        String keyword = sc.nextLine().trim().toLowerCase();
//
//        if (isValidEmail(keyword)) {
//            ArrayList<Person> ketQua = findByNameOrEmail(email -> keyword.equalsIgnoreCase(email.getEmail()));
//            if (ketQua.isEmpty()) {
//                System.out.println("Không tìm thấy thành viên nào với email \"" + keyword + "\"");
//            } else {
//                System.out.println("Kết quả tìm kiếm theo email:");
////                printPerson(ketQua);
//            }
//        } else if (isValidName(keyword)) {
//            ArrayList<Person> ketQua = findByNameOrEmail(name -> keyword.equalsIgnoreCase(name.getName()));
//            if (ketQua.isEmpty()) {
//                System.out.println("Không tìm thấy thành viên nào với tên \"" + keyword + "\"");
//            } else {
//                System.out.println("Kết quả tìm kiếm theo tên:");
////                printPerson(ketQua);
//            }
//        } else {
//            System.out.println("Từ khóa không hợp lệ. Vui lòng nhập tên hoặc email đúng định dạng.");
//        }
//    }
//
        //public static ArrayList<Person> findByNamOrEmail(Predicate<Person> predicate) {
        //    ArrayList<Person> result = new ArrayList<>();
        //    for (Person p : persons)
        //        if(predicate.test(p)){
        //            result.add(p);
        //        };
        //    return result;
        //}
}

