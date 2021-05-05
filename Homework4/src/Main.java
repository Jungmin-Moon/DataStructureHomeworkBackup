import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //replace string name of files with where they actually are.
        File inventoryFile = new File("E:\\NetBeansProjects\\Homework4\\src\\inventory.txt");
        File eventFile = new File("E:\\NetBeansProjects\\Homework4\\src\\event.txt");
        
        Scanner scanner = new Scanner(System.in);
        
        //System.out.println("Enter how many registers will be in this hardware store: ");
        //do while loop to make sure user creates a valid number of registers aka 1 or more. 
        int userInput;
        do {
            System.out.println("Enter how many registers will be in this hardware store: ");
            while(!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number.\n", input);
            }
            userInput = scanner.nextInt();
        } while(userInput < 1);
        
        HardwareStore store1 = new HardwareStore(userInput);
        
        scanner = new Scanner(inventoryFile);
        
        
        //ArrayList<String> tempString = new ArrayList<>();
        //ArrayList<Double> tempDouble = new ArrayList<>();
        
        /*
        scans the items file to create new items and add them to the hardware store inventory list
        */
        while(scanner.hasNextLine()) {
            String temp = scanner.next();
            int tempPrice = scanner.nextInt();
            store1.addItemToCatalog(new Item(temp, tempPrice));
            //tempString.add(temp);
            //tempDouble.add(tempPrice);
        }
        
        //store1.listItems();
        
        scanner = new Scanner(eventFile);
        
        /*
        Scans the event.txt file to read in each line 
        creating a new shopper which is always used via the first two inputs in a line
        then loops through the other aspects of the line after the line is split by space. 
        to add the items to the shoppers cart then moves to the next shopper in the file.
        */
        while(scanner.hasNextLine()) {
            String[] hold;
            String temp = scanner.nextLine();
            hold = temp.split(" ");
            
            Shopper customer = new Shopper(hold[0], hold[1]);
            
            //System.out.println(customer);
            ArrayList<Item> tempStoreInven = store1.getInventory();
         
            for(int i = 2; i < hold.length; i+=2) {
                for(int j = 0; j < tempStoreInven.size(); j++) {
                    if(tempStoreInven.get(j).getName().equalsIgnoreCase(hold[i])) {
                        customer.addItemToCart(tempStoreInven.get(j), Integer.parseInt(hold[i+1]));
                        break;
                    }
                }
            }
            store1.addShopperToLine(customer);
        }
        
        //After reading in every shopper checks them out 
        //Then posts the amount the store has made. 
        store1.checkOutAllShoppers();
        
        System.out.println("The store has made: $" + store1.totalRevenue() + " in profits.");
        
        /*
        Commented out stuff to check and make sure things are placed where they should be.
        
        Queue<Shopper> tempCheck = store1.getCustomerWaitingList();
        
        double total = 0;
        
        for(Shopper s : tempCheck) {
            total += s.amountOwed();
        } 
        
        System.out.println(total/100);
        
        
        store1.checkOutAllShoppers();
        
        System.out.println(store1.totalRevenue());
        
        
        Queue<Shopper> tempCheck = store1.getCustomerWaitingList();
        
        for(Shopper s : tempCheck) {
            System.out.println(s);
        }
        
        for(String s : itemName) {
            System.out.println(s);
        }
        
        for(Integer i : numEachItem) {
            System.out.println(i);
        } */
    }
}