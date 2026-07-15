public class Food extends Menu
{
    private String cuisine; 
    
    public Food(String itemID,String itemName,double price,String cuisine){
        super(itemID,itemName,price);
        this.cuisine = cuisine;
    }
    
    public String getType(){return cuisine;}
    
    public void displayItem(){
        System.out.printf("%-5s %-20s %-15s RM%.2f\n", itemID,itemName,cuisine,price);
    }
    
    public String getCategory(){
        return "Food";
    }
}