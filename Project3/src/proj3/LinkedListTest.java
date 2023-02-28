package proj3;

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Rule //a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);

    /**
     * creates a LinkedList with elements from an array
     * @param itemsToAdd the array of items
     * @return new Linked List
     */
    private LinkedList makeLL(String[] itemsToAdd){
        LinkedList ll = new LinkedList();
        for(String item: itemsToAdd){
            ll.insertAtHead(item);
        }
        return ll;
    }

    @Test //constructs a Linked List object
    public void LinkedListConstructor(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests getLength;
    public void getLengthTest(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertEquals(3, ll.getLength());
    }

    @Test //Tests getIndex;
    public void getIndex(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertEquals(2, ll.getIndex("C"));
    }

    @Test //Tests getIndex; finds the first element which is at index 0
    public void getIndexHead(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertEquals(0, ll.getIndex("A"));
    }

    @Test //Tests getIndex; called opn an empty Linked List
    public void getIndexEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);

        assertEquals(-1, ll.getIndex("X"));
    }

    @Test //Tests getIndex; called to find the index of an element that is not in the list
    public void getIndexNotContained(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertEquals(-1, ll.getIndex("X"));
    }

    @Test //Tests getDataAtIndex;
    public void getDataAtIndex(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertEquals("C", ll.getDataAtIndex(2));
    }

    @Test //Tests getDataAtIndex; invalid index
    public void getDataAtInvalidIndex(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertNull(ll.getDataAtIndex(4));
    }

    @Test //Tests getDataAtIndex; empty list
    public void getDataAtIndexEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);

        assertNull(ll.getDataAtIndex(2));
    }

    @Test //Tests getLength;
    public void isEmptyTrue(){
        String[] items = {};
        LinkedList ll = makeLL(items);

        assertTrue(ll.isEmpty());
    }

    @Test //Tests getLength;
    public void isEmptyFalse(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertFalse(ll.isEmpty());
    }

    @Test //Test isHeadNode; True
    public void isHeadNodeTrue(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertTrue(ll.isHeadNode("A"));
    }

    @Test //Test isHeadNode; False
    public void isHeadNodeFalse(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertFalse(ll.isHeadNode("c"));
    }

    @Test //Tests insertAtHead; inserts new node so the head becomes the new node
    public void insertAtHead(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A", "X"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtHead("X");

        assertTrue(ll.equals(expected));

    }

    @Test //Tests insertAtHead; inserts a node into an empty Linked List.  New node becomes the head
    public void insertAtHeadEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"X"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtHead("X");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertAtEnd;
    public void insertAtEnd(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"X", "C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtEnd("X");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertAtEnd; called on an empty Linked List
    public void insertAtEndEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"X"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtEnd("X");

        assertTrue(ll.equals(expected));
    }
    @Test //Tests insertAfter; inserts new node after the head
    public void insertAfterFirstNode(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "X", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAfterGivenData("X", "A");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertAfter; inserts after the last node. new node now points to null
    public void insertAfterLastItem(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"X", "C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAfterGivenData("X", "C");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertAfter; if call insertAfter on empty LinkedList, does nothing
    public void insertAfterEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAfterGivenData("X", "C");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertAfter; if call insertAfter when item to insert after isn't in list, does nothing
    public void insertAfterNotInList(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAfterGivenData("X", "D");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertBefore; inserts new node before a current node in the list
    public void insertBefore(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "X", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertBeforeGivenData("X", "C");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests insertBefore; tries to insert an element in an empty list.  Does nothing
    public void insertBeforeEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.insertBeforeGivenData("X", "A");
        assertTrue(ll.equals(expected));

    }

    @Test //Tests insertBefore; called on a list in which the node to insert before is not in the list. Does nothing
    public void insertBeforeItemNotContained(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertBeforeGivenData("X", "D");

        assertEquals(3, ll.getLength());
        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAtIndex; the item is added at specified index, and all following elements have +1 index
    public void insertAtIndex(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "X", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtIndex("X", 2);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAtIndex; adds to the first index
    public void insertAtIndexFirst(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A", "X"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtIndex("X", 0);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAtIndex; called on empty list
    public void insertAtIndexEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtIndex("X", 2);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAtIndex; tries to add at an index that is not in the list, does nothing
    public void insertAtIndexNotContained(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAtIndex("X", 10);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAll;
    public void insertAll(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] otherItems = {"3", "2", "1"};
        LinkedList other = makeLL(otherItems);
        String[] expectedItems = {"3", "2", "1", "C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAll(other);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAll;
    public void insertAllFirst(){
        String[] items = {"A"};
        LinkedList ll = makeLL(items);
        String[] otherItems = {"3", "2", "1"};
        LinkedList other = makeLL(otherItems);
        String[] expectedItems = {"3", "2", "1", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAll(other);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAll; adding another list to an empty list
    public void insertAllFirstEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] otherItems = {"3", "2", "1"};
        LinkedList other = makeLL(otherItems);
        String[] expectedItems = {"3", "2", "1"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAll(other);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests addAll; adding another empty list to a list with items
    public void insertAllOtherEmpty(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] otherItems = {};
        LinkedList other = makeLL(otherItems);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.insertAll(other);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeAtHead;
    public void removeAtHead(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = { "C", "B"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeHead();

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeAtHead; called on an empty LinkedList
    public void removeAtHeadEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.removeHead();

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeLast;
    public void removeLast(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeLast();

        assertEquals(2, ll.getLength());
        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeLast; called on an empty LinkedList
    public void removeLastEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.removeLast();

        assertTrue(ll.equals(expected));
    }

    @Test //Tests remove;
    public void remove(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeGivenString("B");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests remove; tries to remove an item that isn't in the list, should do nothing
    public void removeNotContained(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeGivenString("X");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests remove; tries to remove an item that isn't in the list, should do nothing
    public void removeEmptyList(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.removeGivenString("X");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeGivenString; removes the node at the head. The node after becomes the head
    public void removeFirstItem(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeGivenString("A");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests remove; the last item should be removed and length should decrease by 1
    public void removeLastItem(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeGivenString("C");

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeAtIndex; tries to add at an index that is not in the list, so it adds at the end
    public void removeAtIndex(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeAtIndex(2);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeAtIndex; tries to remove an index that is not in the list, does nothing
    public void removeAtIndexNotInList(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {"C", "B", "A"};
        LinkedList expected = makeLL(expectedItems);

        ll.removeAtIndex(4);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests removeAtIndex; called upon empty list, does nothing
    public void removeAtIndexEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);
        String[] expectedItems = {};
        LinkedList expected = makeLL(expectedItems);

        ll.removeAtIndex(4);

        assertTrue(ll.equals(expected));
    }

    @Test //Tests clear; there should be no items in the List and length is 0
    public void clear(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        ll.clear();

        assertEquals(0, ll.getLength());
    }

    @Test //Tests clone;
    public void cloneTest(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        LinkedList expected = ll.clone();

        assertTrue(ll.equals(expected));
    }

    @Test //Tests clone; called on an empty list
    public void cloneTestEmpty(){
        String[] items = {};
        LinkedList ll = makeLL(items);

        LinkedList expected = ll.clone();

        assertTrue(ll.equals(expected));
    }

    @Test //Tests containsData; checks to see if a Linked List contains a specified item
    public void containsTrue(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);

        assertTrue(ll.containsData("A"));
    }

    @Test //Tests containsData; called on a Linked List that does not contain the item
    public void containsFalse(){
        String[] items = {};
        LinkedList ll = makeLL(items);

        assertFalse(ll.containsData("A"));
    }

    @Test //Tests toString;
    public void toStringTest(){
        String[] items = {"C", "B", "A"};
        LinkedList ll = makeLL(items);
        assertEquals("(A, B, C)", ll.toString());
    }
}
