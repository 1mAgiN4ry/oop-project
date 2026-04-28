package OrderingSystem;

public class Transaction {


    private String dateAndTime;
    private double totalPrice;
    private String paymentType;
    private int id;

    public Transaction(String date, double price, String payment, int id){

        this.dateAndTime = date;
        this.totalPrice = price;
        this.paymentType = payment;
        this.id = id;


    }

    public Transaction(){

    }


    //methods

    public String getDateAndTime(){
        return dateAndTime;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public int getId(){
        return id;
    }


}
