package do_exercises.phone;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class NewPhone extends Phone {
    private int quantity;

    public NewPhone() {

    }

    public NewPhone(String id, String name, BigDecimal price, LocalDate warrantyPeriod, OSPhone osPhone, String manufacturer, int quantity) {
        super(id, name, price, warrantyPeriod, osPhone, manufacturer);
        this.quantity = quantity;
    }

    @Override
    public void setId(String id) {
        super.setId("DTM" + id);
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Số lượng không được âm!");
        }
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return super.toString()  +
                "quantity=" + quantity +
                '}';
    }

    public final void inputQuantity(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập số lượng: ");
                int input = Integer.parseInt(sc.nextLine().trim());
                setQuantity(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        inputQuantity(sc);
    }

    @Override
    public BigDecimal getTotalPrice() {
        if (getPrice() == null) {
            return BigDecimal.ZERO;
        }
        return getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format("%s", quantity);
    }
}