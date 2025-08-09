package ex_team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Schedule {
    private LocalDate day;
    private String content;

    public Schedule() {
    }

    public Schedule(LocalDate day, String content) {
        this.day = day;
        this.content = content;
    }

    public LocalDate getDay() {
        return day;
    }


    public String getContent() {
        return content;
    }


    public void input() {
        Scanner sc = new Scanner(System.in);

        // Nhập ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Nhập ngày (dd/MM/yyyy): ");
            String dayInput = sc.nextLine().trim();
            try {
                this.day = LocalDate.parse(dayInput, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ! Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }

        // Nhập nội dung
        while (true) {
            System.out.print("Nhập nội dung: ");
            this.content = sc.nextLine().trim();
            if (!this.content.isEmpty()) {
                break;
            } else {
                System.out.println("Nội dung không được để trống!");
            }
        }
        System.out.println("=> Thêm lịch dạy thành công!\n");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(day, schedule.day) && Objects.equals(content, schedule.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, content);
    }
}



