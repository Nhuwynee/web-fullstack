package ex_morning;

import java.util.Scanner;

public class ProductionEmployee {
/// Thuộc tính chung (trùng lặp) -> xóa -> sử dụng extends
    private String id;
    private String fullName;
    private String dayOfBirth;
    private String address;

/// Thuộc tính riêng
    private double numberProducts;


/// Tạo constructor



    /// Methods
    public void input() {
        Scanner sc = new Scanner(System.in);

        /// Trùng lặp code -> sử dụng từ lớp cha
        System.out.print("Nhập vào tên: ");
        this.fullName = sc.nextLine();

        System.out.print("Nhập vào ngày sinh: ");
        this.dayOfBirth = sc.nextLine();

        System.out.print("Nhập vào địa chỉ: ");
        this.address = sc.nextLine();

//        input(); // Gọi đề quy
//        super.input();

        /// Riêng
        System.out.print("Nhập vào số sản phẩm: ");
        this.numberProducts = Double.parseDouble(sc.nextLine());
    }

    public void output() {
        /// Trùng lặp code -> sử dụng từ lớp cha
        System.out.println("Mã: " + this.id);
        System.out.println("Tên: " + this.fullName);
        System.out.println("Ngày sinh: " + this.dayOfBirth);
        System.out.println("Địa chỉ: " + this.address);

        /// Riêng
        System.out.println("Số sản phẩm: " + this.numberProducts);
    }


/// Đảm bảo tính bao đóng
}
