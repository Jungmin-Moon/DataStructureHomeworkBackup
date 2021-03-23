
import java.util.Comparator;
import java.util.Objects;
public class Contact implements Comparable<Contact>{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    
    
    Contact(String fName, String lName, String pNum, String address, String city, String state) {
        firstName = fName;
        lastName = lName;
        phoneNumber = pNum;
        this.address = address;
        this.city = city;
        this.state = state;
    }
    
    Contact(String fName, String lName, String pNum) {
        firstName = fName;
        lastName = lName;
        phoneNumber = pNum;
    }
    
    //list of accessors 
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
        //return "(" + phoneNumber.substring(0,3) + ") " + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getState() {
        return state;
    }
    
    //list of modifiers 
    
    public void changeFirstName(String newFirstName) {
        firstName = newFirstName;
    }
    
    public void changeLastName(String newLastName) {
        lastName = newLastName;
    }
    
    public void changePhone(String newPhone) {
        phoneNumber = newPhone;
    }
    
    public void changeAddress(String newAddress) {
         address = newAddress;  
    }
    
    public void changeCity(String newCity) {
        city = newCity;
    }
    
    public void changeState(String newState) {
        state = newState;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        
        if(!(obj instanceof Contact)) {
            return false;
        }
        
        Contact tempContact = (Contact) obj;
        
        if(tempContact.getFirstName().equals(this.firstName)) {
            if(tempContact.getLastName().equals(this.lastName)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 14 * hash + Objects.hashCode(this.firstName);
        hash = 14 * hash + Objects.hashCode(this.lastName);
        return hash;
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName + "  Phone number:" + "(" + phoneNumber.substring(0,3) + ") " 
                + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6) + 
                "\n" + address + "\n" + city + "," + state;
    }
    
    @Override
    public int compareTo(Contact another) {
        if(another.getLastName().compareTo(lastName) > 0) {
            return 1;
        }
        if(another.getLastName().compareTo(lastName) < 0) {
            return -1;
        }
        if(another.getLastName().compareTo(lastName) == 0) {
            if(another.getFirstName().compareTo(firstName) > 0) {
                return 1;
            }
            if(another.getFirstName().compareTo(firstName) < 0) {
                return -1;
            }
        }
        
        return 0;
    }
    
    
    
}
