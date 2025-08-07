package ex_morning;

import java.util.Scanner;

public class ManagementEmployee extends Employee {
/// Thuộc tính chung (trùng lặp) -> xóa -> sử dụng extends
//    private String id;
//    private String fullName;
//    private String dayOfBirth;
//    private String address;

/// Thuộc tính riêng
    private double basicSalary;
    private double coefficientSalary;


/// Tạo constructor



    @Override // Chỉ là ký hiệu giúp đọc code dễ hơn -> Không có cũng được
    public void input() {
        Scanner sc = new Scanner(System.in);

        /// Trùng lặp code -> sử dụng từ lớp cha
//        System.out.print("Nhập vào tên: ");
//        this.fullName = sc.nextLine();
//
//        System.out.print("Nhập vào ngày sinh: ");
//        this.dayOfBirth = sc.nextLine();
//
//        System.out.print("Nhập vào địa chỉ: ");
//        this.address = sc.nextLine();

        super.input();

        /// Riêng
        System.out.print("Nhập vào lương cơ bản ");
        this.basicSalary = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập vào hệ số lương: ");
        this.coefficientSalary = Double.parseDouble(sc.nextLine());
    }

    public void output() {
        /// Trùng lặp code -> sử dụng từ lớp cha
//        System.out.println("Mã: " + this.id);
//        System.out.println("Tên: " + this.fullName);
//        System.out.println("Ngày sinh: " + this.dayOfBirth);
//        System.out.println("Địa chỉ: " + this.address);

        super.output();

        /// Riêng
        System.out.println("Lương cơ bản: " + this.basicSalary);
        System.out.println("Hệ số lương: " + this.coefficientSalary);
    }


/// Đảm bảo tính bao đóng

}
