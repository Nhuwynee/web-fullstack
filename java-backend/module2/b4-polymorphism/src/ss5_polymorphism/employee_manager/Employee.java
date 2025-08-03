package ss5_polymorphism.employee_manager;

import java.util.Scanner;

public abstract class Employee implements IEmployee {
    // ===== Properties =====
    private String id;
    private String fullName;
    private String dayOfBirth;
    private String address;

    // ===== Methods =====
    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập vào tên: ");
        this.fullName = sc.nextLine();

        System.out.print("Nhập vào ngày sinh: ");
        this.dayOfBirth = sc.nextLine();

        System.out.print("Nhập vào địa chỉ: ");
        this.address = sc.nextLine();
    }

    public void output() {
        System.out.println("Mã: " + this.id);
        System.out.println("Tên: " + this.fullName);
        System.out.println("Ngày sinh: " + this.dayOfBirth);
        System.out.println("Địa chỉ: " + this.address);
    }

    // ===== Getter & Setter =====
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
