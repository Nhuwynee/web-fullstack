package ss5_abstract_interface;

import java.time.LocalTime;
import java.util.Scanner;

abstract class Employee {
    static final Scanner sc = new Scanner(System.in);

    private String id;
    private String name;


    /// Phương thức bình thường
    public void input() {
        System.out.print("Nhập vào tên: ");
        this.name = sc.nextLine();
    }

    public void output() {
        System.out.println("Mã: " + this.id);
        System.out.println("Tên: " + this.name);
    }


    /// Phương thức trừu tượng (không thể triển khai ở lớp cha này vì thiếu thông tin)
    /// -> chỉ cần biết là có hành vi lấy lương
    /// không cần biết cụ thể cách tính lương như thế nào
//    public double getSalary() {
//        return 0; // Cách viết này không đúng chuẩn OOP -> có thể quên override ở lớp con
//    }
    public abstract double getSalary();
    // Không có body -> giúp ẩn đi phần xử lý bên trong
    // Khi đã khai báo abstract method ở class cha
    // -> class con sẽ báo lỗi "Cần implement method" nếu chưa triển khai method đó


    /// abstract không đi chung với final
//    public final abstract double getSalary();
    // final không cho lớp con override lại method
    // nhưng abstract lại bắt lớp con phải triển khai method
    // -> 2 từ khóa này đối lập nhau
}


/// Lớp abstract vẫn có thể extend từ lớp abstract khác
abstract class ProductionEmployee extends Employee implements TimeKeeping {
    private double numberProducts;

    @Override
    public void input() {
        super.input();

        System.out.print("Nhập vào số sản phẩm: ");
        this.numberProducts = Double.parseDouble(sc.nextLine());
    }

    @Override
    public void output() {
        super.output();

        System.out.println("Số sản phẩm: " + this.numberProducts);
    }

    @Override
    public double getSalary() {
        return numberProducts * 100;
    }


    /// Triển khai ghi đè lại default method của interface
    public abstract void test();
}


class FTProductionEmployee extends ProductionEmployee {
    /// Phương thức abstract getSalary() phải được triển khai 1 lần
    @Override
    public double getSalary() {
        return 8 * 1000;
    }


    /// Khi lớp cha đã implement interface TimeKeeping thì
    /// lớp con buộc phải triển khai abstract method của interface
    /// phải có phạm vi truy cập public, vì mọi method trong interface đều public
    @Override
    public LocalTime checkIn() {
        return LocalTime.of(8, 0);
    }


    /// Triển khai ghi đè lại default method của interface
    @Override
    public void test() {
        System.out.println("This is a time keeping test on full time production employee");
    }
}

