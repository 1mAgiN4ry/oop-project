package PaymentSystem;

import java.util.Random;
import java.util.Scanner;

public class PaymentCard { //pardon the rudeness and insanity for now c:
    //fields
    private static long cardNumb;
    private static int cardNumbLength;
    private static Integer cvcNumb;
    private static Integer expNumb;
    private static String userName;
    private static int currentBalance = 100;
    private static double total;

    static Scanner input = new Scanner(System.in);

    //temporary variables for testing

    private static double tempTotal1 = 99.23;
    private static double tempTotal2 = 129.57;


    //trying using Regex for the 16 numbers itd itd itd

    //constructor
    public PaymentCard() {

    }
    //methods

    public static void main(String[] args){
            int output = diceRoll();
            if (output == 1) {
                total = tempTotal1;
            }
            if (output == 2) {
                total = tempTotal2;
            }
            System.out.println(total);

            payingCard();


    }

    public static void payingCard(){ //rough idea of the payment method
        boolean did = true;
        while (did) { //move all to a new method "askCardNumber"?
            ask();
            if (cardNumbLength < 16) {
                System.out.println("not enough numbers");
                System.out.println("try again");
            } else if (cardNumbLength == 16) {
                System.out.println("ay thanks mate");
                did = false;
            } else if (cardNumbLength > 16) {
                System.out.println("too much numbers");
                System.out.println("try again");
            }
        }
        boolean did2 = true;
        while (did2){ //move all to a new method "askCvcNumber"?
            ask2();
            if (cvcNumb.toString().length() == 3){
                System.out.println("nice cvc gotten");
                did2 = false;
            } else if (cvcNumb.toString().length() != 3) {
                System.out.println("kill yourself");
                System.out.println("try a-fucking-gain");
            }
        }
        boolean did3 = true;
        while (did3) { //move all to a new method "askExpNumber"?
            ask3();
            if (expNumb.toString().length() == 3) {
                System.out.println("yea thatlll do it for exp");
                did3 = false;
            } else if (expNumb.toString().length() != 3) {
                System.out.println("are you fucking stupid?");
                System.out.println("again");
            }
        }
        System.out.println("Checking ballance...");
        if (currentBalance >= total){
            System.out.println("Transaction completed");
        }
        if (currentBalance < total){
            System.out.println("Not enough funds");
        }



        System.out.println("okay that it, go away now"); //just a message letting me know all the code ran
    }


    private static void ask(){
        System.out.println("please enter the card number: ");
        cardNumb = input.nextLong();
        cardNumbLength = getIntegerLength(cardNumb);
    }


    private static void ask2(){
        System.out.println("please enter the cvc number: ");
        cvcNumb = input.nextInt();
    }

    private static void ask3(){
        System.out.println("please enter the expiration number: ");
        expNumb = input.nextInt();
    }

    private static int diceRoll(){
        Random random = new Random();
        int roll = random.nextInt(1, 3);
        System.out.println(roll);
        return roll;
    }

    private static int getIntegerLength(Long number) {
        if (number == null) {
            return 0;
        }
        return number.toString().length();
    }


}
