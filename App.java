import java.util.Scanner;
import java.io.File;
import javax.swing.JOptionPane;
public class App
{
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        
        int orderID = 1;
        while(true){
            String email = JOptionPane.showInputDialog(null,
            "Email:","Log In",JOptionPane.QUESTION_MESSAGE);
            Customer customer = Customer.searchCustomer(email);
            
            if(customer != null){
                String pass = JOptionPane.showInputDialog(
                null,
                "Password:",
                "Log In",
                JOptionPane.QUESTION_MESSAGE);
                if(!customer.getCustPass().equals(pass)){
                    JOptionPane.showMessageDialog(null,
                    "Incorrect Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                String capacity = JOptionPane.showInputDialog(null,
                "Capacity:","Customer Details",JOptionPane.QUESTION_MESSAGE);
                int table = Integer.parseInt(capacity);
                customer.setTableNum(table);
            }
            else{
                String pass = JOptionPane.showInputDialog(null,
                "Password:","Register",JOptionPane.QUESTION_MESSAGE);

                JOptionPane.showMessageDialog(null,
                "Authenticating...","Restaurant Ordering System",
                JOptionPane.INFORMATION_MESSAGE);

                String name = JOptionPane.showInputDialog(null,
                "Name:","Customer Details",JOptionPane.QUESTION_MESSAGE);

                String phone = JOptionPane.showInputDialog(null,
                "Phone:","Customer Details",JOptionPane.QUESTION_MESSAGE);

                String capacity = JOptionPane.showInputDialog(null,
                "Capacity:","Customer Details",JOptionPane.QUESTION_MESSAGE);

                int table = Integer.parseInt(capacity);
        
                customer = new Customer(email, pass, name, phone, table); 
            }
            
            System.out.println("-----------------------------------------------");
            System.out.println("    Do you want to update your information?    ");
            System.out.println("-----------------------------------------------");
            System.out.println("  [1] YES");
            System.out.println("  [2] NO");
            System.out.print("  Selection : ");
            int update = input.nextInt();
            input.nextLine();
            if(update == 1){
                System.out.print("\n  Name : ");
                String newName = input.nextLine();
                System.out.print("  Phone : ");
                String newPhone = input.nextLine();
                customer.updateCustomer(newName, newPhone);
                customer.updateCustomerFile();
                System.out.println("\n  Customer information updated successfully!");
            }
            
            Menu[] menuList = Menu.loadMenu();
            int count = Menu.getMenuCount(menuList);
            
            customer.viewHomePage();
            System.out.print("\n\n========================================");
            System.out.print("\n                  MAIN                  ");
            System.out.print("\n========================================");
            System.out.print("\n  [1] MENU ");
            System.out.print("\n  [2] PROMOTIONS ");
            System.out.print("\n  Selection:  ");
            int selection = input.nextInt();
            input.nextLine();
              
            String promoCode = "";
            if(selection == 2){
                System.out.print("\n\n===============================================");
                System.out.print("\n                    PROMOTION                  ");
                System.out.print("\n===============================================");
                System.out.print("\n  [P1] Loyal Membership [Exclusive Perks]  ");
                System.out.print("\n  [P2] Dinner Prestige Set ");
                System.out.print("\n       [ Buy 2 dessert get 20% off! ] ");
                System.out.print("\n  [P3] Nocturne Dining Selection ");
                System.out.print("\n       [ Weekend dinner 15% off! ] ");
                System.out.print("\n\n  Enter Code : ");
                promoCode = input.nextLine();
                System.out.print("\n===============================================");
            }
            Order order = new Order(orderID);
            orderID++;
            order.setPromoCode(promoCode);
        
            char addMore = 'Y';    
            while(addMore == 'Y'){
                System.out.print("\n\n==================================");
                System.out.print("\n               MENU               ");
                System.out.print("\n==================================");
                System.out.print("\n  [F] Food  ");
                System.out.print("\n  [B] Beverage ");
                System.out.print("\n  [D] Dessert ");
                System.out.print("\n\n  Enter Code: ");
                char category = Character.toUpperCase(input.next().charAt(0));
                input.nextLine();
                System.out.print("==================================");
            
                if(category == 'F'){
                    System.out.println("\n\n==================================================");
                    System.out.println("                       FOOD                       ");
                    System.out.println("==================================================");
  
                    for(int i=0;i<count;i++){
                        if(menuList[i].getCategory().equalsIgnoreCase("Food")){
                            menuList[i].displayItem();
                        }
                    }
                }
            
                if(category == 'B'){
                    System.out.println("\n\n==================================================");
                    System.out.println("                     BEVERAGE                     ");
                    System.out.println("==================================================");
                
                    for(int i=0;i<count;i++){
                        if(menuList[i].getCategory().equalsIgnoreCase("Beverage")){
                            menuList[i].displayItem();
                        }
                    }
                }
              
                 if(category == 'D'){
                    System.out.println("\n\n==================================================");
                    System.out.println("                      DESSERT                     ");
                    System.out.println("==================================================");

                    for(int i=0;i<count;i++){
                        if(menuList[i].getCategory().equalsIgnoreCase("Dessert")){
                            menuList[i].displayItem();
                        }
                    }
                }
                System.out.println("--------------------------------------------------");
                System.out.print("Enter Code : ");
                String itemCode = input.nextLine();
                Menu selected = null;

                for(int i = 0 ; i < count ; i++){
                    if(menuList[i].getItemID().equalsIgnoreCase(itemCode)){
                        selected = menuList[i];
                        break;
                    }
                }

                if(selected != null){
                    System.out.print("Quantity : ");
                    int qty = input.nextInt();
                    input.nextLine();
                    order.addItem(selected, qty);
                }
                else{
                    System.out.print("\nInvalid item code.");
                }

                System.out.print("Add On [Y/N] : ");
                addMore = Character.toUpperCase(input.next().charAt(0));
                input.nextLine();
            }
        
            System.out.print("\n\n========================================");
            System.out.print("\n            Order Confirmation            ");
            System.out.println("\n========================================");
            order.viewOrder();
            System.out.print("----------------------------------------");
            System.out.printf("\n Subtotal    :             RM%.2f\n",order.calculateSubtotal());
            System.out.printf(" Discount    :             RM%.2f\n",order.calculateDiscount());
            System.out.printf(" Service Tax :             RM%.2f\n",order.calculateTax());
            System.out.printf(" Total       :             RM%.2f\n",order.calculateTotal());
            System.out.println("----------------------------------------");
            System.out.println(" [ Payment Method ]");
            System.out.println(" (1) Cash");
            System.out.println(" (2) E-Wallet");
            System.out.println(" (3) Online Banking");
            System.out.print("----------------------------------------");
            System.out.print("\n Payment Code : ");
            int pay = input.nextInt();
            input.nextLine();

            Payment payment = null;
            switch(pay){
                case 1:
                    payment = new Cash(order);
                    break;
                case 2:
                    payment = new EWallet(order);
                    break;
                case 3:
                    payment = new OnlineBanking(order);
                    break;
                default:
                    System.out.print("\nInvalid payment method.");
                    continue;
            }
            double paymentDiscount = payment.processPayment();
            order.setPaymentDiscount(paymentDiscount);
         
            System.out.printf(" Confirm payment of RM%.2f? [Y/N] : ",order.calculateTotal());
            char confirm = Character.toUpperCase(input.next().charAt(0));
            input.nextLine();
            System.out.println("\n >> AUTHORIZING WITH GATEWAY....");
            System.out.println(" >> SUCCESS! Order Created.");
            System.out.print("========================================");
            if(confirm == 'Y'){
                order.saveOrder();
                customer.saveCustomer();
                payment.saveReceipt(customer);
                
                System.out.println("\n\n\n----------------------------------------");
                payment.generateReceipt(customer);
                System.out.println("************** THANK YOU! **************");
            
                if(payment instanceof Cash){
                    System.out.println("       Please Pay At The Counter.       ");
                }
            }
            else{
                System.out.println("\n\nOrder Cancelled.");
            }
        }
    }
}      