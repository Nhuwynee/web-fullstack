package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Apple;
import bean.Dish;
import bean.Item;
import bean.Trader;
import bean.Transaction;
import common.Kind;

public class DataModel {

    private DataModel() {
    }

    public static List<Transaction> getTransactions() {
        Trader t1 = new Trader(1, "Trader T1", "Milan");
        Trader t2 = new Trader(2, "Trader T2", "Cambridge");
        Trader t3 = new Trader(3, "Trader T3", "London");
        Trader t4 = new Trader(4, "Trader T4", "DaNang");
        Trader t5 = new Trader(5, "Trader T5", "Cambridge");
        Trader t6 = new Trader(6, "Trader T6", "QuangNinh");

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

    public static Map<String, Integer> getModels() {
        Map<String, Integer> models = new HashMap<>();
        models.put("Quảng Nam", 92);
        models.put("Đà Nẵng", 43);
        models.put("Thừa Thiên Huế", 75);
        models.put("Quảng Trị", 74);
        models.put("Quảng Bình", 73);
        models.put("Quảng Ninh", 43);
        return models;
    }

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            items = Files.readAllLines(Path.of("inventory.txt"))
                    .stream()
                    .map(Item::new)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static List<Dish> getDishes() {
        return List.of(
                new Dish("1", "D1", 308d, Kind.EGG, false),
                new Dish("2", "D2", 220d, Kind.OTHER, true),
                new Dish("3", "D3", 880d, Kind.EGG, false),
                new Dish("4", "D4", 86d, Kind.FISH, false),
                new Dish("5", "D5", 22d, Kind.MEAT, false),
                new Dish("6", "D6", 707d, Kind.MEAT, false),
                new Dish("7", "D2", 101d, Kind.OTHER, true),
                new Dish("8", "D8", 292d, Kind.OTHER, true)
        );
    }

    public static List<Apple> getApples() {
        return List.of(
                new Apple(1, "red", 22d, "Thai Binh"),
                new Apple(2, "red", 18d, "Da Nang"),
                new Apple(3, "green", 40d, "Quang Nam"),
                new Apple(4, "red", 22d, "Da Nang"),
                new Apple(5, "red", 66d, "Lao Cai"),
                new Apple(6, "green", 22d, "Quang Tri"),
                new Apple(7, "red", 44d, "Da Nang"),
                new Apple(8, "yellow", 22d, "Tuyen Quang")
        );
    }

}