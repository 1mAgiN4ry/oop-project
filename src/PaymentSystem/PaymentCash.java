package PaymentSystem;

import java.util.Random;
import java.util.Scanner;

public class PaymentCash {


    //simple cost - inputAmount class

    //fields

    /*
    * get total price of items
    * get input of how much money given
    * get compare balance against price
    * if balance larger than price subtract and return new balance
    *
    * */


    private static double[] costs = {24.65, 56.19, 6.89};
    static Scanner input = new Scanner(System.in);
    private static double givenBalance;
    //constructor


    //methods

    public void main(String[] args){
        askPayment();
        calculate();
    }

    private void askPayment(){
        System.out.println("Gib monet: ");
        givenBalance = input.nextDouble();

    }

    private void calculate(){
        for (int i = 0; i < 100; i++) {
            int b = coinFlip();
            if (givenBalance >= costs[b]) {
                System.out.println("You did it!!!");
            } else {
                System.out.println("Not enough money dummy");
            }
        }
    }

    private int coinFlip(){
        Random random = new Random();
        int coin = random.nextInt(0,3);
        return coin;
    }

}
