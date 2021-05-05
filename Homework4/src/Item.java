
import java.util.*;

public class Item {
    
    String name;
    int price;
    
    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String toString() {
        return "Item name: " + name + "\nItem Price: " + price;
    }
}
