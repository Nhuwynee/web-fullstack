package ss5_polymorphism.employee_manager;

import java.util.Scanner;

public class ProductionEmployee extends Employee {
    private double numberProducts;

    public ProductionEmployee() {
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
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

    public double getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(double numberProducts) {
        this.numberProducts = numberProducts;
    }
}
