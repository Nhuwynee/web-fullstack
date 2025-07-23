package ex_afternoon;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Ex06
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
        LocalDate startDate = LocalDate.parse(sc.nextLine(), formatter);
        if(startDate.isAfter(LocalDate.now().plusDays(-1))){
            System.out.println("Lỗi! Vui lòng nhập ngày bắt đầu bé hơn ngày hiện tại:" + LocalDate.now().format(formatter));
            return;
        }
        System.out.print("Nhập ngày kết thúc(dd/MM/yyyy): ");
        LocalDate currentDate = LocalDate.parse(sc.nextLine(), formatter);

        if(currentDate.isAfter(LocalDate.now())){
            System.out.println("Lỗi! Vui lòng nhập ngày kết thúc bé hơn hoặc bằng ngày hiện tại:" + LocalDate.now().format(formatter));
            return;
        }

        if(startDate.isAfter(currentDate)){
            System.out.println("Lỗi! Vui lòng nhập ngày bắt đầu '" +startDate.format(formatter) +"' bé hơn hoặc bằng ngày kết thúc:'"+currentDate.format(formatter)+"'");
            return;
        }
        // 1. Tính số ngày làm việc
        int workDays = 0;
        LocalDate tempDate = startDate;
        while (!tempDate.isAfter(currentDate)) {
            DayOfWeek day = tempDate.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                workDays++;
            }
            tempDate = tempDate.plusDays(1);
        }

        // 2. Tổng số tuần
        long totalDays = ChronoUnit.DAYS.between(startDate, currentDate) + 1;
        long totalWeeks = totalDays / 7;

        // 3. Danh sách Thứ 2
        List<LocalDate> mondayList = new ArrayList<>();

        // tìm ngày thứ 2 đầu tiên kể từ ngày bắt đầu
        LocalDate monday = startDate;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.plusDays(1);
        }

        // tìm các ngày thứ 2 từ ngày bắt đầu đến ngày hiện tại
        while (!monday.isAfter(currentDate)) {
            mondayList.add(monday);
            monday = monday.plusWeeks(1);
        }

        // 4.kết quả
        System.out.println("Số ngày làm việc: " + workDays);
        System.out.println("Tổng số tuần: " + totalWeeks);
        System.out.println("Danh sách các Thứ 2:");
        for (LocalDate mondayItem : mondayList) {
            System.out.println(mondayItem.format(formatter));
        }

    }
}
