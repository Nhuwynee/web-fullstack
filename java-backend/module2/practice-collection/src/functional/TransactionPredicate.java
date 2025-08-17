package functional;

import bean.Transaction;

@FunctionalInterface
public interface TransactionPredicate {
    boolean test(Transaction transaction);
}
