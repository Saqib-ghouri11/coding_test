package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionItem;
import com.smallworld.utils.TransactionUtils;

import java.util.*;
import java.util.logging.Logger;

public class TransactionDataFetcher {

    private static final Logger logger = Logger.getLogger(TransactionDataFetcher.class.getName());

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount(Transaction transaction) {
        logger.info("inside getTotalTransactionAmount");
        double sum = TransactionUtils.getTotalTransactionAmount(transaction);;
        logger.info("sum of the amounts of all transactions is : " + sum);
        return sum;

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(Transaction transactions, String senderFullName) {
        logger.info("inside getTotalTransactionAmountSentBy");
        double totalAmount = TransactionUtils.getTotalTransactionAmountSentBy(transactions,senderFullName);
        logger.info("sum of the amounts of all transactions sent by the specified client is : " + totalAmount);
        return totalAmount;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount(Transaction transaction) {
        logger.info("inside getMaxTransactionAmount");
        transaction = TransactionUtils.getUniqueTransactionsAmountInDescendingOrder(transaction);
        double maxAmnt = transaction.getTransaction().get(0).getAmount();
        logger.info("highest transaction amount is : " + maxAmnt);
        return maxAmnt;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients(Transaction transaction) {
        logger.info("inside countUniqueClients");
        List<String> uniqueClients = TransactionUtils.getUniqueClients(transaction);
        long uniqueClientsCount = uniqueClients.size();
        logger.info("number of unique clients that sent or received a transaction is : " + uniqueClientsCount);
        return uniqueClientsCount;
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(Transaction transaction, String clientFullName) {
        logger.info("inside hasOpenComplianceIssues");
        boolean hasOpenComplianceIssue = TransactionUtils.checkOpenComplianceIssues(transaction, clientFullName);
        logger.info("client has transaction with a compliance issue that has not been solved : " + hasOpenComplianceIssue);
        return hasOpenComplianceIssue;

    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName(Transaction transaction) {
        logger.info("inside getTransactionsByBeneficiaryName");
        return TransactionUtils.getTransactionsByBeneficiaryName(transaction);
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds(Transaction transaction) {
        logger.info("inside getUnsolvedIssueIds");
        Set<Integer> unresolvedIssueIds = TransactionUtils.getUnresolvedIssueIds(transaction);
        return unresolvedIssueIds;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages(Transaction transaction) {
        logger.info("inside getAllSolvedIssueMessages");
        List<String> allSolvedIssueMessages = TransactionUtils.getAllSolvedIssueMessages(transaction);
        return allSolvedIssueMessages;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<TransactionItem> getTop3TransactionsByAmount(Transaction transaction) {
        logger.info("inside getTop3TransactionsByAmount");
        return TransactionUtils.getTop3TransactionsByAmount(transaction);
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender(Transaction transaction) {
        logger.info("inside getTopSender");
        Optional<String> topSender=TransactionUtils.getTopSender(transaction);
        logger.info("senderFullName of the sender with the most total sent amount is : " + topSender.get());
        return topSender;
    }

}
