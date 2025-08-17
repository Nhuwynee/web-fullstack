package ex_morning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class Ex05 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        // a. Chuyển chuỗi "02/28/2023" sang java.util.Date
        String inputDateStr = "02/28/2023";
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        Date dateA = sdf1.parse(inputDateStr);
        System.out.println("a. Date (java.util.Date): " + dateA);

        // b. Hiện ngày hệ thống (java.util.Date) theo định dạng dd/MM/yyyy
        Date currentDate = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("b. Ngày hiện tại (dd/MM/yyyy): " + sdf2.format(currentDate));

        // c. Chuyển "02/28/2023" sang LocalDate
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDateC = LocalDate.parse("02/28/2023", dtf1);
        System.out.println("c. LocalDate: " + localDateC);

        // d. Lấy ngày hiện tại dạng LocalDate và hiển thị theo dd/MM/yyyy
        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("d. Ngày hiện tại (LocalDate): " + today.format(dtf2));

        // e. Lấy ngày, tháng, năm, giờ, phút, giây hiện tại
        LocalDateTime now = LocalDateTime.now();
        System.out.println("e. Ngày giờ hiện tại:");
        System.out.println("   Ngày: " + now.getDayOfMonth());
        System.out.println("   Tháng: " + now.getMonthValue());
        System.out.println("   Năm: " + now.getYear());
        System.out.println("   Giờ: " + now.getHour());
        System.out.println("   Phút: " + now.getMinute());
        System.out.println("   Giây: " + now.getSecond());

        // f. Nhập ngày bất kỳ → cho biết ngày đó rơi vào thứ mấy và còn bao nhiêu ngày từ nay đến đó
        System.out.print("f. Nhập ngày (dd/MM/yyyy): ");
        String inputF = sc.nextLine();
        LocalDate inputDateF = LocalDate.parse(inputF, dtf2);
        DayOfWeek thu = inputDateF.getDayOfWeek();
        long daysBetween = ChronoUnit.DAYS.between(today, inputDateF);
        System.out.println("   Ngày đó là: " + thu);
        System.out.println("   Số ngày còn lại từ hôm nay: " + daysBetween);

        // g. Gợi ý: Nếu ngày hiện tại là 26/6, thì 1 tháng sau là 26/7 (giữ nguyên ngày)
        LocalDate exampleG = LocalDate.of(2024, 6, 26);  // ví dụ ngày 26/6
        LocalDate oneMonthLater = exampleG.plusMonths(1);
        System.out.println("g. Ngày 1 tháng sau của " + exampleG.format(dtf2) + " là: " + oneMonthLater.format(dtf2));

        // h. Nhập 2 chuỗi định dạng dd/MM/yyyy, chuyển thành LocalDate và tính số ngày chênh
        System.out.print("h. Nhập ngày thứ nhất (dd/MM/yyyy): ");
        String str1 = sc.nextLine();
        System.out.print("   Nhập ngày thứ hai (dd/MM/yyyy): ");
        String str2 = sc.nextLine();

        LocalDate d1 = LocalDate.parse(str1, dtf2);
        LocalDate d2 = LocalDate.parse(str2, dtf2);
        long chenhLech = ChronoUnit.DAYS.between(d1, d2);
        System.out.println("   Số ngày chênh lệch: " + Math.abs(chenhLech));

        sc.close();
    }
}
