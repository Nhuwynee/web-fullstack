package ex_afternoon.ex01;

import java.util.Scanner;

public class Fraction {
    int numerator;
    int denominator;

    public void input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter numerator: ");
                numerator = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter denominator: ");
                denominator = Integer.parseInt(sc.nextLine());
                if (denominator == 0) {
                    System.out.println("Denominator must not be zero! Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    public void output() {
        System.out.println("Entered fraction: " + toString());
        System.out.println("Reduced fraction: " + getShorten());
    }


    @Override
    public String toString() {
        if (denominator == 1) return String.valueOf(numerator);
        if (numerator == 0) return "0";
        if (denominator < 0) return (-numerator) + "/" + (-denominator);
        return numerator + "/" + denominator;
    }

    public String getShorten() {
        int u = uscln(Math.abs(numerator), Math.abs(denominator));
        int shortenNumerator = numerator / u;
        int shortenDenominator = denominator / u;

        if (shortenDenominator < 0) {
            shortenNumerator = -shortenNumerator;
            shortenDenominator = -shortenDenominator;
        }
        if (shortenDenominator == 1) {
            return String.valueOf(shortenNumerator);
        }

        return shortenNumerator + "/" + shortenDenominator;
    }

    // 16 8
    // temp = 0
    // a = 8
    // b = 0

    public int uscln(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static Fraction sum(Fraction n1, Fraction n2) {
        Fraction newNum = new Fraction();

        if (n1.denominator == n2.denominator) {
            newNum.numerator = n1.numerator + n2.numerator;
            newNum.denominator = n1.denominator;
        } else {
            newNum.numerator = (n1.numerator * n2.denominator) + (n2.numerator * n1.denominator);
            newNum.denominator = n1.denominator * n2.denominator;
        }
        return newNum;
    }

    public static Fraction sub(Fraction n1, Fraction n2) {
        Fraction newNum = new Fraction();

        if (n1.denominator == n2.denominator) {
            newNum.numerator = n1.numerator - n2.numerator;
            newNum.denominator = n1.denominator;
        } else {
            newNum.numerator = (n1.numerator * n2.denominator) - (n2.numerator * n1.denominator);
            newNum.denominator = n1.denominator * n2.denominator;
        }

        return newNum;
    }

    public static Fraction mul(Fraction n1, Fraction n2) {
        Fraction newNum = new Fraction();

        newNum.numerator = n1.numerator * n2.numerator;
        newNum.denominator = n1.denominator * n2.denominator;

        return newNum;
    }

    public static Fraction div(Fraction n1, Fraction n2) {
        if (n2.numerator == 0) {
            return null;
        }

        Fraction newNum = new Fraction();

        newNum.numerator = n1.numerator * n2.denominator;
        newNum.denominator = n1.denominator * n2.numerator;

        return newNum;
    }

    public void isNumber() {
        if (numerator == 0) System.out.println(" = 0");
        else if (numerator * denominator < 0) System.out.println("âm");
        else System.out.println("dương");
    }
}
