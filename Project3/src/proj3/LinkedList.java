package proj3;

/**
 *
 * LinkedList class that represents a Linked List, contains ListNodes.
 *
 *  Invariants of the LinkedList class:
 *  1. Length = Number of ListNodes in LinkedList.
 *  2. If length == 0, firstNode = null.
 *  3. The firstNode is a reference to the first ListNode in the LinkedList.
 *  4. Last ListNode in LinkedList points to null.
 *  5. ListNode.next refers to the next ListNode in the LinkedList.
 */
public class LinkedList
{
    private final int EMPTY = 0;
    private final int START = 0;

    private int length;
    private ListNode firstNode;

    /**
     * Constructs an empty Linked List
     */
    public LinkedList()
    {
        length = EMPTY;
        firstNode = null;
    }

    /**
     * @return the length of the Linked List
     */
    public int getLength()
    {
        return length;
    }


    /**
     * Returns the index of the first instance of given data within Linked List
     * @param data given String
     * @return the index of the given data
     */
    public int getIndex(String data){
        if(!isEmpty()){
            int count = 0;
            ListNode runner = getHead();

            while(runner != null && !runner.getData().equals(data)){
                runner = runner.next();
                count++;
            }
            if(runner != null){
                return count;
            }
        }
        return -1;
    }


    /**
     * gets and returns the String data at the given index
     * @param index given index
     * @return data at given index
     */
    public String getDataAtIndex(int index){
        if(!isEmpty()){
            int count = 0;
            ListNode runner = getHead();

            while(runner != null && count != index){
                runner = runner.next();
                count++;
            }
            if(runner != null){
                return runner.getData();
            }
        }
        return null;
    }


    /**
     * checks to see if the linked list is empty
     * @return true iff linked list is empty, false otherwise
     */
    public boolean isEmpty(){
        return getLength() == EMPTY;
    }


    /**
     * checks to see if the data given is the data in the firstNode
     * @param dataToCheck given data
     * @return true iff the given data is the String data in the firstNode
     */
    public boolean isHeadNode(String dataToCheck){
        return this.firstNode.getData().equals(dataToCheck);
    }


    /**
     * inserts the newNode to the front of the linked list, becomes the firstNode
     * @param dataToAdd the given data for newNode
     */
    public void insertAtHead(String dataToAdd){
    	ListNode newNode = new ListNode(dataToAdd);

        if(isEmpty()) {
            this.firstNode = newNode;
        }
        else {
            newNode.next = getHead();
            this.firstNode = newNode;
        }
        this.length++;
    }


    /**
     * inserts the newNode at the end of the Linked List
     * @param dataToAdd given data for newNode
     */
    public void insertAtEnd(String dataToAdd){
        if(isEmpty()){
            ListNode newNode = new ListNode(dataToAdd);
            this.firstNode = newNode;
            this.length++;
        }else{
            insertAfterGivenData(dataToAdd, getLast().getData());
        }
    }


    /**
     * inserts the newNode after the node with the given data
     * @param dataToAdd given data for newNode
     * @param dataBefore the data of the node of which to insert the newNode after
     */
    public void insertAfterGivenData(String dataToAdd, String dataBefore){
        if(!isEmpty()){
            if(containsData(dataBefore)) {
                ListNode runner = getHead();

                while (runner != null && !(runner.getData().equals(dataBefore))) {
                    runner = runner.next();
                }
                if (runner != null) {
                    ListNode newNode = new ListNode(dataToAdd);
                    if (runner.next() == null) {
                        newNode.next = null;
                    } else {
                        newNode.next = runner.next();
                    }
                    runner.next = newNode;
                    this.length++;
                }
            }
        }
    }


    /**
     * insert the newNode before the node with the given data
     * @param dataToAdd given data for newNode
     * @param dataAfter the data of the node of which to insert the newNode before
     */
    public void insertBeforeGivenData(String dataToAdd, String dataAfter){
        if(!isEmpty()){
            if(containsData(dataAfter)){
                ListNode runner = getHead();
                ListNode previousNode = null;

                while (runner != null && !(runner.getData().equals(dataAfter))) {
                    previousNode = runner;
                    runner = runner.next();
                }
                if(runner != null) {
                    ListNode newNode = new ListNode(dataToAdd);
                    if (isHeadNode(dataToAdd)) {
                        newNode.next = getHead();
                        this.firstNode = newNode;
                    } else {
                        previousNode.next = newNode;
                        newNode.next = runner;
                    }
                }
                this.length++;
            }
        }
    }


