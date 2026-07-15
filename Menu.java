import java.util.Scanner;
import java.io.File;
public abstract class Menu
{
    protected String itemID;
    protected String itemName;
    protected double price;

    public Menu(){}
    public Menu(String itemID, String itemName, double price){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemID(){return itemID;}
    public String getItemName(){return itemName;}
    public double getPrice(){return price;}
    
    public static int getMenuCount(Menu[] menuList){
        int count = 0;
        while(count < menuList.length && menuList[count] != null){
            count++;
        }
        return count;
    }
    
    public static Menu[] loadMenu() throws Exception{
        Menu[] menuList = new Menu[100];
        File file = new File("menu.txt");
        Scanner readFile = new Scanner(file);
        int count = 0;
        while(readFile.hasNextLine()){
            String line = readFile.nextLine().trim();
            if(line.isEmpty()){
                continue;
            }
            String[] data = line.split(",");
            String id = data[0].trim();
            String item = data[1].trim();
            double price = Double.parseDouble(data[2].trim());
            String type = data[3].trim();
            if(id.startsWith("F"))
                menuList[count] = new Food(id,item,price,type);
            else if(id.startsWith("B"))
                menuList[count] = new Beverage(id,item,price,type);
            else
                menuList[count] = new Dessert(id,item,price,type);
            count++;
        }
        readFile.close();
        return menuList;
    }
    
    public abstract void displayItem();
    public abstract String getCategory();
}