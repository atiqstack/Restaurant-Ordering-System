public class Cash extends Payment
{
    public Cash(Order order){super(order);}
    
    public double processPayment(){
        System.out.println(" Please pay at the counter.");
        return 0;
    }
}