package Dlist;
import java.util.Iterator;
public class DList implements Iterable<String> {

    private static class DListNode {
        public String data;
        public DListNode next;
        public DListNode previous;
        
        public DListNode(String data) {
            this.data = data;
            next = null;
            previous = null;
        }
        
        public DListNode () {
            data = null;
        }
    }
    
    private DListNode nil;
    private int numElements = 0;
    
    public DList() {
        nil = new DListNode();
        nil.previous = nil;
        nil.next = nil;
        nil.data = null;
    }
    
    /*
    checks to see how many elements are already in the list
    after seeing how many there are take the appropriate actions depending 
    on size of the list. 
    
    */
    public void addFirst(String elem) {
        if(numElements == 0) {
            DListNode newNode = new DListNode(elem);
            nil.next = newNode;
            nil.previous = newNode;
            newNode.next = nil;
            newNode.previous = nil;
            numElements++;
        } else if (numElements == 1) {
            DListNode newNode = new DListNode(elem);
            DListNode temp = nil.previous;
            temp.previous = newNode;
            newNode.next = temp;
            newNode.previous = nil;
            numElements++;
        } else {
            DListNode newNode = new DListNode(elem);
            DListNode temp1 = nil.next;
            newNode.next = temp1;
            newNode.previous = nil;
            temp1.previous = newNode;
            nil.next = newNode;
            numElements++;
        }
    }
    
    /*
    Same as addFirst
    where first check number of elements in the list
    After checking how many elements there are then modify the list
    */
    
    public void addLast(String elem) {
        if(numElements == 0) {
            DListNode newLastNode = new DListNode(elem);
            newLastNode.previous = nil;
            newLastNode.next = nil;
            nil.previous = newLastNode;
            nil.next = newLastNode;
            numElements++;
        } else if(numElements == 1) {
            DListNode newLastNode = new DListNode(elem);
            DListNode oldLast = nil.previous;
            newLastNode.previous = oldLast;
            newLastNode.next = nil;
            nil.previous = newLastNode;
            numElements++;
        } else {
            DListNode newLastNode = new DListNode(elem);
            DListNode oldLast = nil.previous;
            newLastNode.previous = oldLast;
            newLastNode.next = nil;
            nil.previous = newLastNode;
            oldLast.next = newLastNode;
            numElements++;
        }
    }
    
    //just returns head data which can be gotten from nil.next.data;
    public String getFirst() {
        return nil.next.data;
    }
    
    //same as getFirst but its from nil.previous.data
    
    public String getLast() {
        return nil.previous.data;
    }
    
    
    //stores head and the node after head in temp values
    //Then modifies previous and next of nil and the two temp nodes
    //set all aspects of old head to null except data for garbage collection 
    //stores the data from old head and returns it at the end after cleaning
    public String removeFirst() {
        String temp = nil.next.data;
        
        DListNode tempNode1 = nil.next;
        DListNode tempNode2 = tempNode1.next;
        
        nil.next = tempNode2;
        tempNode2.previous = nil;
        tempNode1.next = null;
        tempNode1.previous = null;
        numElements--;
        
        return temp;
    }
    
    //same method as removeFirst
    public String removeLast() {
        String temp = nil.previous.data;
        
        DListNode tempLast1 = nil.previous;
        DListNode tempSecondLast = tempLast1.previous;
        
        nil.previous = tempSecondLast;
        tempSecondLast.next = nil;
        tempLast1.next = null;
        tempLast1.next = null;
        numElements--;
        
        return temp;
    }
    
    /*
    An not optimal while loop to try and find the data of an index
    Using a while loop starting from head.next after checking nil.next 
    */
    public String get(int index) throws IndexOutOfBoundsException {
        if(index > numElements) {
            throw new IndexOutOfBoundsException("Index does not exist.");
        } else {
            String info = "";
            
            int i = 0;
            DListNode start = nil.next;
            
            if(index == 0)
                return start.data;
            
            DListNode temp = start.next;
            index++;
            while(i <= index) {
                if(index == i) {
                    info = temp.data;
                    break;
                } else {
                    i++;
                    temp = temp.next;
                }
            }
            
            return info;
        }    
    }
    
    /*
    Does the same while loop check as get 
    Checks the first node then moves on to the rest of the nodes and checks one by one
    
    */
    
    public String set(int index, String value) throws IndexOutOfBoundsException{
        if(index > numElements) {
            throw new IndexOutOfBoundsException("Index does not exist.");
        } else {
            String oldValue = "";
            int i = 0;
            DListNode start = nil.next;
            
            if(index == 0) {
                return start.data;
            }
            
            DListNode temp = start.next;
            index++;
            while(i <= index) {
                if(index == i) {
                    oldValue = temp.data;
                    break;
                } else {
                    i++;
                    temp = temp.next;
                }
            }
            
            return oldValue;
        }
    }
    
    /*
    Same while loop method as the two above.
    Converts obj to string to make it easier to check and compare data to it
    then checks first node before moving on to the rest of the nodes.
    */
    
    public boolean contains(Object obj) {
        String check = (String) obj;
        boolean result = false;
        
        int start = 0;
        DListNode startNode = nil.next;
        if(startNode.data.equalsIgnoreCase(check)) 
            return true;
        else {
            start++;
            DListNode temp = startNode.next;
            while(start < numElements) {
                if(temp.data.equalsIgnoreCase(check)) {
                    return true;
                } else {
                    start++;
                    temp = temp.next;
                }
            }
        }
        
        return result;
    }
    
    
    //just returns numElements
    public int size() {
        return numElements;
    }
    
    /*
    Does the same while loop method as above
    Checking first node then going from there.
    */
    
    public int indexOf(Object obj) {
        String check = (String) obj;
        int result = -1;
        
        DListNode startNode = nil.next;
        int startIndex = 0;
        if(startNode.data.equalsIgnoreCase(check)) {
            return 0;
        } else {
            startIndex++;
            DListNode temp = startNode.next;
            while(startIndex < numElements) {
                if(temp.data.equalsIgnoreCase(check)) {
                    return startIndex;
                } else {
                    startIndex++;
                    temp = temp.next;
                }
            }
        }
        
        
        return result;
        
    }
    
    private class DListIterator implements Iterator<String> {
        private DListNode pointer;
        
        public DListIterator() {
            if(nil.next == nil) 
                pointer = nil;
            else 
                pointer = nil.next;
        }

        @Override
        public boolean hasNext() {
            return pointer.next != null;
        }

        @Override
        public String next() {
            String data = pointer.data;
            pointer = pointer.next;
            return data;
        }
    }
    
    
    @Override
    public Iterator<String> iterator() {
        return new DListIterator();
    } 
    
}