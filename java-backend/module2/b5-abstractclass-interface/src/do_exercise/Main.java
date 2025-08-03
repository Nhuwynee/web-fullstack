package do_exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Phone> phones = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choose;

        while (true) {
            System.out.println("\n===== Màn Hình Menu Chính =====");
            System.out.println("CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI");
            System.out.println("1. Xem danh sách điện thoại");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp theo giá");
            System.out.println("6. Tìm kiếm");
            System.out.println("7. Tính tổng tiền");
            System.out.println("8. Giảm giá cho điện thoại cũ");
            System.out.println("9. Thoát chương trình");
            System.out.print("Mời bạn lựa chọn: ");

            try {
                choose = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
                continue;
            }

            switch (choose) {
                case 1 -> menuXemDanhSach();
                case 2 -> menuAddPhone();
                case 3 -> updatePhone();
                case 4 -> deletePhone();
                case 5 -> menuSortByPrices();
                case 6 -> menuSearchPhone();
                case 7 ->
                    System.out.println("TỔNG TIỀN CỦA TẤT CẢ ĐIỆN THOẠI CÓ TRONG CỬA HÀNG LÀ: " +
                            String.format("%,.0f", tinhTongGia()) + " VNĐ");
                case 8 -> {
                    menuPromotionPrice();
                    printPhone(OldPhone.class);
                }
                case 9 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    // valid id
    private static boolean isValidId(String id) {
        if (id == null || id.length() != 6) return false;
        String prefix = id.substring(0, 3);
        String numberPart = id.substring(3);
        if (!prefix.equals("DTC") && !prefix.equals("DTM")) return false;

        for (char c : numberPart.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    // menu 1
    private static void menuXemDanhSach() {
        int choose;
        while (true) {
            System.out.println("\n--- Menu Xem Danh Sách ---");
            System.out.println("1. Xem tất cả");
            System.out.println("2. Xem điện thoại cũ");
            System.out.println("3. Xem điện thoại mới");
            System.out.println("4. Trở về menu chính");
            System.out.print("Lựa chọn của bạn: ");

            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    System.out.println("Hiển thị tất cả điện thoại.");
                    printPhone(Phone.class);
                    break;
                case 2:
                    System.out.println("Hiển thị điện thoại cũ.");
                    printPhone(OldPhone.class);
                    break;
                case 3:
                    System.out.println("Hiển thị điện thoại mới.");
                    printPhone(NewPhone.class);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void menuSearchPhone() {
        while (true) {
            System.out.println("\n--- Menu Tìm Kiếm ---");
            System.out.println("1. Tìm kiếm tất cả điện thoại");
            System.out.println("2. Tìm kiếm điện thoại cũ");
            System.out.println("3. Tìm kiếm điện thoại mới");
            System.out.println("4. Trở về menu chính");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int luachon = Integer.parseInt(sc.nextLine());
                switch (luachon) {
                    case 1:
                        menuDetailSearch(Phone.class);
                        break;
                    case 2:
                        menuDetailSearch(OldPhone.class);
                        break;
                    case 3:
                        menuDetailSearch(NewPhone.class);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    // Task 3 - 1 : Cập nhật điện thoại
    private static void printPhone(Class<?> clazz) {
        if (clazz == null) {
            System.out.println("Class truyền vào bị null.");
            return;
        }

        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
            return;
        }

        if (clazz == Phone.class) {
            phones.forEach(System.out::println);
        } else {
            for (Phone p : phones) {
                if (clazz.isInstance(p)) {
                    System.out.println(p);
                }
            }
        }
    }

    // menu 2
    private static void menuAddPhone() {
        int choose;
        while (true) {
            System.out.println("\n--- Menu Thêm Mới ---");
            System.out.println("1. Thêm mới điện thoại cũ");
            System.out.println("2. Thêm mới điện thoại mới");
            System.out.println("3. Trở về menu chính");
            System.out.print("Lựa chọn của bạn: ");

            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    System.out.println("Thêm điện thoại cũ.");
                    OldPhone olephone = new OldPhone();
                    addPhone(olephone);
                    break;
                case 2:
                    System.out.println("Thêm điện thoại mới.");
                    NewPhone newphone = new NewPhone();
                    addPhone(newphone);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // Task 3 - 2 : Cập nhật điện thoại
    private static <T extends Phone> void addPhone(T phone) {
        phone.setId(getIdentity(phone.getClass()));
        phone.input(sc);
        phones.add(phone);
        System.out.println("Thêm điện thoại thành công!\n");

    }

    private static String getIdentity(Class<?> clazz) {
        int maxId = 0;
        for (Phone p : phones) {
            if(clazz.isInstance(p)) {
                String id = p.getId();
                if(id.length() > 3){
                    int numberLast = Integer.parseInt(id.substring(3));
                    if (numberLast > maxId) {
                        maxId = numberLast;
                    }
                }
            }
        }
        return String.format("%03d", (maxId + 1));
    }


    // Task 3 - 3 : Cập nhật điện thoại

    private static void updatePhone() {
        String id;
        boolean check = false;
        do {
            System.out.println("Vui lòng nhập id điện thoại muốn sửa chữa (DTCxxx, DTMxxx):");
            id = sc.nextLine();
            if (isValidId(id)) {
                check = true;
            } else {
                System.out.println("ID không hợp lệ! Vui lòng nhập lại.");
            }
        } while (!check);

        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại rỗng!");
            return;
        }

        boolean found = false;
        for (Phone p : phones) {
            if (id.equalsIgnoreCase(p.getId())) {
                found = true;

                System.out.println("Bạn muốn cập nhật: ");
                System.out.println("1. Tên điện thoại.\n" +
                        "2. Giá bán.\n" +
                        "3. Thời gian bảo hành.\n" +
                        "4. OS (Android/iOS).\n" +
                        "5. Hãng sản xuất.");
                if (p instanceof NewPhone) {
                    System.out.println("6. Số lượng.");
                } else if (p instanceof OldPhone) {
                    System.out.println("6. Tình trạng pin.");
                }
                System.out.println("7. Trở về.");

                int choose;
                try {
                    do {
                        System.out.println("Nhập lựa chọn của bạn: ");
                        choose = Integer.parseInt(sc.nextLine());
                        switch (choose) {
                            case 1 -> p.inputName(sc);
                            case 2 -> p.inputPrice(sc);
                            case 3 -> p.inputWarrantyPeriod(sc);
                            case 4 -> p.inputOsPhone(sc);
                            case 5 -> p.inputManufacturer(sc);
                            case 6 -> {
                                if (p instanceof NewPhone np) {
                                    np.inputQuantity(sc);
                                } else if (p instanceof OldPhone op) {
                                    op.inputBatteryStatus(sc);
                                }
                            }
                            case 7 -> {
                                return;
                            }
                            default -> System.out.println("Lựa chọn không hợp lệ!");
                        }
                        System.out.println("Cập nhập thành công!");
                    } while (choose < 1 || choose > 7);
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số!");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy thông tin điện thoại với id = " + id);
        }
    }

    // Task 3 - 4
    private static void deletePhone() {
        String id;
        while (true) {
            System.out.print("Nhập id điện thoại muốn xóa: ");
            id = sc.nextLine();

            if (!isValidId(id)) {
                System.out.println("Id nhập vào không hợp lệ!");
            } else {
                break;
            }
        }

        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại rỗng!");
            return;
        }

        Phone phoneToDelete = null;
        for (Phone p : phones) {
            if (id.equalsIgnoreCase(p.getId())) {
                phoneToDelete = p;
                phones.remove(phoneToDelete);
                System.out.println("Xóa thông tin điện thoại thành công.");
                break;
            }
        }

        if (phoneToDelete == null) {
            System.out.println("Không tìm thấy thông tin điện thoại với id = " + id);
        }
    }

    // Task 3 - 5
    private static void menuSortByPrices() {
        int choose;
        while (true) {
            System.out.println("\n--- Menu Sắp Xếp Theo Giá ---");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            System.out.println("3. Trở về menu chính");
            System.out.print("Lựa chọn của bạn: ");

            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    System.out.println("Sắp xếp tăng dần.");
                    printSortedPhones("asc");
                    break;
                case 2:
                    System.out.println("Sắp xếp giảm dần.");
                    printSortedPhones("desc");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void printSortedPhones(String type) {
        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
            return;
        }
        if (type.equals("desc")) {
            sortByFor(type);
            System.out.println("===== Danh sách điện thoại sau khi sắp xếp theo giá (giảm dần) =====");
            for (Phone p : phones) {
                System.out.println(p);
            }
        } else if(type.equals("asc")) {
            sortByFor(type);
            System.out.println("===== Danh sách điện thoại sau khi sắp xếp theo giá (tăng dần) =====");
            for (Phone p : phones) {
                System.out.println(p);
            }
        }
    }

    private static void sortByFor(String type) {
        if (phones == null || phones.size() < 2) return;
        if(type.equals("asc")){
            for (int i = 0 ;i < phones.size() ;i++){
                for (int j = i + 1; j < phones.size(); j++){
                    if (phones.get(i).getPrice().compareTo(phones.get(j).getPrice()) > 0) {
                        Phone temp = phones.get(i);
                        phones.set(i, phones.get(j));
                        phones.set(j, temp);
                    }
                }
            }
        } else if(type.equals("desc")){
            for (int i = 0 ;i < phones.size() ;i++){
                for (int j = i + 1; j < phones.size(); j++){
                    if (phones.get(i).getPrice().compareTo(phones.get(j).getPrice()) < 0) {
                        Phone temp = phones.get(i);
                        phones.set(i, phones.get(j));
                        phones.set(j, temp);
                    }
                }
            }
        }

    }

    // Task 3 - 6
    private static void menuDetailSearch(Class<?> clazz) {
        int choose;
        String loaiDienThoai = "";

        if (clazz == OldPhone.class) {
            loaiDienThoai = "Điện thoại cũ";
        } else if (clazz == NewPhone.class) {
            loaiDienThoai = "Điện thoại mới";
        }

        while (true) {
            System.out.println("\n--- " + loaiDienThoai + " - Menu Tìm Kiếm Chi Tiết ---");
            System.out.println("1. Tìm kiếm theo khoảng giá");
            System.out.println("2. Tìm kiếm theo tên");
            System.out.println("3. Tìm kiếm theo hãng");
            System.out.println("4. Trở về menu Tìm kiếm");
            System.out.print("Lựa chọn của bạn: ");

            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    boolean check = false;
                    BigDecimal from;
                    BigDecimal to;
                    do {
                        System.out.print("Nhập giá từ: ");
                        from =  BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
                        System.out.print("Đến: ");
                        to = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
                        if( from.compareTo(to) > 0 ) {
                            check = true;
                            System.out.println("Vui lòng nhập lại khoảng giá hợp lệ");
                        }
                    } while(check);

                    System.out.println("Tìm " + loaiDienThoai + " trong khoảng giá " + from + " - " + to);
                    searchPhoneByPrice(clazz, from, to);
                    break;
                case 2:
                    System.out.print("Nhập tên cần tìm: ");
                    String ten = sc.nextLine();
                    System.out.println("Tìm " + loaiDienThoai + " theo tên: " + ten);
                    searchPhoneByName(clazz, ten);
                    break;
                case 3:
                    System.out.print("Nhập hãng cần tìm: ");
                    String hang = sc.nextLine();
                    System.out.println("Tìm " + loaiDienThoai + " theo hãng: " + hang);
                    searchPhoneByManu(clazz, hang);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void searchPhoneByName(Class<?> clazz, String ten) {
        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống!");
            return;
        }
        boolean found = false;
        for (Phone phone : phones) {
            if (clazz.isInstance(phone) && phone.getName().toLowerCase().contains(ten.toLowerCase())) {
                System.out.println(phone);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy điện thoại nào với tên: " + ten);
        }
    }

    private static void searchPhoneByManu(Class<?> clazz, String hang) {
        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống!");
            return;
        }
        boolean found = false;
        for (Phone phone : phones) {
            if (clazz.isInstance(phone) && phone.getManufacturer().toLowerCase().contains(hang.toLowerCase())) {
                System.out.println(phone);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy điện thoại nào của hãng: " + hang);
        }
    }

    private static void searchPhoneByPrice(Class<?> clazz, BigDecimal from, BigDecimal to) {
        if (phones == null || phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống!");
            return;
        }
        boolean found = false;
        for (Phone phone : phones) {
            if (clazz.isInstance(phone) && phone.getPrice().compareTo(from) >= 0 && phone.getPrice().compareTo(to) <= 0) {
                System.out.println(phone);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy điện thoại nào trong khoảng giá: " + from + " - " + to);
        }
    }

    // Task 3 - 7 tính tổng giá
    private static BigDecimal tinhTongGia() {
        BigDecimal total = new BigDecimal("0") ;
        for (Phone p : phones) {
            if (p instanceof OldPhone oldPhone) {
                total = total.add(oldPhone.getTotalPrice());
            }else if( p instanceof NewPhone newPhone) {
                total = total.add(newPhone.getTotalPrice());
            }
        }
        return total;
    }

    // Task 3 - 8
    private static void promotionPrice(Float proVal) {
        if (phones == null || phones.isEmpty()) {
            System.out.println("Không có điện thoại nào trong giỏ hàng :)))!");
            return;
        }
        boolean checkUpdateVal = false;
        for (Phone p : phones) {
            if (p instanceof OldPhone oldPhone) {
                BigDecimal newPrice = oldPhone.promote(proVal);
                oldPhone.setPrice(newPrice);
                checkUpdateVal = true;
            }
        }
        if (checkUpdateVal) {
            System.out.println("Giảm giá thành công cho tất cả sản phẩm cũ");
        } else {
            System.out.println("Không có điện thoại cũ nào để giảm giá!");
        }
    }

    private static void menuPromotionPrice() {
        while (true) {
            try {
                System.out.print("Nhập % giảm giá cho sản phẩm cũ (0-100): ");
                float proVal = Float.parseFloat(sc.nextLine());
                if (proVal < 0 || proVal > 100) {
                    System.out.println("Phần trăm giảm giá phải nằm trong khoảng 0-100%!");
                }
                promotionPrice(proVal);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ cho phần trăm giảm giá!");
            }
        }
    }
}
