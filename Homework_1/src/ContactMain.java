
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ContactMain {
    public static void main(String[] args) {
        //modified to have duplicate contacts to see if methods to find 
        //and make sure duplilcate entries don't get added 
        Contact contact1 = new Contact("John", "Smith", "7185248756", "2345 Flushing Avenue", "Flushing", "NY");
        Contact contact2 = new Contact("Jamie", "Jones", "7189651238");
        Contact contact3 = new Contact("John", "Wayne", "7182549625", "3209 Bedford Avenue", "Brooklyn", "NY");
        Contact contact4 = new Contact("Sally", "Ann", "7184298543");
        Contact contact5 = new Contact("Sarah", "Gil", "7187257286", "147 51st Street", "Miami", "FL");
        Contact contact7 = new Contact("Annabelle", "Stan", "7182694576", "125 Main Street", "Chicago", "IL");
        Contact contact6 = new Contact("Billy", "Kicks", "7184269710", "45th Street", "Brooklyn", "NY");
        Contact contact8 = new Contact("Billy", "Kicks", "7184269710", "45th Street", "Brooklyn", "NY");
        Contact contact9 = new Contact("Annabelle", "Stan", "7182694576", "125 Main Street", "Chicago", "IL");
        
        Contact[] contacts = new Contact[9];
        
        contacts[0] = contact1;
        contacts[1] = contact2;
        contacts[2] = contact3;
        contacts[3] = contact4;
        contacts[4] = contact5;
        contacts[5] = contact6;
        contacts[6] = contact7;
        contacts[7] = contact8;
        contacts[8] = contact9;
        
        ContactList contactList = new ContactList(contacts);
        
        
        //part that checks if findByLastName works as intended. 
        
        //sorting the list first
        contactList.sortList(contactList.getList());
        
        Contact c = contactList.findByLastName("Jones");
        Contact c1 = contactList.findByLastName("Happy");
        
        System.out.println("Check to see if last name exists: ");
        if(c == null) {
            System.out.println("That name does not exist.");
        }
        else {
            System.out.println("The contact : " + c + " does exist");
        }
        
        if(c1 == null) {
            System.out.println("Name does not exist.");
        }
        else {
            System.out.println(c1);
        }
        System.out.println();
        
        //check to see if find by phone works as intended 
        Contact phoneContact = contactList.findByPhoneNumber("7182546987");
        Contact phoneContact2 = contactList.findByPhoneNumber("7185248756");
        
        System.out.println("Checking if phone number exists: ");
        if(phoneContact == null) {
            System.out.println("That number is invalid.");
        }
        else {
            System.out.println("The phone number: " + phoneContact.getPhoneNumber() + " does exist.");
        }
        
        if(phoneContact2 == null) {
            System.out.println("That contact number doesn't exist");
        } else {
            System.out.println("The phone number: " + phoneContact2.getPhoneNumber() + " does exist.");
        }
        System.out.println();
        
        //finding by city
        System.out.println("Finding by city: ");
        ContactList cities = new ContactList();
        cities = contactList.findAllByCity("Brooklyn");
        
         for(int i = 0; i < cities.getList().size(); i++) {
            System.out.println(cities.getList().get(i));
        }
        System.out.println();
         
        //checking finding by last name first initial 
        System.out.println("Finding by initial of last name: ");
        ContactList lastNameChar = new ContactList();
        lastNameChar = contactList.findAllByLastInitial('S');
        
        for(int i = 0; i < lastNameChar.getList().size(); i++) {
            System.out.println(lastNameChar.getList().get(i));
        }
        
        //checking to see if citylist and initial list are equal 
        System.out.println();
        System.out.println("City List == Initial List: " + lastNameChar.equals(cities));
        
        //simple check to see if size works
        System.out.println();
        System.out.println(contactList.size());
        
        //check to see if finding by index works and if exception will be thrown
        System.out.println();
        System.out.println(contactList.get(0));
        //System.out.println(contactList.get(9));
        
        //Iterator check 
        
        Iterator itr = contactList.iterator();
        
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        
        System.out.println();
        
        //check for removing an object 
        System.out.println(contactList.remove(contact5));
        
        System.out.println();
        
        Iterator itr2 = contactList.iterator();
        while(itr2.hasNext()) {
            System.out.println(itr2.next());
        }
    }
}
