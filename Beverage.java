public class Beverage extends Menu
{
    private String bevType;

    public Beverage(String itemID,String itemName, double price,String bevType){
        super(itemID,itemName,price);
        this.bevType = bevType;
    }
    
    public String getType(){return bevType;}

    public void displayItem(){
        System.out.printf("%-5s %-20s %-15s RM%.2f\n",itemID,itemName,bevType,price);
    }
    
    public String getCategory(){
        return "Beverage";
    }
}