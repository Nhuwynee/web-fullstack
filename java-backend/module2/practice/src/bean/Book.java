package bean;

import java.math.BigDecimal;
import java.util.Scanner;

public class Book extends Document {
    private String author;
    private int pageCount;

    public Book() {
    }

    public Book(String id, String title, String publisher, BigDecimal price, String author, int pageCount) {
        super(id, title, publisher, price);
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public void setId(String id) {
        super.setId("B" + id);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) throws IllegalArgumentException {
        if (pageCount < 0) {
            throw new IllegalArgumentException("Số trang phải > 0");
        }
        this.pageCount = pageCount;
    }

    public void inputAuthor(Scanner sc) {
        System.out.print("Nhập tác giả: ");
        setAuthor(sc.nextLine());
    }

    public void inputPageCount(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập số trang: ");
                int page = Integer.parseInt(sc.nextLine());
                setPageCount(page);
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
        inputAuthor(sc);
        inputPageCount(sc);
    }
    @Override
    public String toCSV() {
        return super.toCSV() + String.format("%s,%s", author, pageCount);
    }

    @Override
    public BigDecimal calculateValue() {
        return getPrice().multiply(BigDecimal.valueOf(0.8));
    }

    @Override
    public String toString() {
        return super.toString() + "Book{" +
                "author='" + author + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}
