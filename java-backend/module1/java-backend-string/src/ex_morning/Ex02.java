package ex_morning;

import static java.lang.Short.parseShort;

public class Ex02 {
    public static void main(String[] args) {
        String numberString = "10";
        // a
        int a = Integer.parseInt(numberString);
        System.out.println("a. " + a);
        // b
        String b = String.valueOf(a);
        System.out.println("b. " + b);
        // c
        long c = Long.parseLong(b);
        System.out.println("c. " + c);
        // d
        String d = String.valueOf(c);
        System.out.println("d. " + d);
        // e
        float e = Float.parseFloat(d);
        System.out.println("e. " + e);
        // f
        String f = String.valueOf(e);
        System.out.println("f. " + f);
        // g
        double g = Double.parseDouble(f);
        System.out.println("g. " + g);
        // h
        String h = String.valueOf(g);
        System.out.println("h. " + h);
        // i
        String ii = "1";
        short i = Short.parseShort(ii);
        System.out.println("i. " + i);
        // k
        String k = String.valueOf(i);
        System.out.println("k. " + k);
    }
}
