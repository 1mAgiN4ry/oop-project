package PaymentSystem;
import OrderingSystem.Order;
import java.util.Scanner;

public class PaymentCard {
    //fields
    private static double test = 1;
    private static String cardNumber;
    private static String cvcNumb;
    private static String expNumb;
    private Order currentOrder;
    private static String paymentType = "Card";
    private static Scanner input = new Scanner(System.in);
    private static ReceiptPrinter printer;


    //constructor
    public PaymentCard(Order order) {
        currentOrder = order;
        printer = new ReceiptPrinter(currentOrder);
    }


    //methods
    public static void payingCard() { //rough idea of the payment method
        askCardNumber();
        askCvcNumber();
        askExpNumber();
        trans();
    }

    public static void askCardNumber() {//trying using Regex for the 16 numbers itd itd itd
        boolean done = true;
        while (done) {
            System.out.println("please enter the card number: ");
            cardNumber = input.nextLine();
            if (cardNumber.length() < 16) {
                System.out.println("not enough numbers");
                System.out.println("try again");
            } else if (cardNumber.length() == 16) {
                System.out.println("ay thanks mate");
                done = false;
            } else if (cardNumber.length() > 16) {
                System.out.println("too much numbers");
                System.out.println("try again");
            }
        }
    }

    public static void askCvcNumber() {
        boolean done = true;
        while (done) { //move all to a new method "askCvcNumber"?
            System.out.println("please enter the cvc number: ");
            cvcNumb = input.nextLine();
            if (cvcNumb.length() == 3) {
                System.out.println("nice cvc gotten");
                done = false;
            } else if (cvcNumb.length() != 3) {
                System.out.println("kill yourself");
                System.out.println("try a-fucking-gain");
            }
        }
    }

    public static void askExpNumber() {
        boolean done = true;
        while (done) {
            System.out.println("please enter the expiration number: ");
            expNumb = input.nextLine();
            if (expNumb.length() == 4) {
                System.out.println("yea thatlll do it for exp");
                done = false;
            } else if (expNumb.length() != 4) {
                System.out.println("are you fucking stupid?");
                System.out.println("again");
            }
        }
    }

    public static void trans() {
        System.out.println("Checking ballance...");
        System.out.println("Transaction completed");
        printer.mainPrinter(paymentType, test);
    }
}
