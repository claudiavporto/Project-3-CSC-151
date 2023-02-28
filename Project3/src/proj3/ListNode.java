package proj3;

/**
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field,
 * holding a pointer to a String object. 
 *
 * This is the only class where I'll let you use public instance variables.
 *
 * Invariant of the List Node class:
 * 1. Each ListNode contains data and a reference to the next ListNode
 *
 */
public class ListNode
{
    public String data;
    public ListNode next;

    public ListNode(String newData)
    {
        data = newData;
        next = null;
    }

    public ListNode(String newData, ListNode nextNode){
        data = newData;
        next = nextNode;
    }

    public ListNode next(){
        return this.next;
    }

    public String getData(){
        return this.data;
    }

    public String toString(){
    	return getData();
    }

}
