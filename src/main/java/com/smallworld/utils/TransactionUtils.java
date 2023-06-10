package com.smallworld.utils;

import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionItem;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionUtils {

    public static Transaction getUniqueTransactions(Transaction transactions) {
        List<TransactionItem> uniqueTransactions = transactions.getTransaction()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        transactions.setTransaction(uniqueTransactions);
        return transactions;
    }

    public static List<String> getUniqueClients(Transaction transactions) {
        // Extract unique names from transactions
        List<String> uniqueClients = new ArrayList<>();

        List<String> uniqueClientsSender = getDistinctSendersFromTransaction(transactions);
        List<String> uniqueClientsBenificiary = getDistinctBeneficiariesFromTransaction(transactions);

        uniqueClients.addAll(uniqueClientsSender);
        uniqueClients.addAll(uniqueClientsBenificiary);

        return uniqueClients;
    }

    public static Boolean checkOpenComplianceIssues(Transaction transaction, String name) {
        // Extract open compliance issue
        return transaction.getTransaction()
                .stream()
                .anyMatch(transactionItem -> (transactionItem.getSenderFullName().equals(name) || transactionItem.getBeneficiaryFullName().equals(name)) && !transactionItem.isIssueSolved());

    }

    public static Map<String, Transaction> getTransactionsByBeneficiaryName(Transaction transaction) {
        // Extract all transactions indexed by beneficiary name
        List<String> uniqueBeneficiaries = getDistinctBeneficiariesFromTransaction(transaction);
        Map<String, Transaction> transactionMap = new HashMap<>();
        uniqueBeneficiaries
                .forEach(client -> transactionMap.put(client, new Transaction(transaction.getTransaction()
                        .stream()
                        .filter(transactionItem -> transactionItem.getBeneficiaryFullName().equals(client))
                        .collect(Collectors.toList()))));

        return transactionMap;

    }

    public static List<String> getDistinctBeneficiariesFromTransaction(Transaction transaction) {
        return transaction.getTransaction()
                .stream()
                .map(transactions -> transactions.getBeneficiaryFullName())
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> getDistinctSendersFromTransaction(Transaction transaction) {
        return transaction.getTransaction()
                .stream()
                .map(transactions -> transactions.getSenderFullName())
                .distinct()
                .collect(Collectors.toList());
    }

    public static Set<Integer> getUnresolvedIssueIds(Transaction transaction) {
        // Extract unresolved issue ids from transaction
        return transaction.getTransaction()
                .stream()
                .filter(transactionWithUnresolvedIssue -> !transactionWithUnresolvedIssue.isIssueSolved())
                .map(unresolvedIssueId -> unresolvedIssueId.getIssueId())
                .collect(Collectors.toSet());
    }

    public static List<String> getAllSolvedIssueMessages(Transaction transaction) {
        // Extract resolved issue messages from transaction
        return transaction.getTransaction()
                .stream()
                .filter(transactionWithResolvedIssue -> (transactionWithResolvedIssue.isIssueSolved() && !StringUtils.isEmpty((transactionWithResolvedIssue.getIssueMessage()))))
                .map(transactionMessage -> transactionMessage.getIssueMessage())
                .collect(Collectors.toList());
    }

    public static Transaction getUniqueTransactionsAmountInDescendingOrder(Transaction transaction) {
        // Extract unique transaction amount from transaction
        transaction = getUniqueTransactions(transaction);
        //sorting based on amount using comparator
        transaction.setTransaction(transaction.getTransaction()
                .stream()
                .sorted((o1, o2) -> o1.getAmount() < o2.getAmount() ? 1 : -1)
                .collect(Collectors.toList()));
        return transaction;
    }

    public static List<TransactionItem> getTop3TransactionsByAmount(Transaction transaction) {
        // Extract top3 transactions by amount from transaction
        transaction = getUniqueTransactionsAmountInDescendingOrder(transaction);
        return transaction.getTransaction()
                .stream()
                .limit(3).collect(Collectors.toList());
    }

    public static double getTotalTransactionAmount(Transaction transaction) {
        // Extract total transaction amount from transaction
        transaction = getUniqueTransactions(transaction);
        return transaction.getTransaction()
                .stream()
                .mapToDouble(transactionItem -> transactionItem.getAmount())
                .sum();
    }

    public static double getTotalTransactionAmountSentBy(Transaction transactions, String senderFullName) {
        // Extract total amount sent by sender from transaction
        transactions = TransactionUtils.getUniqueTransactions(transactions);
        return transactions.getTransaction()
                .stream()
                .filter(transactionItem -> transactionItem.getSenderFullName().equals(senderFullName))
                .mapToDouble(value -> value.getAmount())
                .sum();
    }

    public static Optional<String> getTopSender(Transaction transaction) {
        //Extract senderFullName of the sender with the most total sent amount
        transaction=getUniqueTransactions(transaction);
        List<String> senders=getDistinctSendersFromTransaction(transaction);
        Map<Double,String> senderWithMaxTransactionAmount=new HashMap<>();
        Transaction finalTransaction = transaction;
        senders.forEach(sender -> senderWithMaxTransactionAmount.put(finalTransaction.getTransaction()
                .stream()
                .filter(transactionItem -> transactionItem.getSenderFullName().equals(sender))
                .mapToDouble(sentAmount -> sentAmount.getAmount())
                .sum(),sender)
        );
        Double maxAmount=senderWithMaxTransactionAmount.keySet()
                .stream()
                .sorted((o1, o2) -> o1<o2?1:-1)
                .collect(Collectors.toList()).get(0);
        return senderWithMaxTransactionAmount.get(maxAmount).describeConstable();
    }
}
