package PaymentSystem;
import HistoryAndTracking.LogPrinter;
import HistoryAndTracking.TransactionLogger;
import OrderingSystem.Order;
import MenuSystem.MenuItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import LoyaltySystem.LoyaltyManager;
import OrderingSystem.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiptPrinter {
    private static final Logger log = LoggerFactory.getLogger(ReceiptPrinter.class);
    //fields
    static int space = 50;  //decides the max characters allowed per line
    static int spaceNow;    //decides amount of space between the item and the price
    static Order currentOrder;
    static LocalDateTime myDateTime;
    static double finalPrice;

    //constructor
    public ReceiptPrinter(Order order){
        currentOrder = order;
        LoyaltyManager loyaltyManager = new LoyaltyManager(currentOrder.getOrderUser());
    }

    //methods
    public static void mainPrinter(String paymentType, double balance){
        headerPrinter();
        itemPrinter(currentOrder);
        footerPrinter(currentOrder, paymentType, balance);
        Transaction transaction = new Transaction(myDateTime.toString(), currentOrder.calculateOrderTotal(), paymentType, currentOrder.getOrderUser().getUid());
        TransactionLogger transactionLogger = new TransactionLogger();
        transactionLogger.logTransation(transaction);
        LogPrinter logPrinter = new LogPrinter();
        List<Transaction> transactionList = logPrinter.getTransactions();
        System.out.println(transactionList);
    }

    public static void itemPrinter(Order order){
        for (MenuItem item : order.getOrderItems()) {
            String priceLength = Double.toString(Math.abs(item.getPrice()));
            spaceNow = space - (item.getName().length() + priceLength.length());
            System.out.print(item.getName());
            dotPrinter(spaceNow);
            System.out.println(item.getPrice());

        }

    }

    static void headerPrinter(){
        myDateTime = LocalDateTime.now(); //find and reference original code
        DateTimeFormatter myFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = myDateTime.format(myFormater);
        dashPrinter(space);
        System.out.println("Gino's Cafeteria");
        System.out.println("[address information]");
        System.out.println("Date & Time of purchase: " + formattedDate);
        dashPrinter(space);
    }

    static void footerPrinter(Order order, String paymentType, double balance){
        dashPrinter(space);
        System.out.println("Your total is: " + order.calculateOrderTotal());
        System.out.println("Payment type: " + paymentType);
        if(paymentType == "Cash"){
            System.out.println("Change given: " + balance);
        }
        LoyaltyManager.GivePoints(currentOrder.calculateOrderTotal());
        dashPrinter(space);
    }

    static void dotPrinter(int l){
        for (int i = 1 ; i <= l ; i++) {
            System.out.print(".");
        }
    }

    static void dashPrinter(int l){
        for (int i = 1 ; i <= l ; i++){
            System.out.print("=");
        }
        System.out.println();
    }
}
