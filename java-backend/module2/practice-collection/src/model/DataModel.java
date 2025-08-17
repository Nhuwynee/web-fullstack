package model;

import bean.Trader;
import bean.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataModel {
    private DataModel() {}

    // vì đề chủ yếu những câu tìm kiếm, ko thêm sửa xóa nên sử dụng List.of (immutable)
    public static List<Transaction> getTransactions() {
        Trader t1 = new Trader(1, "Trader T1", "Milan");
        Trader t2 = new Trader(2, "Trader T2", "Cambridge");
        Trader t3 = new Trader(3, "Trader T3", "London");
        Trader t4 = new Trader(4, "Trader T4", "DaNang");
        Trader t5 = new Trader(5, "Trader T5", "Cambridge");
        Trader t6 = new Trader(6, "Trader T6", "QuangTri");

        return List.of(
                new Transaction(101, t1, strToDate("30.10.2022 11:12:20"), 6200d),
                new Transaction(102, t1, strToDate("29.10.2024 12:12:20"), 1200d),
                new Transaction(103, t1, strToDate("28.10.2023 13:12:20"), 2200d),
                new Transaction(104, t2, strToDate("12.10.2021 14:12:20"), 1800d),
                new Transaction(105, t2, strToDate("22.11.2022 15:12:20"), 3456d),
                new Transaction(106, t3, strToDate("30.10.2024 18:12:20"), 1456d),
                new Transaction(107, t6, strToDate("14.10.2018 18:12:20"), 2589d),
                new Transaction(108, t4, strToDate("30.10.2024 22:12:20"), 1111d),
                new Transaction(109, t4, strToDate("25.12.2020 18:12:20"), 3698d),
                new Transaction(110, t5, strToDate("30.10.2024 14:12:20"), 1593d),
                new Transaction(111, t3, strToDate("30.11.2017 18:12:20"), 2580d),
                new Transaction(112, t2, strToDate("30.10.2024 11:12:20"), 4000d)
        );
    }

    private static LocalDateTime strToDate(String str) {
        return LocalDateTime.parse(str,
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
