package do_exercises.phone;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Phone implements Comparable<Phone> {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDate warrantyPeriod;
    private OSPhone osPhone;
    private String manufacturer;

    public Phone() {}

    public Phone(String id, String name, BigDecimal price, LocalDate warrantyPeriod, OSPhone osPhone, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warrantyPeriod = warrantyPeriod;
        this.osPhone = osPhone;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Giá bán phải >= 0");
        }
        this.price = price;
    }


    public LocalDate getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(LocalDate warrantyPeriod) {
        if (warrantyPeriod == null || !warrantyPeriod.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày bảo hành phải sau ngày hiện tại");
        }
        this.warrantyPeriod = warrantyPeriod;
    }


    public OSPhone getOsPhone() {
        return osPhone;
    }

    public void setOsPhone(OSPhone osPhone) {
        this.osPhone = osPhone;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", warrantyPeriod=" + warrantyPeriod +
                ", osPhone=" + osPhone +
                ", manufacturer='" + manufacturer + '\'' +
                ' ';
    }

    public final void inputName(Scanner sc) {
        System.out.print("Nhập tên điện thoại: ");
        setName(sc.nextLine());
    }

    public final void inputPrice(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập giá bán: ");
                setPrice(new BigDecimal(sc.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi nhập giá không hợp lệ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public final void inputWarrantyPeriod(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print("Nhập thời gian bảo hành (dd/MM/yyyy): ");
                LocalDate time = LocalDate.parse(sc.nextLine().trim(), formatter);
                setWarrantyPeriod(time);
                break;
            } catch (DateTimeException e) {
                System.out.println("Lỗi nhập ngày không hợp lệ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public final void inputOsPhone(Scanner sc) {
        OSPhone[] osOptions = OSPhone.values();
        while (true) {
            System.out.println("Chọn OS:");
            for (int i = 0; i < osOptions.length; i++) {
                System.out.println((i + 1) + ". " + osOptions[i]);
            }
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice >= 1 && choice <= osOptions.length) {
                    setOsPhone(osOptions[choice - 1]);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số lựa chọn thích hợp.");
            }
            System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
        }
    }

    public final void inputManufacturer(Scanner sc) {
        System.out.print("Nhập hãng điện thoại: ");
        setManufacturer(sc.nextLine());
    }

    public void input(Scanner sc) {
        inputName(sc);
        inputPrice(sc);
        inputWarrantyPeriod(sc);
        inputOsPhone(sc);
        inputManufacturer(sc);
    }

    public abstract BigDecimal getTotalPrice();

    @Override
    public int compareTo(Phone other) {
        return this.price.compareTo(other.price);
    }

//    public void input(Scanner sc) {
//        System.out.println("Nhập thông tin điện thoại #" + id + " :");
//
//        System.out.print("Nhập tên điện thoại: ");
//        this.name = sc.nextLine();
//
//        while (true) {
//            System.out.print("Nhập giá bán: ");
//            try {
//                this.price = new BigDecimal(sc.nextLine().trim());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Nhập sai định dạng. Vui lòng nhập lại!");
//            }
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        while (true) {
//            System.out.print("Nhập thời gian bảo hành (dd/MM/yyyy): ");
//            String input = sc.nextLine().trim();
//
//            try {
//                this.warrantyPeriod = LocalDate.parse(input, formatter);
//                break;
//            } catch (DateTimeParseException e) {
//                System.out.println("Nhập sai định dạng. Vui lòng nhập lại!");
//            }
//        }
//
//        while (true) {
//            System.out.print("Chọn OS: ");
//            OSPhone[] osOptions = OSPhone.values();
//            for (int i = 0; i < osOptions.length; i++) {
//                System.out.println((i + 1) + ". " + osOptions[i]);
//            }
//            try {
//                int choose = Integer.parseInt(sc.nextLine());
//                if (choose >= 1 && choose < osOptions.length) {
//                    this.osPhone = osOptions[choose - 1];
//                    break;
//                } else {
//                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn số từ 1 - " + osOptions.length);
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Vui lòng nhập một số hợp lệ!");
//            }
//        }
//
//        System.out.print("Nhập hãng điện thoại: ");
//        this.manufacturer = sc.nextLine();
//    }

}
