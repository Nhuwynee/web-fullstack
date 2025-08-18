package bean;

import java.math.BigDecimal;
import java.util.Scanner;

public class Magazine extends Document {
    private int issueNumber;
    private int month;

    public Magazine() {
    }

    public Magazine(String id, String title, String publisher, BigDecimal price, int issueNumber, int month) {
        super(id, title, publisher, price);
        this.issueNumber = issueNumber;
        this.month = month;
    }

    @Override
    public void setId(String id) {
        super.setId("M" + id);
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber < 0) {
            throw new IllegalArgumentException("Số phát hành phải > 0");
        }
        this.issueNumber = issueNumber;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 0 || month > 12) {
            throw new IllegalArgumentException("Số tháng phải > 0 và < 12");
        }
        this.month = month;
    }

    public void inputMonth(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập số tháng: ");
                int month = Integer.parseInt(sc.nextLine());
                setMonth(month);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void inputIssueNumber(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập số phát hành: ");
                int page = Integer.parseInt(sc.nextLine());
                setIssueNumber(page);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }}
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        inputMonth(sc);
        inputIssueNumber(sc);
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format("%s,%s", issueNumber, month);
    }

    @Override
    public BigDecimal calculateValue() {
        return getPrice().multiply(BigDecimal.valueOf(0.6));
    }


    @Override
    public String toString() {
        return super.toString()  + "Magazine{" +
                "issueNumber=" + issueNumber +
                ", month=" + month +
                '}';
    }
}
