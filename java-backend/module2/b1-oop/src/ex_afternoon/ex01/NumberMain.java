package ex_afternoon.ex01;

public class NumberMain {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();

        System.out.println("===== Nhập phân số thứ nhất =====");
        n1.input();

        System.out.println("===== Nhập phân số thứ hai =====");
        n2.input();

        System.out.println("\n - Phân số thứ nhất =====");
        n1.output();

        System.out.println("\n - Phân số thứ hai =====");
        n2.output();

        System.out.println("\n===== Tổng 2 phân số =====");
        System.out.printf("%s + %s = %s\n",
                n1.toString(), n2.toString(), Number.sum(n1, n2).getRutGon());

        System.out.println("\n===== Hiệu 2 phân số =====");
        System.out.printf("%s - %s = %s\n",
                n1.toString(), n2.toString(), Number.sub(n1, n2).getRutGon());

        System.out.println("\n===== Tích 2 phân số =====");
        System.out.printf("%s * %s = %s\n",
                n1.toString(), n2.toString(), Number.mul(n1, n2).getRutGon());

        Number result = Number.div(n1, n2);
        System.out.println("\n===== Thương 2 phân số =====");
        if (result == null) {
            System.out.printf("%s / %s = Không thể chia (phân số thứ 2 = 0)\n",
                    n1.toString(), n2.toString());
        } else {
            System.out.printf("%s / %s = %s\n",
                    n1.toString(), n2.toString(), result.getRutGon());
        }

        System.out.println("\n===== Dấu của các phân số =====");
        System.out.printf("Phân số 1: %s ", n1);
        n1.isNumber();
        System.out.printf("Phân số 2: %s ", n2);
        n2.isNumber();
    }
}