package test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionDao;
import com.smallworld.data.TransactionItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TransactionDataFetcherTest {

    private static final Logger logger = Logger.getLogger(TransactionDataFetcherTest.class.getName());
    TransactionDataFetcher transactionDataFetcher;
    Transaction transaction;
    TransactionDao transactionDao;
    @Before
    public void setUp() throws Exception {
         transactionDataFetcher=new TransactionDataFetcher();
         transactionDao=new TransactionDao();
         transaction=transactionDao.getTransactionMock();
    }

    @After
    public void tearDown() throws Exception {
        transactionDataFetcher=null;
        transactionDao=null;
        transaction=null;
    }
    @Test
    public void getTotalTransactionAmount(){
        logger.info("running test for getTotalTransactionAmount");
        Double amount=transactionDataFetcher.getTotalTransactionAmount(transaction);
        assertNotNull(amount);
        assertTrue(amount>=0);
        assertEquals(2889.17,amount,0.0);
    }
    @Test
    public void getTotalTransactionAmountSentBy(){
        logger.info("running test for getTotalTransactionAmountSentBy");
        Double amount=transactionDataFetcher.getTotalTransactionAmountSentBy(transaction,"Tom Shelby");
        assertNotNull(amount);
        assertTrue(amount>=0);
        assertEquals(678.06,amount,0.0);
    }
    @Test
    public void getMaxTransactionAmount(){
        logger.info("running test for getMaxTransactionAmount");
        Double amount=transactionDataFetcher.getMaxTransactionAmount(transaction);
        assertNotNull(amount);
        assertTrue(amount>=0);
        assertEquals(985.0,amount,0.0);
    }
    @Test
    public void countUniqueClients(){
        logger.info("running test for countUniqueClients");
        double count=transactionDataFetcher.countUniqueClients(transaction);
        assertNotNull(count);
        assertTrue(count>=0);
        assertEquals(15,count,0);
    }
    @Test
    public void hasOpenComplianceIssues(){
        logger.info("running test for hasOpenComplianceIssues");
        Boolean hasOpenComplianceIssueSender=transactionDataFetcher.hasOpenComplianceIssues(transaction,"Tom Shelby");
        Boolean hasOpenComplianceIssueBenificiary =transactionDataFetcher.hasOpenComplianceIssues(transaction,"Alfie Solomons");
        assertNotNull(hasOpenComplianceIssueSender);
        assertNotNull(hasOpenComplianceIssueBenificiary);
        assertEquals(true, hasOpenComplianceIssueBenificiary);
        assertEquals(true, hasOpenComplianceIssueSender);
    }

    @Test
    public void getTransactionsByBeneficiaryName(){
        logger.info("running test for getTransactionsByBeneficiaryName");
        Map<String,Transaction> transactionsByBeneficiaryName=transactionDataFetcher.getTransactionsByBeneficiaryName(transaction);
        assertNotNull(transactionsByBeneficiaryName);
        assertEquals(1,transactionsByBeneficiaryName.get("Alfie Solomons").getTransaction().size());
        assertEquals(2,transactionsByBeneficiaryName.get("Arthur Shelby").getTransaction().size());
    }
    @Test
    public void getUnsolvedIssueIds(){
        logger.info("running test for getUnsolvedIssueIds");
        Set<Integer> unsolvedIssueIds=transactionDataFetcher.getUnsolvedIssueIds(transaction);
        assertNotNull(unsolvedIssueIds);
        assertEquals(5,unsolvedIssueIds.size());
    }
    @Test
    public void getAllSolvedIssueMessages(){
        logger.info("running test for getUnsolvedIssueIds");
        List<String> allSolvedIssueMessages=transactionDataFetcher.getAllSolvedIssueMessages(transaction);
        assertNotNull(allSolvedIssueMessages);
        assertEquals(3,allSolvedIssueMessages.size());
    }
    @Test
    public void getTop3TransactionsByAmount(){
        logger.info("running test for getTop3TransactionsByAmount");
        List<TransactionItem> top3TransactionsByAmount=transactionDataFetcher.getTop3TransactionsByAmount(transaction);
        assertNotNull(top3TransactionsByAmount);
        assertEquals(3,top3TransactionsByAmount.size());
        assertEquals(985.0,top3TransactionsByAmount.get(0).getAmount(),0.0);
        assertEquals(666.0,top3TransactionsByAmount.get(1).getAmount(),0.0);
        assertEquals(430.2,top3TransactionsByAmount.get(2).getAmount(),0.0);
    }
    @Test
    public void getTopSender(){
        logger.info("running test for getTopSender");
        Optional<String> topSender=transactionDataFetcher.getTopSender(transaction);
        assertNotNull(topSender);
        assertEquals(topSender.get(),"Arthur Shelby");
    }

}