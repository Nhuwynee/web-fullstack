package ex_morning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        String str = "02/28/2023";
        // a.
        SimpleDateFormat strFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date str2 = strFormat.parse(str);
            System.out.println("a. " + str2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // b.
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("b. " + sdf2.format(date));

        // c.
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        LocalDate lc = LocalDate.parse(str, dt); 
        System.out.println("c. " + lc);

        // d.
        LocalDate lcDate = LocalDate.now();
        System.out.println("d. " + dt.format(lcDate));

        // e.
        System.out.println("e. " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        // f. Một tháng sau là ngày nào? thứ mấy? cách bao nhiêu ngày?
        LocalDateTime oneMonthLater = LocalDateTime.now().plusMonths(1);
        System.out.println("f. 1 tháng sau là: " + oneMonthLater.getDayOfMonth() + "/" + oneMonthLater.getMonthValue() + "/" + oneMonthLater.getYear());
        System.out.println("f. Thứ của 1 tháng sau là: " + oneMonthLater.getDayOfWeek());

        // Đếm số ngày bằng vòng lặp
        LocalDateTime temp1 = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime temp2 = oneMonthLater.toLocalDate().atStartOfDay();
        int count = 0;
        while (temp1.isBefore(temp2)) {
            temp1 = temp1.plusDays(1);
            count++;
        }
        System.out.println("   Cách hiện tại: " + count + " ngày");

        // g. Cách đây 1000 ngày là ngày nào?
        LocalDateTime thousandDaysAgo = LocalDateTime.now();
        for (int i = 0; i < 1000; i++) {
            thousandDaysAgo = thousandDaysAgo.minusDays(1);
        }
        System.out.println("g. 1000 ngày trước: " + thousandDaysAgo.getDayOfMonth() + "/" + thousandDaysAgo.getMonthValue() + "/" + thousandDaysAgo.getYear());

        // h. Nhập 2 ngày, tính chênh lệch bằng vòng lặp
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ngày 1 (dd/MM/yyyy): ");
        String str1 = scanner.nextLine();
        System.out.print("Nhập ngày 2 (dd/MM/yyyy): ");
        String str2 = scanner.nextLine();

        LocalDate d1 = LocalDate.parse(str1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate d2 = LocalDate.parse(str2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int diff = 0;
        if (d1.isBefore(d2)) {
            while (d1.isBefore(d2)) {
                d1 = d1.plusDays(1);
                diff++;
            }
        } else {
            while (d2.isBefore(d1)) {
                d2 = d2.plusDays(1);
                diff++;
            }
        }

        System.out.println("h. Số ngày chênh lệch: " + diff + " ngày");

    }
}
