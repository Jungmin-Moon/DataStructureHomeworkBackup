
import java.util.*;



public class HardwareStore {
    
    int numRegisters;
    LinkedList<Queue<Shopper>> registers;
    Queue<Shopper> waitingShoppers;
    ArrayList<Item> itemCatalog;
    double revenueInCents;
    
    
    //Creates a store with some numRegisters number of registers. 
    //then creates a linkedList of registers that is a Queue inside a Queue
    //create a priority queue of waiting shoppers 
    //then call createRegisters() to fill the registers Queue of Queues with the 
    //amount of registers as stated by numRegisters 
    //also create a revenue check in cents and set it to 0
    HardwareStore(int numRegisters) {
        this.numRegisters = numRegisters;
        registers = new LinkedList<>();
        waitingShoppers = new PriorityQueue<>();
        createRegisters();
        itemCatalog = new ArrayList<>();
        revenueInCents = 0.;
    }
    
    //adds the items read from file after being made into the catalog
    public void addItemToCatalog(Item item) {
        itemCatalog.add(item);
    }
    
    //The next two are just to get the specified lists to manipulate or use. 
    public ArrayList getInventory() {
        return itemCatalog;
    }
    
    public Queue getCustomerWaitingList() {
        return waitingShoppers;
    }
    
    //a way to print items in the store if need be
    public void listItems() {
        for(Item i : itemCatalog) {
            System.out.println(i);
        }
    }
    
    //depending on numRegisters 
    //creates a for loop that creates a new LinkedList queue of registers that shoppers 
    //can potentially be put into 
    //which is then added to the list of register queue.
    private void createRegisters() {
        for(int i = 0; i < numRegisters; i++) {
            Queue<Shopper> tempRegister = new LinkedList<>();
            
            registers.add(tempRegister);
        }
    }
    
    //just adds the shopper into the priority queue
    public void addShopperToLine(Shopper shopper) {
        waitingShoppers.add(shopper);
    }
    
    /*
    Checks if there is only one register in the hardware store and if so just 
    checks out the shoppers in the priority queue and removes shoppers one by one
    If there are 2 or more 
    calls checkQueues() which will be explained later 
    Afterwards goes through the registers and gets their grand total to add to the revenueInCents
    Then removes that shopper from that specific queue 
    then moves to the next queue. 
    
    */
    public void checkOutAllShoppers() {
        
        if(numRegisters == 1) {
            Iterator<Shopper> iter = waitingShoppers.iterator();
            while(iter.hasNext()) {
                revenueInCents += iter.next().amountOwed();
                iter.remove();
            }
        }
        else {
            checkQueues();
            for(int i = 0; i < registers.size(); i++) {
                Queue<Shopper> tempQueue = registers.get(i);
                Iterator<Shopper> iter = tempQueue.iterator();

                while(iter.hasNext()) {
                    //Shopper tempShopper = iter.next();
                    revenueInCents += iter.next().amountOwed();
                    iter.remove();
                }
            }
        }
        //System.out.println(revenueInCents);
    }
    
    
    /*
    Uses a while loop to go through the list of shoppers in the priority queue.
    finds the position of the smallest queue size via findShortestQueue()
    then adds a shopper to the queue with the smallest size, then removes them from the priority queue 
    Then moves to the next shopper in the priority queue still.
    
    */
    private void checkQueues() {
        
        Iterator<Shopper> iter = waitingShoppers.iterator();
        
        while(iter.hasNext()) {
            
            int position = findShortestQueue();
            //System.out.println(position);
            registers.get(position).add(iter.next());
            iter.remove();
        }
    }
    
    /*
    Helper method to find the queue in the list of queues with the shortest size
    sets up index 0 as the one with the shortest queue 
    And changes the minIndex which represents the queue with the smallest size 
    then returns it for checkQueues() to use. 
    */
    
    private int findShortestQueue() {
        int minSize = registers.get(0).size();
        int minIndex = 0;
        int i = 0;
        for(Queue<Shopper> l : registers) {
            if(l.size() < minSize) {
                minSize = l.size();
                minIndex = i;
            }
            i++;
        }
        
        return minIndex;
    }
   
    
    //takes the revenueincents which is calculated when checkoutAllShoppers() is called 
    //adds it to revenue 
    //then returns the revenue / 100. to get value in $xx.xx form
    public double totalRevenue() {
        double revenue = 0.;
        
        revenue += revenueInCents;
        
        return revenue / 100.;
    }
}
