package view;

import bean.Transaction;
import functional.TransactionPredicate;
import model.DataModel;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TransactionApp {

    // Class
    // Trader: id, name, city
    // Transaction: id, trader, createdTime, value

    private static List<Transaction> transactions = DataModel.getTransactions();

    public static void main(String[] args) {

        // 1.
        // Cách 1
        generate("C1: 1. Find all transactions in the year 2024 and sort them by value (small to high).",
//      Bị xám: findTransaction(2024, (t1, t2) -> t1.getValue().compareTo(t2.getValue())));
                findTransaction(2024, Comparator.comparing(Transaction::getValue)));

        // Cách 2: dùng stream
        generate("C2: 1. Find all transactions in the year 2024 and sort them by value (small to high).",
                transactions.stream()
                        .filter(t -> t.getCreatedTime().getYear() == 2024)
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .toList()
        );

        // Cách 3: Functional Interface (tự tạo) cho điều kiện bên trong
        //          hoặc dùng Functional có sẵn trong Java -> Predicate (truyền T trả về boolean)
        generate("C3: 1. Find all transactions in the year 2024 and sort them by value (small to high).",
                findTransaction(
                        t -> t.getCreatedTime().getYear() == 2024,
                        (t1, t2) -> t1.getValue().compareTo(t2.getValue()))
        );

        // 2.
        generate("2. Find all transactions have value greater than 2000 and sort them by trader’s city",
                findTransaction(
                        t -> t.getValue() > 2000,
                        (t1, t2) -> t1.getTrader().getCity().compareTo(t2.getTrader().getCity()))
        );

        // 3. What are all the unique cities where the traders work ?
        generate(
                "3. What are all the unique cities where the traders work ?",
                new HashSet<>(transform(transactions, t -> t.getTrader().getCity()))
        );

        // 4. Find all traders from Cambridge and sort them by name desc.
        var cbrTransactions1 = findTransaction(t -> t.getTrader().getCity().equals("Cambridge"),
                (t1, t2) -> t2.getTrader().getName().compareTo(t1.getTrader().getName()));

        generate(
                " 4. Find all traders from Cambridge and sort them by name desc.",
                new LinkedHashSet<>(transform(cbrTransactions1, t -> t.getTrader()))
        );

        // 5. Return a string of all traders’ names sorted alphabetically.
        var tNames = new TreeSet<>(transform(transactions, t -> t.getTrader().getName()));
        System.out.printf(
                "5. Return a string of all traders’ names sorted alphabetically: %s\n\n",
                String.join(", ", tNames)
        );
        // 6. Are any traders based in Milan ?
        System.out.printf("6. Are any traders based in Milan ? --> %s\n\n",
                test(t -> "Milan".equalsIgnoreCase(t.getTrader().getCity())));

        // 7. Count the number of traders in Milan.
        System.out.printf("7 Count the number of traders in Milan --> %s\n\n",
                count(t -> t.getTrader().getCity().equals("Milan")));

        // 8. Print all transactions’ values from the traders living in Cambridge.
        var cbrTransactions = findTransaction(t -> t.getTrader().getCity().equals("Cambridge"));
        generate(
                "8. Print all transactions’ values from the traders living in Cambridge.",
                transform(cbrTransactions, t -> t.getValue())
        );

        // 9. What’s the highest value of all the transactions ?
        // System.out.printf("9. What’s the highest value of all the transactions --> %s\n\n", max(transactions));

        // System.out.printf("10. Find the transaction with the smallest value --> %s\n\n", min(transactions));

        //System.out.printf("10. Find the transaction with the smallest value --> %s\n\n",
        //		reduce(transactions, Double.MIN_VALUE, (v1, v2) -> v1 < v2));

        System.out.printf("9. What’s the highest value of all the transactions --> %s\n\n",
                reduce(transactions, Double.MIN_VALUE, (v1, v2) -> v1 > v2 ? v1 : v2));

        System.out.printf("10. Find the transaction with the smallest value --> %s\n\n",
                reduce(transactions, Double.MAX_VALUE, (v1, v2) -> v1 < v2 ? v1 : v2));

    }

    /**     2 hàm câu 1 và 2 chung logic, chỉ khác ở chỗ:
     - transaction.getCreatedTime().getYear() == year
     - transaction.getValue() > value
     => Sử dụng Strategy Pattern:
     - Tạo ra Functional Interface: boolean test(Traction transaction)
     */

    // 9. 10.
    private static Double reduce(List<Transaction> transactions, Double initial, BinaryOperator<Double> operator) {
        Double result = initial;

        for (Transaction transaction: transactions) {
            result = operator.apply(result, transaction.getValue());
        }

        return result;
    }

    // 7.
    private static int count(TransactionPredicate predicate) {
        var result = findTransaction(predicate);
        return Set.copyOf(transform(result, t -> t.getTrader())).size();
    }

    // 1
    private static List<Transaction> findTransaction(int year, Comparator<Transaction> comparator) {
        List<Transaction> result = new ArrayList<>();

        for (var transaction : transactions) {
            if (transaction.getCreatedTime().getYear() == year) {
                result.add(transaction);
            }
        }

        result.sort(comparator);

        return result;
    }

    // 2
    private static List<Transaction> findTransaction(Double value, Comparator<Transaction> comparator) {
        List<Transaction> result = new ArrayList<>();

        for (var transaction : transactions) {
            if (transaction.getValue() > value) {
                result.add(transaction);
            }
        }

        result.sort(comparator);

        return result;
    }

    // Sau khi tạo Functional Interface để tối ưu cho logic câu 1, 2
    private static List<Transaction> findTransaction(TransactionPredicate predicate, Comparator<Transaction> comparator) {
        List<Transaction> result = new ArrayList<>();

        for (var transaction : transactions) {
            if (predicate.test(transaction)) {
                result.add(transaction);
            }
        }

        if (comparator != null) {
            result.sort(comparator);
        }

        return result;
    }

    // hàm dành cho các câu ko sort
    private static List<Transaction> findTransaction(TransactionPredicate predicate) {
        return findTransaction(predicate, null);
    }

    // hàm dành cho lấy ra thuộc tính trong Transaction chứ kh phải list Transaction
    // interface Function (truyền T trả về R)
    private static <R> List<R> transform(List<Transaction> trans, Function<Transaction, R> transformer) {
        List<R> result = new ArrayList<>();

        for (var t : trans) {
            result.add(transformer.apply(t));
        }

        return result;
    }

    // 6.
    private static boolean test(TransactionPredicate predicate) {
        for (var transaction : transactions) {
            if (predicate.test(transaction)) {
                return true;
            }
        }
        return false;
    }

    private static <T> void generate(String question, Collection<T> elements) {
        System.out.println(question + " --> {");
        elements.forEach(element -> System.out.println("  + " + element));
        System.out.println("}\n");
    }
}

