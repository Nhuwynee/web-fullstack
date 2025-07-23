package ex_afternoon.ex01;

import java.util.Scanner;

public class Number {
    int tu;
    int mau;

    public void input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Nhập tử số: ");
                tu = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập mẫu số: ");
                mau = Integer.parseInt(sc.nextLine());
                if (mau == 0) {
                    System.out.println("Mẫu số phải khác 0! Nhập lại.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Không hợp lệ! Vui lòng nhập một số nguyên.");
            }
        }
    }

    public void output() {
        System.out.println("Phân số vừa nhập: " + toString());
        System.out.println("Phân số rút gọn: " + getRutGon());
    }

    @Override
    public String toString() {
        if (mau == 1) return String.valueOf(tu);
        if (tu == 0) return "0";
        if (mau < 0) return (-tu) + "/" + (-mau);
        return tu + "/" + mau;
    }

    public String getRutGon() {
        int u = uscln(Math.abs(tu), Math.abs(mau));
        int rutTu = tu / u;
        int rutMau = mau / u;

        if (rutMau < 0) {
            rutTu = -rutTu;
            rutMau = -rutMau;
        }
        if (rutMau == 1) {
            return String.valueOf(rutTu);
        }

        return rutTu + "/" + rutMau;
    }

    public int uscln(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static Number sum(Number n1, Number n2) {
        Number newNum = new Number();

        if (n1.mau == n2.mau) {
            newNum.tu = n1.tu + n2.tu;
            newNum.mau = n1.mau;
        } else {
            newNum.tu = (n1.tu * n2.mau) + (n2.tu * n1.mau);
            newNum.mau = n1.mau * n2.mau;
        }
        return newNum;
    }

    public static Number sub(Number n1, Number n2) {
        Number newNum = new Number();

        if (n1.mau == n2.mau) {
            newNum.tu = n1.tu - n2.tu;
            newNum.mau = n1.mau;
        } else {
            newNum.tu = (n1.tu * n2.mau) - (n2.tu * n1.mau);
            newNum.mau = n1.mau * n2.mau;
        }

        return newNum;
    }

    public static Number mul(Number n1, Number n2) {
        Number newNum = new Number();

        newNum.tu = n1.tu * n2.tu;
        newNum.mau = n1.mau * n2.mau;

        return newNum;
    }

    public static Number div(Number n1, Number n2) {
        Number newNum = new Number();

        newNum.tu = n1.tu * n2.mau;
        newNum.mau = n1.mau * n2.tu;

        return newNum;
    }

    public void isNumber() {
        if (tu == 0) System.out.println(" = 0");
        else if (tu * mau < 0) System.out.println("âm");
        else System.out.println("dương");
    }
}
