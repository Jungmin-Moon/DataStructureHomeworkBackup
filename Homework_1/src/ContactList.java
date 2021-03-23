import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class ContactList implements Iterable<Contact> {
    private List<Contact> contacts;
    
    ContactList() {
        contacts = new ArrayList<>();
    }
    
    ContactList(Contact [] contacts) {
        List<Contact> tempList = Arrays.asList(contacts);
        this.contacts = new ArrayList<>(contacts.length);
        this.contacts.addAll(0, tempList);
    }
    
    public List<Contact> getList() {
        return contacts;
    }
    
    //binary search to find the contact via last name
    //done slightly differently on comparison to check for < 0 on this 
    //and the binary search below due to the list being ordered by last name
    public Contact findByLastName(String last) {
        
        int left = 0;
        int right = contacts.size() - 1;
        
        //while loop to check middle and adjust if need be. standard binary search
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            String tempLast = contacts.get(mid).getLastName();
            
            if(tempLast.compareToIgnoreCase(last) == 0) 
                return contacts.get(mid);
            
            if(tempLast.compareToIgnoreCase(last) > 0) 
                left = mid + 1;
            else 
                right = mid - 1;
        } 
        
        return null;
    }
    
    //binary search that searchs through the templist created in both 
    //last inital search and city search to check if the list has 
    //those contacts already inside. 
    //before attempting to add
    public int binarySearch(List<Contact> conList, Contact c) {
        int left = 0;
        int right = conList.size() - 1;
        
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(conList.get(mid).compareTo(c) == 0) {
                return mid;
            }
            if(conList.get(mid).compareTo(c) < 0) {
                left = mid + 1;
            } else {
                right = mid + 1;
            }
        }
        
        return -1;
    }
    
    //Sort using sort function 
    //if this is too cheeky and not in the spirit of the assignment, I understand why i lost points for this. 
    public void sortList(List<Contact> c) {
        Collections.sort(c);
    }
    
    
    //simple linear search for phone number after List is sorted by last name; 
    public Contact findByPhoneNumber(String phone) {
        
        for(int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPhoneNumber().equalsIgnoreCase(phone))
                return contacts.get(i);
        }
        
        return null;
    }
    
    //adds contacts to a new list if the first initial of last name matches the char given
    //sorts the list on every go just in case but probably not needed and just wastes runtime, not 100% on that
    //then checks with binarySearch to see if its already inside the list 
    //if is not in the list will then add it 
    //before turning it into an array and creating a new contactList and returning that. 
    public ContactList findAllByLastInitial(char ch) {
        
        ArrayList<Contact> tempList = new ArrayList<>();
        
        for(int i = 0; i < contacts.size(); i++) {
            sortList(contacts);
            if(contacts.get(i).getLastName().charAt(0)==(ch)) {
                if(binarySearch(tempList, contacts.get(i)) >= 0) 
                    continue;
                else 
                    tempList.add(contacts.get(i));
            }
        }
        Contact[] tempArray = tempList.toArray(new Contact[tempList.size()]);
        ContactList lastNameInitial = new ContactList(tempArray);
        return lastNameInitial;
        
    }
    
    //adds contact if city matches parameter city through linear search
    //same reasoning as above method
    public ContactList findAllByCity(String city) {
        
        ArrayList<Contact> tempList = new ArrayList<>();
        
        for(int i = 0; i < contacts.size(); i++) {
            sortList(contacts);
            Contact tempC = contacts.get(i);
            if(tempC.getCity() == null) 
                continue;
            if(tempC.getCity().equalsIgnoreCase(city))
                if(binarySearch(tempList, tempC) >= 0)
                    continue;
                else 
                    tempList.add(tempC);
                    
            
        }
        
        Contact[] tempArray = tempList.toArray(new Contact[tempList.size()]);
        ContactList cityList = new ContactList(tempArray);
        return cityList;
    }

    //add that first checks via index if it already exists 
    //if it does exist already starting at index 0 to the list size  
    private boolean add(Contact c) {
        
        //sortList(contacts);
        if(contacts.isEmpty()) {
            contacts.add(c);
            return true;
        }
        sortList(contacts);
        for(int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).compareTo(c) < 0) {
                contacts.add(i + 1, c);
            }
            if(contacts.get(i).compareTo(c) > 0) {
                if(i == 0) {
                    contacts.add(i, c);
                    return true;
                } else 
                    contacts.add(i - 1, c);
                    return true;
            } else {
                contacts.add(i, c);
                return true;
            }
        }
            
        
        return false;
    }
    
    //just gets the size of list.
    public int size() {
        return contacts.size();
    }
    
    //casts obj to contact then removes that Contact but since its already stored as a contact
    //it can be easily returned. 
    public Contact remove (Object obj) {
        Contact c1 = (Contact) obj;
        contacts.remove(c1);
        return c1;
    }
    
    
    public Contact get(int index) throws IndexOutOfBoundsException{
        return contacts.get(index);
    }
    
    //creates a templist that is first sorted 
    //then compares the ContactList list and the second list which is templist to compare.
    @Override
    public boolean equals(Object obj) {
        ContactList tempList = (ContactList) obj;
        List<Contact> list1 = new ArrayList<>();
        list1 = tempList.getList();
        
        tempList.sortList(list1);
        sortList(contacts);
        
        return list1.equals(contacts);
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.contacts);
        return hash;
    }
    
    
    @Override
    public String toString() {
        String builtString = "";
        
        for(Contact c : contacts) {
            String checkAddress = c.getAddress();
            String checkState = c.getState();
            String checkCity = c.getCity();
            
            builtString += "First Name: [" + c.getFirstName() + "] Last Name: [" +
                    c.getLastName() + "] \nPhone Number: [" + c.getPhoneNumber() + "]\nCity: [" + 
                    ((checkState == null) ? checkState : "N/A") + 
                    "]\nAddress: [" + ((checkAddress == null) ? checkAddress : "N/A") + "]\nState: [" + 
                    ((checkCity == null) ? checkCity : "N/A") + "]\n";
        } 
        
        return builtString;
    } 
    
    //using default list iterator instead of creating a new inner class iterator
    @Override
    public Iterator<Contact> iterator() {
        return contacts.iterator();     
    }
}