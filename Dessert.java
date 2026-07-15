public class Dessert extends Menu
{
    public String dessertType;
    
    public Dessert(String itemID,String itemName,double price,String dessertType){
        super(itemID,itemName,price);
        this.dessertType = dessertType;
    }
    
    public void displayItem(){
        System.out.printf("%-5s %-20s %-15s RM%.2f\n", itemID,itemName,dessertType,price);
    }
    
    public String getCategory(){return "Dessert";}
}