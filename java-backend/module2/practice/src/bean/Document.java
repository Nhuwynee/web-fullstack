package bean;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

public abstract class Document {
    private String id;
    private String title;
    private String publisher;
    private BigDecimal price;

    public Document() {
    }

    public Document(String id, String title, String publisher, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Giá bán phải >= 0");
        }
        this.price = price;
    }

    public void inputTitle(Scanner sc) {
        System.out.print("Nhập title: ");
        setTitle(sc.nextLine());
    }

    public void inputPublisher(Scanner sc) {
        System.out.print("Nhập nhà xuất bản: ");
        setPublisher(sc.nextLine());
    }

    public void inputPrice(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập giá bán: ");
                setPrice(new BigDecimal(sc.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi nhập giá không hợp lệ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void input(Scanner sc) {
        inputTitle(sc);
        inputPublisher(sc);
        inputPrice(sc);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,",id, title, publisher, price);
    }

    public abstract BigDecimal calculateValue();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Document document)) return false;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                '}';
    }
}
