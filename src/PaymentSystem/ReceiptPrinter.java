package PaymentSystem;
import OrderingSystem.Order;
import MenuSystem.MenuItem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import LoyaltySystem.LoyaltyManager;

public class ReceiptPrinter {
    //fields
    static int space = 40;  //decides the max characters allowed per line
    static int spaceNow;    //decides amount of space between the item and the price
    static Order currentOrder;

    //constructor
    public ReceiptPrinter(Order order){
        currentOrder = order;
    }

    //methods
    public static void mainPrinter(String paymentType, double balance){
        headerPrinter();
        itemPrinter(currentOrder);
        footerPrinter(currentOrder, paymentType, balance);
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
        LocalDateTime myDateTime = LocalDateTime.now(); //find and reference original code
        DateTimeFormatter myFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = myDateTime.format(myFormater);
        dashPrinter(space);
        System.out.println("ginoes pizzzaaaaaaaaaaaaaaaa");
        System.out.println("adress informationen");
        System.out.println("Date and tioum of purchase: " + formattedDate);
        dashPrinter(space);
    }

    static void footerPrinter(Order order, String paymentType, double balance){
        dashPrinter(space);
        System.out.println("Your total is: " + order.calculateOrderTotal());
        System.out.println("Payment type: " + paymentType);
        if(paymentType == "Cash"){
            System.out.println("Change given: " + balance);
        }
        LoyaltyManager.GivePoints(balance);
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
