package ss5_polymorphism;


class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}


public class CompileTime {
    public static void main(String[] args) {
        Calculator cal = new Calculator();

        int sum1 = cal.add(5, 10); // Compiler chọn phương thức add(int, int)
        int sum2 = cal.add(1, 2, 3); // Compiler chọn phương thức add(int, int, int)
        double sum3 = cal.add(3.14, 2); // Compiler chọn phương thức add() nào???
    }
}
