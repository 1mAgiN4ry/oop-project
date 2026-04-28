package PaymentSystem;
import OrderingSystem.Order;
import java.util.Scanner;

public class PaymentCard {
    private static final Scanner input = new Scanner(System.in);
    static ReceiptPrinter printer;

    //constructor
    public PaymentCard(Order order) {
        printer = new ReceiptPrinter(order);
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
            System.out.println("Please enter the card number: ");
            String cardNumber = input.nextLine();
            if (cardNumber.length() < 16) {
                System.out.println("Card number length insufficient");
                System.out.println("Try again");
            } else if (cardNumber.length() == 16) {
                System.out.println("Card number accepted");
                done = false;
            } else {
                System.out.println("Card number length too long");
                System.out.println("Try again");
            }
        }
    }

    public static void askCvcNumber() {
        boolean done = true;
        while (done) { //move all to a new method "askCvcNumber"?
            System.out.println("Please enter the CVC number: ");
            String cvcNumb = input.nextLine();
            if (cvcNumb.length() == 3) {
                System.out.println("CVC number accepted");
                done = false;
            } else {
                System.out.println("CVC number unaccepted");
                System.out.println("CVC number should be the length of 3");
            }
        }
    }

    public static void askExpNumber() {
        boolean done = true;
        while (done) {
            System.out.println("Please enter the expiration number: ");
            String expNumb = input.nextLine();
            if (expNumb.length() == 4) {
                System.out.println("Expiration number accepted");
                done = false;
            } else {
                System.out.println("Unaccepted date");
                System.out.println("Try again");
            }
        }
    }

    public static void trans() {
        System.out.println("Checking balance...");
        System.out.println("Transaction completed");
        double test = 1;
        String paymentType = "Card";
        printer.mainPrinter(paymentType, test);
    }
}
