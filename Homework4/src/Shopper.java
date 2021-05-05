
public class Shopper implements Comparable<Shopper>{

    String firstName;
    String lastName;
    ShoppingCart userCart;
    
    //Outside of firstname and lastname 
    //also creates a shopping cart for the shopper and only for them
    Shopper(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        userCart = new ShoppingCart();
    }
    
    //checks if the numItems are 1 or 0 and does accordingly
    //if its greater than 1 adds it multiple times using a for loop
    public void addItemToCart(Item item, int numItems) {
        if(numItems == 1) {
            userCart.addItem(item);
        } else if(numItems == 0) {
            System.out.println("No items added. ");
        } else {
            for(int i = 0; i < numItems; i++) {
                userCart.addItem(item);
            }
        }
    }
    
    /*
    First get the cent value of the cart
    Then divide it by 100 to get what it would be normally. 
    Then multiply that normal price by the sales tax in this case 8.875%
    Then add the tax to the totalAmount along with the normal price version of the whole cart
    Then use math.round to round to the nearest cent
    Then finally multiply that final amount by 100 to get the total in cents with no dollars or decimals. 
    */
    public double amountOwed() {
        double totalAmount = 0;
        int centValue = userCart.grandTotal();
        
        
        double tempTotal = centValue / 100;
        centValue /= 100;
        double tax = tempTotal * 0.08875;
        
        totalAmount += centValue;
        totalAmount += tax;
        
        totalAmount = (Math.round(totalAmount * 100.) / 100.);
        
        double totalInCents = (totalAmount * 100);
        
        return totalInCents;
    }
    
    public String toString() {
        return "Firat Name: " + firstName + " Last Name: " + lastName + "\nCart List: " + 
                "\n" + userCart.toString();
    }
    
    //Compares the amount owed by each shopper and depending on the amount places them further or lower on the 
    //priority queue. 
    @Override
    public int compareTo(Shopper o) {
        if(this.amountOwed() > o.amountOwed()) {
            return -1;
        } else if(this.amountOwed() == o.amountOwed()) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
