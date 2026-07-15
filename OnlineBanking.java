public class OnlineBanking extends Payment
{
    public OnlineBanking(Order order){super(order);}

    public double processPayment(){
        double discount = order.calculateSubtotal() * 0.05;
        System.out.printf(" Extra Discount : RM%.2f\n",discount);
        return discount;
    } 
}