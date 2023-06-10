package com.smallworld.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class TransactionDao {
    /**TransactionDao is created for making the logic based on requirements and test cases,*/
    public Transaction getTransactionMock()  {
        Transaction transaction=new Transaction();
        TransactionItem[] transactionItems=null;
        try {
            File data=new File("transactions.json");
            ObjectMapper objectMapper = new ObjectMapper();
             transactionItems = objectMapper.readValue(data, TransactionItem[].class);

        }catch (Exception e){
            e.printStackTrace();
        }
        transaction.setTransaction(Arrays.asList(transactionItems));
        return transaction;
    }

}
