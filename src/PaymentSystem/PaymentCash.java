package PaymentSystem;

import OrderingSystem.Order;


import java.util.Scanner;

public class PaymentCash {


    //simple cost - inputAmount class

    //fields

    static double totalPrice;
    static Scanner input = new Scanner(System.in);
    private static double givenBalance;


    /*
    * get total price of items
    * get input of how much money given
    * get compare balance against price
    * if balance larger than price subtract and return new balance
    *
    * should the classes have a private variable
    * for the variables they need to from other classes
    * or should they call them in the methods and not keep them anywhere?
    *
    *
    * */


    //constructor
    public void PaymentCard(){

    }

    //methods


    private void askPayment(){
        System.out.println("Gib monet: ");
        givenBalance = input.nextDouble();

    }

    public static void cashPaying(Order order){
        totalPrice = order.calculateOrderTotal();
        System.out.println("Total price: " + totalPrice);
        System.out.println("Please provide payment:");
        givenBalance = input.nextDouble();
        calculate(totalPrice, givenBalance);



        // get price, show price, ask for payment, get payment,
        // compare payment [if enough subtract total from given, return given]
        // [if not tell not enough and try again / print failure]
        // print receipt
        //
        // note before work (wed 25.3) somehow find out how to connect it in the bigger system
        // after selecting desired items maybe switch for payment, ask what payment type then call payment type class
        // or alt ask others for ideas
        // also work on card and start printer

    }

    private static void calculate(double price, double balance){
            if (balance >= price){
                balance = balance - price;
                System.out.println("Thank you, your change is: " + balance);
                //call printer for receipt
        } else {
                System.out.println("Unsufficient funds to cover the cost.");
                //call printer for failed receipt
            }
     }
}
