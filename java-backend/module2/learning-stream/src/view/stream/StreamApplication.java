package view.stream;

import bean.Transaction;
import model.DataModel;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import bean.Trader;
import static utils.CollectionUtils.*;
import static java.util.Comparator.*;

public class StreamApplication {

    public static void main(String[] args) {
        List<Transaction> transactions = DataModel.getTransactions();

        generate(
                "1. Find all transactions in the year 2024 and sort them by value (small to high)",
                transactions.stream()
                        .filter(t -> t.getCreatedTime().toLocalDate().getYear() == 2024)
                        .sorted(comparing(t -> t.getValue())) // Transaction::getValue
                        .toList()
        );

        // t -> t.getValue() --> Transaction::getValue
        // t -> t.getTrader().getCity() --> Transaction::getTrader::getCity
        // t -> t.getTraderCity() --> Transaction::getTraderCity

        generate(
                "2. Find all transactions have value greater than 300 and sort them by trader’s city",
                transactions.stream()
                        .filter(t -> t.getValue() > 300)
                        .sorted(comparing(Transaction::getTraderCity))
                        .toList()
        );

        generate(
                "3. What are all the unique cities where the traders work",
                transactions.stream()
                        .map(t -> t.getTraderCity())
                        .collect(Collectors.toSet())
        );

        generate(
                "4. Find all traders from Cambridge and sort them by name desc.",
                transactions.stream()
                        .filter(t -> "Cambridge".equals(t.getTraderCity()))
                        .map(Transaction::getTrader)
                        .sorted(comparing(Trader::getName, reverseOrder()))
                        .collect(Collectors.toSet())
        );

        // 5. Return a string of all traders’ names sorted alphabetically.
        System.out.printf(
                "5. Return a string of all traders’ names sorted alphabetically: %s\n\n",
                transactions.stream()
                        .map(t -> t.getTraderName()) // Stream<String>
                        .distinct()
                        .collect(Collectors.joining(", ", "[", "]"))
        );

        // 6. Are any traders based in Milan ?
        System.out.printf(
                "6. Are any traders based in Iran --> %s\n\n",
                transactions.stream().anyMatch(t -> "Iran".equals(t.getTraderCity()))
        );

        // 7. Count the number of traders in Milan.
        System.out.printf(
                "7. Count the number of traders in Milan --> %s\n\n",
                transactions.stream()
                        .map(Transaction::getTrader)
                        .distinct()
                        .filter(t -> "Milan".equals(t.getCity())).count()
        );

        DecimalFormat df = new DecimalFormat("#,###");
        System.out.printf(
                "8. Print all transactions’ values from the traders living in Cambridge %s\n\n",
                transactions.stream()
                        .filter(t -> "Cambridge".equals(t.getTraderCity()))
                        .map(t -> df.format(t.getValue()))
                        .collect(Collectors.joining(" "))
        );

        System.out.printf(
                "9. What’s the highest value of all the transactions --> %s\n\n",
                df.format(
                        transactions.stream()
                                .mapToDouble(Transaction::getValue)
                                .max()
                                .orElseThrow()
                )
        );

        System.out.printf(
                "10. What’s the smallest value of all the transactions --> %s",
                df.format(
                        transactions.stream()
                                .mapToDouble(Transaction::getValue)
                                .min()
                                .orElseThrow()
                )
        );
    }

}
