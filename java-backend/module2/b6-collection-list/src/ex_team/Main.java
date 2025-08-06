package ex_team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Person> persons = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static LinkedList<Schedule> schedules = new LinkedList<>();

    private static void initializeSampleData() {
        // Create Lecturers first (for Teaching Assistants to reference)
        Lecturer lecturer1 = new Lecturer("L001", "Dr. John Smith", 45, "john.smith@university.edu",
                40, "Computer Science");
        Lecturer lecturer2 = new Lecturer("L002", "Prof. Sarah Johnson", 38, "sarah.johnson@university.edu",
                35, "Software Engineering");
        Lecturer lecturer3 = new Lecturer("L003", "Dr. Michael Brown", 42, "michael.brown@university.edu",
                38, "Database Systems");

        // Add Backend Students
        persons.add(new StudentBE("BE001", "Alice Chen", 22, "alice.chen@student.edu",
                8.7, 45, "Java"));
        persons.add(new StudentBE("BE002", "Bob Wilson", 23, "bob.wilson@student.edu",
                7.2, 42, "Python"));
        persons.add(new StudentBE("BE003", "Charlie Davis", 21, "charlie.davis@student.edu",
                6.8, 38, "C#"));
        persons.add(new StudentBE("BE004", "Diana Lee", 24, "diana.lee@student.edu",
                9.1, 48, "Java"));

        // Add Fullstack Students
        persons.add(new StudentFS("FS001", "Eva Martinez", 23, "eva.martinez@student.edu",
                8.3, 50, 3));
        persons.add(new StudentFS("FS002", "Frank Thompson", 22, "frank.thompson@student.edu",
                7.9, 47, 2));
        persons.add(new StudentFS("FS003", "Grace Kim", 25, "grace.kim@student.edu",
                8.8, 52, 5));
        persons.add(new StudentFS("FS004", "Henry Garcia", 24, "henry.garcia@student.edu",
                6.5, 44, 1));

        // Add Lecturers
        persons.add(lecturer1);
        persons.add(lecturer2);
        persons.add(lecturer3);

        // Add Teaching Assistants
        TeachingAssistant ta1 = new TeachingAssistant("TA001", "Ivan Petrov", 26,
                "ivan.petrov@university.edu", 20, 15);
        ta1.addLecture(lecturer1);
        ta1.addLecture(lecturer2);

        TeachingAssistant ta2 = new TeachingAssistant("TA002", "Julia Wang", 25,
                "julia.wang@university.edu", 18, 12);
        ta2.addLecture(lecturer2);
        ta2.addLecture(lecturer3);

        persons.add(ta1);
        persons.add(ta2);
    }

    private static void mainMenu() {
        System.out.println("===== Màn Hình =====");
        System.out.println("Hệ Thống Quản Lý Academy");
        System.out.println("1. Thêm thành viên");
        System.out.println("2. Hiển thị danh sách thành viên");
        System.out.println("3. Tìm kiếm thành viên theo tên hoặc email");
        System.out.println("4. Cập nhật thông tin cho thành viên");
        System.out.println("5. Xóa thành viên");
        System.out.println("6. Sắp xếp học viên theo điểm trung bình");
        System.out.println("7. Tính học phí của học viên");
        System.out.println("8. Tính  lương của  giảng viên");
        System.out.println("9. Tìm kếm giảng viên có bao nhiêu trợ giảng");
        System.out.println("10. Tạo lớp học");
        System.out.println("11. Thêm học viên vào lớp học");
        System.out.println("12. Học viên có điểm trung bình cao nhất trong lớp");
        System.out.println("13. Thêm buổi giảng mới");
        System.out.println("14. Xóa buổi giảng theo ngày");
        System.out.println("15. Hiển thị toàn bộ lịch");
        System.out.println("16. Thoát chương trình");
    }

    // 1 Quân
    private static void menuAdd() {
        System.out.println("===== Màn Hình =====");
        System.out.println("Thêm thành viên");
        System.out.println("1. Học viên BE");
        System.out.println("2. Học viên FS");
        System.out.println("3. Giảng viên");
        System.out.println("4. Trợ giảng");
        System.out.println("5. Thoát...");
    }

    private static void processAdd() {
        int choice;

        while (true) {
            menuAdd();
            System.out.print("Bạn muốn thêm thành viên nào: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    StudentBE newStudentBE = new StudentBE();
                    addNewPerson(newStudentBE);
                    break;
                case 2:
                    StudentFS newStudentFS = new StudentFS();
                    addNewPerson(newStudentFS);
                    break;
                case 3:
                    Lecturer newLecturer = new Lecturer();
                    addNewPerson(newLecturer);
                    break;
                case 4:
                    TeachingAssistant newTeachingAssistant = new TeachingAssistant();
                    addNewPerson(newTeachingAssistant);
                    addLecturerForAssistant(newTeachingAssistant);
                    break;
                case 5:
                    return;
                case 6:
                    System.out.println("Lựa chọn không hợp lệ xin chọn lại!\n");
            }
        }
    }

    private static String getRandomIdentify() {
        int number = (int) (Math.random() * 1000);
        return String.format("%03d", number);
    }

    private static boolean checkIdentify(String id) {
        for (Person person : persons) {
            if (person.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Person> void addNewPerson(T person) {
        do {
            person.setId(getRandomIdentify());
        } while (!checkIdentify(person.getId()));
        person.input();
        persons.add(person);
        System.out.println("Thêm thành viên mới thành công!\n");
    }

    private static void addLecturerForAssistant(TeachingAssistant teachingAssistant) {
        ArrayList<Lecturer> lecturers = getList(Lecturer.class);

        if (lecturers.isEmpty()) {
            System.out.println("Hiện tại chưa có giảng viên!\n");
            return;
        }

        while (!lecturers.isEmpty()) {
            System.out.println("Giảng viện hổ trợ: ");
            for (int i = 0; i < lecturers.size(); i++) {
                Lecturer lecturer = lecturers.get(i);
                System.out.println((i + 1) + ". " + lecturer.getId() + ": " + lecturer.getFullName());
            }
            System.out.println((lecturers.size() + 1) + ". " + "Dừng chọn.");

            System.out.println("Chọn GV: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == lecturers.size() + 1) {
                break;
            }

            if (choice < 1 || choice > lecturers.size() + 1) {
                System.out.println("Lựa chọn không hợp lệ!\n");
                continue;
            }

            teachingAssistant.addLecture(lecturers.get(choice - 1));
            lecturers.remove(choice - 1);
        }
    }

    // 2 Quân
    private static void menuShowPerson() {
        System.out.println("===== Màn Hình =====");
        System.out.println("Hiển thị thành viên");
        System.out.println("1. Học viên BE");
        System.out.println("2. Học viên FS");
        System.out.println("3. Giảng viên");
        System.out.println("4. Trợ giảng");
        System.out.println("5. Tất cả");
        System.out.println("6. Thoát...");
    }

    private static void processShowPerson() {
        int choice;

        while (true) {
            menuShowPerson();

            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> displayList(getList(StudentBE.class));
                case 2 -> displayList(getList(StudentFS.class));
                case 3 -> displayList(getList(Lecturer.class));
                case 4 -> displayList(getList(TeachingAssistant.class));
                case 5 -> displayList(persons);
                case 6 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ xin chọn lại!\n");
            }
        }
    }

    private static <T extends Person> ArrayList<T> getList(Class<T> type) {
        ArrayList<T> list = new ArrayList<>();

        for (Person person : persons) {
            if (type.isInstance(person)) {
                list.add(type.cast(person));
            }
        }

        return list;
    }

    private static <T extends Person> void displayList(ArrayList<T> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Thông tin người thứ " + (i + 1));
            System.out.println(list.get(i));
        }
    }

    // 3 Như
    public static void findPerson() {
        System.out.print("Nhập từ khóa cần tìm (họ tên hoặc email): ");
        String keyword = sc.nextLine().trim().toLowerCase();

        ArrayList<Person> ketQua = new ArrayList<>();
        String kieuTimKiem = "";

        if (isValidEmail(keyword)) {
            for (Person p : persons) {
                if (p.getEmail().equalsIgnoreCase(keyword)) {
                    ketQua.add(p);
                }
            }
            kieuTimKiem = "email";
        } else if (isValidName(keyword)) {
            for (Person p : persons) {
                if (p.getFullName().toLowerCase().contains(keyword)) {
                    ketQua.add(p);
                }
            }
            kieuTimKiem = "tên";
        } else {
            System.out.println("Từ khóa không hợp lệ. Vui lòng nhập tên hoặc email đúng định dạng.\n");
            return;
        }

        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy thành viên nào với " + kieuTimKiem + " \"" + keyword + "\"" + "\n");
        } else {
            System.out.println("Kết quả tìm kiếm theo " + kieuTimKiem + ":");
            displayList(ketQua);
        }
    }

    public static boolean isValidName(String keyword) {
        return keyword.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidEmail(String keyword) {
        return keyword.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$");
    }

    // 4 Như
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
                        person.setFullName(newName);
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

        if (personToDelete instanceof Lecturer) {
            for (Person p : persons) {
                if (p instanceof TeachingAssistant ta) {
                    ta.removeLecture((Lecturer) personToDelete);
                }
            }
        }
        persons.remove(personToDelete);

        System.out.println("Đã xóa thành viên thành công.");
    }

    // 6 Minh Sắp xếp danh sách theo điểm trung bình
    private static void menuSortByAVG() {
        int choose;
        while (true) {
            do {
                System.out.println("===== Màn Hình 4 =====\nSẮP XẾP THEO ĐIỂM TRUNG BÌNH");
                System.out.println("1. Học viên backend");
                System.out.println("2. Học viên fullstack");
                System.out.println("3. Trở về menu chính");

                System.out.print("Mời bạn lựa chọn: ");
                choose = Integer.parseInt(sc.nextLine());

                switch (choose) {
                    case 1:
                        sortAllStudentsUtil(StudentBE.class);
                        break;
                    case 2:
                        sortAllStudentsUtil(StudentFS.class);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
                }
            } while (choose < 1 || choose > 3);
        }
    }

    private static <T extends Student> void sortAllStudentsUtil(Class<T> type) {
        ArrayList<T> filteredList = new ArrayList<>();
        for (Person person : persons) {
            if (type.isInstance(person)) {
                filteredList.add(type.cast(person));
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("Không có học viên nào !");
            return;
        }

        System.out.println("1. Tăng dần theo điểm trung bình");
        System.out.println("2. Giảm dần theo điểm trung bình");
        System.out.print("Chọn cách sắp xếp: ");
        int choose = Integer.parseInt(sc.nextLine());

        if (choose == 1) {
            sortByFor(filteredList, true);
        } else if (choose == 2) {
            sortByFor(filteredList, false);
        } else {
            System.out.println("Lựa chọn không hợp lệ, xin chọn lại!");
            return;
        }

        System.out.println("----- Danh sách sau khi sắp xếp theo điểm trung bình -----");
        int count = 1;
        for (T student : filteredList) {
            System.out.println("Học viên thứ " + count++);

            System.out.println("ID: " + student.getId());
            System.out.println("Tên: " + student.getFullName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Điểm trung bình: " + student.getAvgScore());
            System.out.println("Xếp loại: " + student.getClassify());
            System.out.println("----------------------------");
        }
    }

    private static <T extends Student> void sortByFor(ArrayList<T> list, boolean ascending) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double avgI = list.get(i).getAvgScore();
                double avgJ = list.get(j).getAvgScore();

                boolean needSwap = ascending ? avgI > avgJ : avgI < avgJ;
                if (needSwap) {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    // 7. Minh
    private static void tuitionStudent() {
        System.out.println("Bạn muốn tính học phí cho học viên nào?");
        System.out.println("1. Học viên Backend");
        System.out.println("2. Học viên Fullstack");
        System.out.println("3. Tất cả");
        int choose;
        do {
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1 -> System.out.println("Tong hoc phi backend" + totalTuition(StudentBE.class));
                case 2 -> System.out.println("Tong hoc phi Fullend" + totalTuition(StudentFS.class));
                case 3 -> System.out.println("Tong tat hoc phi " + totalTuition(Student.class));
                default -> System.out.println("Bạn đã nhập sai ! Vui lòng chọn 1 - 2.");
            }
        } while (choose < 1 || choose > 3);
    }

    private static <T extends Student> double totalTuition(Class<T> type) {
        double sum = 0;
        for (Person person : persons) {
            if (type.isInstance(person)) {
                T student = type.cast(person);
                sum += student.tuitionFee();
            }
        }
        return sum;
    }

    // 8. Thủy
    private static void calculateSalary() {
        System.out.println("Hãy lựa chọn: ");
        System.out.println("1. Giảng viên");
        System.out.println("2. Trợ giảng");
        System.out.print("Mời bạn nhập: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            double totalSalaryLecturer = 0;
            for (Person p : persons) {
                if (p instanceof Lecturer lecturer) {
                    totalSalaryLecturer += lecturer.getSalary();
                }
            }
            System.out.println("=> Tổng lương của các giảng viên: " + totalSalaryLecturer);
        } else if (choice == 2) {
            double totalSalaryTA = 0;
            for (Person p : persons) {
                if (p instanceof TeachingAssistant ta) {
                    totalSalaryTA += ta.getSalary();
                }
            }
            System.out.println("=> Tổng lương của các trợ giảng: " + totalSalaryTA);
        } else {
            System.out.println("Lựa chọn không hợp lệ! Chỉ chọn 1 hoặc 2.");
        }
    }

    // 9. Thủy
    private static void findSupportsOfTeacherByName() {
        sc.nextLine();
        System.out.print("Nhập id giảng viên cần tra cứu: ");
        String id = sc.nextLine().trim();

        Lecturer foundLecturer = null;

        // Tìm giảng viên theo ID
        for (Person p : persons) {
            if (p instanceof Lecturer && id.equals(p.getId())) {
                foundLecturer = (Lecturer) p;
                break;
            }
        }
        // Nếu không tìm thấy
        if (foundLecturer == null) {
            System.out.println("Không tìm thấy giảng viên với ID: " + id);
            return;
        }
        // Duyệt toàn bộ danh sách để tìm các trợ giảng hỗ trợ giảng viên này
        ArrayList<TeachingAssistant> teachingAssistants = new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof TeachingAssistant ta) {
                for (Lecturer supported : ta.getLecturers()) {
                    if (supported.equals(foundLecturer)) {
                        teachingAssistants.add(ta);
                        break;
                    }
                }
            }
        }
        // In kết quả
        System.out.println("Giảng viên: \n" + foundLecturer);
        System.out.println("Số lượng trợ giảng hỗ trợ: " + teachingAssistants.size());

        if (teachingAssistants.isEmpty()) {
            System.out.println("Không có trợ giảng nào hỗ trợ giảng viên này.");
        } else {
            System.out.println("Danh sách trợ giảng:");
            for (TeachingAssistant ta : teachingAssistants) {
                System.out.println(" - " + ta);
            }
        }
    }

    // 10. Thêm khóa học - Như
    private static void addNewCourse() {
        Course newCourse = new Course();
        do {
            newCourse.setId(getRandomIdentify());
        } while (!checkIdentify(newCourse.getId()));
        newCourse.input();
        courses.add(newCourse);
        System.out.println("Thêm mới lớp học thành công!");
    }

    // 11. Thêm học viên vào lớp học - Như
    public static void addStudentToClass() {
        ArrayList<Student> students = getList(Student.class);
        if (courses.isEmpty()) {
            System.out.println("Hiện chưa có lớp học nào.");
            return;
        }

        System.out.println("=== DANH SÁCH LỚP HỌC ===");
        for (int i = 0; i < courses.size(); i++) {
            System.out.printf("%d. %s - %s\n", i + 1, courses.get(i).getId(), courses.get(i).getName());
        }

        int choice;
        while(true) {
            try {
                System.out.print("Chọn lớp học để thêm học viên: ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice < 1 || choice > courses.size()) {
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }

        Course selectedCourse = courses.get(choice - 1);

        // học viên chưa có lớp
        ArrayList<Student> studentInAllClasses = new ArrayList<>();
        for (Student s : selectedCourse.getStudents()) {
            studentInAllClasses.add(s);
        }
        // studentInAllClasses.addAll(selectedCourse.getStudents());

        ArrayList<Student> studentsNotInClass = new ArrayList<>();
        for (Student s : students) {
            if (!studentInAllClasses.contains(s)) {
                studentsNotInClass.add(s);
            }
        }

        if (studentsNotInClass.isEmpty()) {
            System.out.println("Tất cả học viên đã có lớp.");
            return;
        }

        while (true) {
            System.out.println("=== DANH SÁCH HỌC VIÊN CHƯA VÀO LỚP ===");
            for (int i = 0; i < studentsNotInClass.size(); i++) {
                System.out.printf("%d. %s - %s\n", i + 1, studentsNotInClass.get(i).getId(), studentsNotInClass.get(i).getFullName());
            }

            System.out.print("Chọn học viên để thêm: ");
            int studentChoice = Integer.parseInt(sc.nextLine());
            if (studentChoice < 1 || studentChoice > studentsNotInClass.size()) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            Student selectedStudent = studentsNotInClass.get(studentChoice - 1);
            selectedCourse.setStudents(selectedStudent);
            studentsNotInClass.remove(selectedStudent);
            System.out.printf("Đã thêm học viên %s vào lớp %s.\n", selectedStudent.getFullName(), selectedCourse.getName());
            System.out.println("Muốn tiếp tục chọn hay không ? (Y/N)");
            String choose = sc.nextLine();
            if (choose.equalsIgnoreCase("Y")) {
                continue;
            }
            System.out.println("Hoàn thành thêm học viên.");
            break;
        }
    }

    // 12. Tìm học viên điểm cao nhất - Quân
    private static void findStudentMaxScore() {
        if (courses.isEmpty()) {
            System.out.println("Hiện tại chưa có lớp học!");
            return;
        }

        System.out.println("===== DANH SÁCH LỚP HỌC =====");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getId() + " - " + courses.get(i).getName());
        }

        ArrayList<Student> students;
        while (true) {
            try {
                System.out.print("Chọn lớp: ");
                int choiceCourse = Integer.parseInt(sc.nextLine());

                if (choiceCourse < 0 || choiceCourse > courses.size()) {
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
                }

                students = courses.get(choiceCourse - 1).getStudents();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên!");
            }
        }

        if (students.isEmpty()) {
            System.out.println("Hiện tại lớp chưa có học viên nào!");
            return;
        }

        ArrayList<Student> listMaxScore = new ArrayList<>();
        double max = students.get(0).getAvgScore();
        for (Student student : students) {
            if (student.getAvgScore() > max) {
                max = student.getAvgScore();
                listMaxScore.clear();
                listMaxScore.add(student);
            } else if (student.getAvgScore() == max) {
                listMaxScore.add(student);
            }
        }

        System.out.println("Danh sách sinh viên có điểm cao nhất trong lớp: ");
        displayList(listMaxScore);
    }

    // 13. Thêm buổi giảng dạy - Thủy
    private static void addSchedule() {
        Schedule newSchedule = new Schedule();
        System.out.println("=== Thêm buổi giảng mới ===");
        newSchedule.input();
        schedules.add(newSchedule);
    }

    //14. Xóa lịch dạy - Minh
    private static void deleteSchedule() {
        LocalDate dateToDelete = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String keyword;

        while (true) {
            System.out.println("Nhập ngày-tháng-năm cần xóa (dd/MM/yyyy):");
            keyword = sc.nextLine().trim();
            try {
                dateToDelete = LocalDate.parse(keyword, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày nhập không hợp lệ! Vui lòng nhập lại.\n");
            }
        }

        Iterator<Schedule> i = schedules.iterator();
        boolean found = false;

        while (i.hasNext()) {
            Schedule s = i.next();
            if (s.getDay() != null && s.getDay().equals(dateToDelete)) {
                i.remove();
                found = true;
            }
        }

        if (found) {
            System.out.println("Đã xóa lịch có ngày " + keyword);
        } else {
            System.out.println("Không tìm thấy lịch có ngày " + keyword);
        }
    }

    //15. Hiển thị lịch dạy - Minh
    private static void displaySchedule() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (schedules.isEmpty()) {
            System.out.println("Danh sách buổi giảng trống.");
            return;
        }
        int count = 0;
        for (Schedule s : schedules) {
            System.out.println("Lịch học thứ: " + (++count));

            String dayFormatted = (s.getDay() != null) ? s.getDay().format(formatter) : "Chưa có ngày";
            String content = (s.getContent() != null && !s.getContent().isEmpty()) ? s.getContent() : "Chưa có nội dung";

            System.out.println("Ngày: " + dayFormatted);
            System.out.println("Nội dung: " + content);
            System.out.println("---------------------------\n");
        }
    }

    public static void main(String[] args) {
        initializeSampleData();
        int choice;
        while (true) {
            mainMenu();
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> processAdd();
                case 2 -> processShowPerson();
                case 3 -> findPerson();
                case 4 -> menuUpdate();
                case 5 -> menuDelete();
                case 6 -> menuSortByAVG();
                case 7 -> tuitionStudent();
                case 8 -> calculateSalary();
                case 9 -> findSupportsOfTeacherByName();
                case 10 -> addNewCourse();
                case 11 -> addStudentToClass();
                case 12 -> findStudentMaxScore();
                case 13 -> addSchedule();
                case 14 -> deleteSchedule();
                case 15 -> displaySchedule();
                case 16 -> {
                    System.err.println("Kết thúc chương trình!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ xin chọn lại!\n");
            }
        }
    }
}