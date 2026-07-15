import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
public abstract class Payment
{
    protected Order order;
    
    public Payment(Order order){
        this.order = order;
    }

    public abstract double processPayment();

    public void generateReceipt(Customer customer){ //association
        System.out.println(" Receipt Order#" + order.getOrderID());
        System.out.println("----------------------------------------");
        System.out.println("               LUXE & Co.               ");
        System.out.println("     No. 18, Jalan Bukit Bintang 2,     ");
        System.out.println("       Bukit Bintang City Centre,       ");
        System.out.println("      55100 Kuala Lumpur, Malaysia      ");
        System.out.println("                                        ");
        System.out.println(" Customer Name : " + customer.getCustName());
        System.out.println(" Payment Method : " + getClass().getSimpleName());
        System.out.println("----------------------------------------");
        order.viewOrder();
        System.out.println("----------------------------------------");
        System.out.printf(" Sub Total:                RM%.2f\n", order.calculateSubtotal());
        System.out.printf(" Promotion:               -RM%.2f\n", order.calculateDiscount());
        System.out.printf(" Service Tax:              RM%.2f\n", order.calculateTax());
        System.out.printf(" Total:                    RM%.2f\n", order.calculateTotal());
        System.out.println("\n----------------------------------------");
    }
    
    public void saveReceipt(Customer customer){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter("receipt.txt", true));
            pw.println("Receipt #" + order.getOrderID());
            pw.println("Customer : " + customer.getCustName());
            pw.println("Payment : " + getClass().getSimpleName());
            for(int i = 0; i < order.itemCount; i++){
                pw.println(order.itemList[i].getItemName() + " x " + order.qtyList[i]);
            }
            pw.printf("Subtotal : RM%.2f%n", order.calculateSubtotal());
            pw.printf("Discount : RM%.2f%n", order.calculateDiscount());
            pw.printf("Tax      : RM%.2f%n", order.calculateTax());
            pw.printf("Total    : RM%.2f%n", order.calculateTotal());
            pw.println("--------------------------------");
            pw.println();
            pw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}