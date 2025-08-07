package ss5_polymorphism.employee_manager;

import java.util.Scanner;

public class ManagementEmployee extends Employee {
    private double basicSalary;
    private double coefficientSalary;

    public ManagementEmployee() {
    }


    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);

        super.input();

        System.out.print("Nhập vào lương cơ bản ");
        this.basicSalary = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập vào hệ số lương: ");
        this.coefficientSalary = Double.parseDouble(sc.nextLine());
    }

    @Override
    public void output() {
        super.output();

        System.out.println("Lương cơ bản: " + this.basicSalary);
        System.out.println("Hệ số lương: " + this.coefficientSalary);
    }

    @Override
    public double getSalary() {
        return basicSalary * coefficientSalary;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }
}
