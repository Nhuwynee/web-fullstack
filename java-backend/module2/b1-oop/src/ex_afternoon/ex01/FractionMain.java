package ex_afternoon.ex01;

public class FractionMain {
    public static void main(String[] args) {
        Fraction n1 = new Fraction();
        Fraction n2 = new Fraction();

        System.out.println("===== Enter the first fraction =====");
        n1.input();

        System.out.println("===== Enter the second fraction =====");
        n2.input();

        System.out.println("\n - First fraction =====");
        n1.output();

        System.out.println("\n - Second fraction =====");
        n2.output();

        System.out.println("\n===== Sum of two fractions =====");
        System.out.printf("%s + %s = %s\n",
                n1.toString(), n2.toString(), Fraction.sum(n1, n2).getShorten());

        System.out.println("\n===== Sub of two fractions =====");
        System.out.printf("%s - %s = %s\n",
                n1.toString(), n2.toString(), Fraction.sub(n1, n2).getShorten());

        System.out.println("\n===== Mul of two fractions =====");
        System.out.printf("%s * %s = %s\n",
                n1.toString(), n2.toString(), Fraction.mul(n1, n2).getShorten());

        Fraction result = Fraction.div(n1, n2);
        System.out.println("\n===== Div of two fractions =====");
        if (result == null) {
            System.out.printf("%s / %s = Cannot divide (second fraction = 0)\n",
                    n1.toString(), n2.toString());
        } else {
            System.out.printf("%s / %s = %s\n",
                    n1.toString(), n2.toString(), result.getShorten());
        }

        System.out.println("\n===== Sign of the fractions =====");
        System.out.printf("Fraction 1: %s ", n1);
        n1.isNumber();
        System.out.printf("Fraction 2: %s ", n2);
        n2.isNumber();
    }
}
