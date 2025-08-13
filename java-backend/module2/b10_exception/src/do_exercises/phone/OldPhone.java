package do_exercises.phone;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

public class OldPhone extends Phone implements Promotion {
    private String batteryStatus;

    public OldPhone() {}

    public OldPhone(String id, String name, BigDecimal price, LocalDate warrantyPeriod, OSPhone osPhone, String manufacturer, String batteryStatus) {
        super(id, name, price, warrantyPeriod, osPhone, manufacturer);
        this.batteryStatus = batteryStatus;
    }

    @Override
    public void setId(String id) {
        super.setId("DTC" + id);
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    @Override
    public String toString() {
        return super.toString()  +
                "batteryStatus=" + batteryStatus +
                '}';
    }

    public final void inputBatteryStatus(Scanner sc) {
        while (true) {
            System.out.print("Nhập phần trăm (%) pin (0-100): ");
            String input = sc.nextLine().trim();

            if (!input.endsWith("%")) {
                System.out.println("Vui lòng nhập kèm ký hiệu % (ví dụ: 80%)");
                continue;
            }

            String numberPart = input.substring(0, input.length() - 1);
            try {
                int percent = Integer.parseInt(numberPart);
                if (percent < 0 || percent > 100) {
                    System.out.println("Giá trị phần trăm phải từ 0 đến 100.");
                    continue;
                }
                setBatteryStatus(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 75%)");
            }
        }
    }


    @Override
    public void input(Scanner sc) {
        super.input(sc);
        inputBatteryStatus(sc);
    }

    @Override
    public BigDecimal getTotalPrice() {
        try {
            String value = batteryStatus.substring(0, batteryStatus.length() - 1);
            BigDecimal percent = new BigDecimal(value).divide(new BigDecimal("100"));
            return getPrice().multiply(percent);
        } catch (NullPointerException | NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new IllegalStateException("Trạng thái pin không hợp lệ: " + batteryStatus, e);
        }
    }

    @Override
    public BigDecimal promote(Float proVal) {
        if (proVal < 0 || proVal > 100) {
            throw new IllegalArgumentException("Phần trăm giảm giá phải từ 0 đến 100.");
        }
        BigDecimal discountPercent = BigDecimal.valueOf(proVal).divide(BigDecimal.valueOf(100));
        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discountPercent);
        return this.getPrice().multiply(discountMultiplier).setScale(0, RoundingMode.HALF_UP);
    }
}