import java.util.ArrayList;
public class ShoppingCart {
    
    ArrayList<Item> cart;
    
    ShoppingCart() {
        cart = new ArrayList<>();
    }
    
    public void addItem(Item item) {
        cart.add(item);
    }
    
    //loop to add all item prices 
    //then multiplies by 100 to get cents version. 
    public int grandTotal() {
        int total = 0;
        for(Item i : cart) {
            total += i.getPrice();
        }
        
        total *= 100;
        return total;
    }
    
    public int numItems() {
        return cart.size();
    }
    
    public String toString() {
        
        String output = "";
        
        for(Item i : cart) {
            output += "[Item 1: " + i.getName() + "] " + "[Price " + i.getPrice() + "]\n";
        }
        
        return output;
    }
}
