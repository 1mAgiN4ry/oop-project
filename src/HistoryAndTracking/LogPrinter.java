package HistoryAndTracking;

import OrderingSystem.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class LogPrinter {






    public List<Transaction> getTransactions(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream("src/Transactions.JSON");
            List<Transaction> transactionList = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));

            return transactionList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
