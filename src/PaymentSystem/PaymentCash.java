package PaymentSystem;
import OrderingSystem.Order;
import java.util.Scanner;

public class PaymentCash {
    //fields
    private static final Scanner input = new Scanner(System.in);
    private static Order currentOrder;
    private static ReceiptPrinter printer;

    //constructor
    public PaymentCash(Order order){
        currentOrder = order;
        printer = new ReceiptPrinter(currentOrder);
    }

    //methods
    public static void payingCash(){
        double totalPrice = currentOrder.calculateOrderTotal();
        System.out.println("Total price: " + totalPrice);
        System.out.println("Please provide payment:");
        double givenBalance = input.nextDouble();
        calculate(totalPrice, givenBalance, currentOrder);
    }

    private static void calculate(double price, double balance, Order order) {
        String paymentType = "Cash";
        boolean payed = true;
        while (payed) {
            if (balance >= price) {
                balance = balance - price;
                System.out.println("Thank you, your change is: " + balance);
                //call printer for receipt
                printer.mainPrinter(paymentType, balance);
                payed = false;
            } else {
                System.out.println("Unsufficient funds to cover the cost.");
                System.out.println("Try again");
            }
        }
    }
}

