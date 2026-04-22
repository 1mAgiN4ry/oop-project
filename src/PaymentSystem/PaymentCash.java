package PaymentSystem;
import OrderingSystem.Order;
import java.util.Scanner;

public class PaymentCash {
    //fields
    private static double totalPrice;
    private static Scanner input = new Scanner(System.in);
    private static double givenBalance;
    private static Order currentOrder;
    private static ReceiptPrinter printer;
    private static String paymentType = "Cash";

    //constructor
    public PaymentCash(Order order){
        currentOrder = order;
        printer = new ReceiptPrinter(currentOrder);
    }

    //methods
    public static void cashPaying(){
        totalPrice = currentOrder.calculateOrderTotal();
        System.out.println("Total price: " + totalPrice);
        System.out.println("Please provide payment:");
        givenBalance = input.nextDouble();
        calculate(totalPrice, givenBalance, currentOrder);
    }

    private static void calculate(double price, double balance, Order order){
            if (balance >= price){
                balance = balance - price;
                System.out.println("Thank you, your change is: " + balance);
                //call printer for receipt
                printer.mainPrinter(paymentType, balance);
        } else {
                System.out.println("Unsufficient funds to cover the cost.");
                //call printer for failed receipt
                printer.mainPrinter(paymentType, balance);
            }
     }
}
