import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Order
{
    protected int orderID;
    protected Menu[] itemList;
    protected int[] qtyList;
    protected int itemCount;
    protected String promoCode;
    protected double paymentDiscount;

    public Order(){
        itemList = new Menu[10];
        qtyList = new int[10];
        itemCount = 0;
    }

    public Order(int orderID){
        this.orderID = orderID;
        itemList = new Menu[10];
        qtyList = new int[10];
        itemCount = 0;
    }
    
    public void addItem(Menu menu,int qty){
        itemList[itemCount] = menu;
        qtyList[itemCount] = qty;
        itemCount++;
    }

    public void setPromoCode(String promoCode){
        this.promoCode = promoCode;
    }
    
    public void setPaymentDiscount(double paymentDiscount){
        this.paymentDiscount = paymentDiscount;
    }
    
    public int getOrderID(){return orderID;}

    public double calculateSubtotal(){
        double subtotal = 0;
        for(int i=0;i<itemCount;i++){
            subtotal += itemList[i].getPrice()
                    * qtyList[i];
        }
        return subtotal;
    }

    public double calculateDiscount(){
        if(promoCode == null || promoCode.isEmpty()){
            return paymentDiscount;
        }
        
        switch(promoCode.toUpperCase()){
            case "P1":
                return calculateSubtotal() * 0.10 + paymentDiscount;
            case "P2":
                return calculateSubtotal() * 0.20 + paymentDiscount;
            case "P3":
                return calculateSubtotal() * 0.15 + paymentDiscount;
            default:
                return paymentDiscount;
        }
    }

    public double calculateTax(){
        return (calculateSubtotal() - calculateDiscount()) * 0.06;
    }

    public double calculateTotal(){
        return calculateSubtotal() - calculateDiscount() + calculateTax();
    }

    public void saveOrder(){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter("order.txt", true));
            pw.println("Order ID : " + orderID);
            for(int i = 0; i < itemCount; i++){
                pw.println(itemList[i].getItemID() + "," +
                           itemList[i].getItemName() + "," +
                           qtyList[i]);
            }
            pw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void viewOrder(){
        System.out.printf("%-20s %-5s %-10s%n","Order","Qty","SubTotal");
        for(int i=0;i<itemCount;i++){
            double sub = itemList[i].getPrice() * qtyList[i];
            System.out.printf("%-20s %-5d RM%.2f\n", itemList[i].getItemName(), qtyList[i], sub);
        }
    }
}