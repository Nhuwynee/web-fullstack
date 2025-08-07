package ex_morning;

import java.util.Scanner;

public class Employee {
/// 4 thuộc tính chung
    private String id;
    private String fullName;
    private String dayOfBirth;
    private String address;

/// 4 thuộc tính chung này là private -> làm thế nào các class kế thừa sử dụng được ??



/// Tạo Constructor cho lớp cha gồm Không tham số & 4 tham số
    public Employee() {
    }

    public Employee(String id, String fullName, String dayOfBirth, String address) {
        this.id = id;
        this.fullName = fullName;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
    }


    /// Methods dùng chung
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


/// Đảm bảo tính bao đóng
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
