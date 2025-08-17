package bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {

    private Integer id;
    private String name;
    private BigDecimal salesPrice;
    private LocalDate expiredDate;

    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(String line) {
        var tokens = line.split(", ");
        this.id = Integer.parseInt(tokens[0]);
        this.name = tokens[1];
        this.salesPrice = new BigDecimal(tokens[2]);
        this.expiredDate = LocalDate.parse(tokens[3],
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public Item(Integer id, LocalDate expiredDate) {
        this.id = id;
        this.expiredDate = expiredDate;
    }

    public Item(Integer id, String name, BigDecimal salesPrice, LocalDate expiredDate) {
        this.id = id;
        this.name = name;
        this.salesPrice = salesPrice;
        this.expiredDate = expiredDate;
    }

    public static Item init() {
        return new Item();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", salesPrice=" + salesPrice + ", expiredDate=" + expiredDate
                + "]";
    }
}