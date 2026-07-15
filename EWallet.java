public class EWallet extends Payment
{
    public EWallet(Order order){super(order);}

    public double processPayment(){
        double subtotal = order.calculateSubtotal();
        int point = (int)(subtotal / 10);
        int discountPercent = point / 10;
        double discount = subtotal * (discountPercent / 100.0);

        System.out.println(" Points Earned : " + point);
        System.out.println(" Discount : " + discountPercent + "%");
        System.out.printf(" Discount Amount : RM%.2f\n", discount);

        return discount;
    }
}