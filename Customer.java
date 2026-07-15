import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
public class Customer
{
    private String custEmail;
    private String custPass;
    private String custName;
    private String custPhone;
    private int tableNum;
    
    public Customer(){}

    public Customer(String custEmail, String custPass, String custName, String custPhone, int tableNum){
        this.custEmail = custEmail;
        this.custPass = custPass;
        this.custName = custName;
        this.custPhone = custPhone;
        this.tableNum = tableNum;
    }

    public String getCustName(){return custName;}
    public String getCustPass(){return custPass;}

    public void setTableNum(int tableNum){this.tableNum = tableNum;}
    
    public void viewHomePage(){
        System.out.println("\n  Welcome back, " + custName + "!");
    }
    
    public void updateCustomer(String newName, String newPhone){
        custName = newName;
        custPhone = newPhone;
    }
    
    public void updateCustomerFile(){
        try{
            File file = new File("customer.txt");
            Scanner read = new Scanner(file);

            StringBuilder data = new StringBuilder();

            while(read.hasNextLine()){
                String line = read.nextLine();
                if(line.trim().isEmpty()){
                    continue;
                }
                
                String[] temp = line.split(",");
                if(temp[0].equalsIgnoreCase(custEmail)){
                    data.append(custEmail).append(",").append(custPass).append(",")
                    .append(custName).append(",").append(custPhone).append("\n");
                }
                else{
                    data.append(line).append("\n");
                }
            }
            read.close();
        
            PrintWriter pw = new PrintWriter(new FileWriter("customer.txt"));
            pw.print(data.toString());
            pw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void saveCustomer(){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter("customer.txt", true));

            System.out.print("\n\n========================================");
            System.out.print("\n            Customer Details            ");
            System.out.print("\n========================================");
            System.out.print("\n  Email: " + custEmail);
            System.out.print("\n  Password: " + custPass);
            System.out.print("\n  Name: " + custName);
            System.out.print("\n  Phone: " + custPhone);
            System.out.print("\n  Capacity: " + tableNum);
            System.out.print("\n----------------------------------------");
            
            pw.println(custEmail + "," + custPass + "," + custName + "," + custPhone);
            pw.close();
        }
        catch(IOException e){
            System.out.print("\nError: " + e.getMessage());
        }
    }
    
    public static Customer searchCustomer(String email){
        try{
            Scanner read = new Scanner(new File("customer.txt"));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] data = line.split(",");
                if(data[0].equalsIgnoreCase(email)){
                    read.close();
                    return new Customer(data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),0);
                }
            }
            read.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}