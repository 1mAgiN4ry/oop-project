package HistoryAndTracking;

import OrderingSystem.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransactionLogger {

    /*
    things to log
    date and time
    payment type
    loyalty points
    total
    user ID
     */

    public void logTransation(Transaction transaction){
        LogPrinter logPrinter = new LogPrinter();
        List<Transaction> transactionList = logPrinter.getTransactions();
        transactionList.add(transaction);
        File Logfile = new File("src/Transactions.JSON");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Logfile, transactionList);
        } catch (IOException e) {

        }
        System.out.println(transaction);
    }





}