    /**
     * inserts the newNode at the given index in the LinkedList
     * @param dataToAdd given data for newNode
     * @param index given index
     */
    public void insertAtIndex(String dataToAdd, int index){
        if(!isEmpty()){
            int count = 0;
            ListNode runner = getHead();
            ListNode previousNode = null;

            while (runner != null && count != index) {
                previousNode = runner;
                runner = runner.next();
                count++;
            }

            if(runner != null) {
                ListNode newNode = new ListNode(dataToAdd);
                if(count == START){
                    this.firstNode = newNode;
                }else{
                    previousNode.next = newNode;
                }
                newNode.next = runner;
                this.length++;
            }
        }
    }


    /**
     * inserts a given Linked List at the end of a LinkedList
     * @param listToAdd given LinkedList
     */
    public void insertAll(LinkedList listToAdd){
        if(!isEmpty()){
            this.getLast().next = listToAdd.getHead();
        }else{
            this.firstNode = listToAdd.getHead();
        }
        this.length += listToAdd.getLength();
    }


    /**
     * removes the firstNode in the LinkedList
     */
    public void removeHead(){
        if(!isEmpty()){
            this.firstNode = getHead().next();
            this.length--;
        }
    }


    /**
     * removes the last node in the LinkedList
     */
    public void removeLast(){
        if(!isEmpty()){
            removeAtIndex(getLength()-1);
        }
    }


    /**
     * removes the node with the given String data
     * @param dataToRemove given data
     */
    public void removeGivenString(String dataToRemove){
        ListNode runner = getHead();
        ListNode previousNode = null;

        while(runner != null && !runner.getData().equals(dataToRemove)) {
            previousNode = runner;
            runner = runner.next();
        }
        if(runner != null) {
            if(runner == getHead()) {
                this.firstNode = runner.next();
            }else{
                previousNode.next = runner.next();
            }
            runner.next = null;
            this.length--;
        }
    }


    /**
     * removes the node at the given index in the LinkedList
     * @param index given index
     */
    public void removeAtIndex(int index){
        if(!isEmpty()){
            int count = 0;

            ListNode runner = getHead();
            ListNode previousNode = null;

            while(runner != null && count != index) {
                previousNode = runner;
                runner = runner.next();
                count++;
            }
            if(runner != null) {
                previousNode.next = runner.next();
                this.length--;
            }
        }
    }


    /**
     * empties the LinkedList
     */
    public void clear(){
        this.length = EMPTY;
        this.firstNode = null;
    }


    /**
     * creates a copy of the LinkedList
     * @return the copy of the LinkedList
     */
    public LinkedList clone(){
        LinkedList copy = new LinkedList();
        ListNode runner = this.getHead();
        while (runner != null){
            copy.insertAtEnd(runner.getData());
            if(runner.next() == null){
                runner.next = null;
            }
            runner = runner.next();
        }
        copy.length = this.getLength();

        return copy;
    }


    /**
     * checks to see if another given LinkedList is equal to this LinkedList
     * @param other given LinkedList
     * @return true iff the two LinkedLists are equal to each other
     */
    public boolean equals(LinkedList other){
        if(this.getLength() != other.getLength()){
            return false;
        }

        ListNode runner = this.getHead();
        ListNode runnerOther = other.getHead();
        while(runner != null && runnerOther != null){
            if(!runner.getData().equals(runnerOther.getData())){
                return false;
            }
            runner = runner.next();
            runnerOther = runnerOther.next();
        }
        return runner == null && runnerOther == null;
    }


    /**
     * checks to see if any of the ListNodes in the LinkedList contain the given data
     * @param data given data
     * @return true iff the LinkedList contains the given data
     */
    public boolean containsData(String data){
        ListNode runner = getHead();

        while (runner != null){
            if(runner.getData().equals(data)){
                return true;
            }
            runner = runner.next();
        }
        return false;
    }


    /**
     * Produce a string representation of this LinkedList.
     * The string you create should be formatted
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty LinkedList
     * should give back "{}" followed by its capacity.
     *
     * @return a string representation of this sequence.
     */
	public String toString(){ 
		String toReturn = "(";
		ListNode runner = getHead();
		while(runner != null){
			toReturn = toReturn + runner;
			runner = runner.next;
			if(runner != null){
				toReturn = toReturn + ", ";
			}
		}
		toReturn = toReturn + ")";
		return toReturn;
	}

    //PRIVATE HELPER METHODS
    private ListNode getHead(){
        return firstNode;
    }

    private ListNode getLast(){
        if(!isEmpty()) {
            ListNode runner = getHead();
            ListNode previous = null;

            while(runner != null){
                previous = runner;
                runner = runner.next();
            }
            return previous;
        }
        return null;
    }
}


