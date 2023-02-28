package proj3;  // Gradescope needs this.
/**
 *  CSC 151: DATA STRUCTURES, PROJECT 3: STAY IN LINE
 *  * @author Claudia Porto
 *  * @version 10/18/2022
 *
 * A class that represents a Sequence ADT. Holds items of String type.
 * Elements are accessed by a "current" index.
 *
 * Invariants of the Sequence class:
 *  1. If there is no current index, current = -1.
 *  2. -1 < current < size, (size(number of items) = holder.length)
 *  3. If size > 0, the contents are stored in holder at indices 0 to size-1 and the contents at indices
 *     >= size are irrelevant.
 *  4. If size = 0, the contents are irrelevant.
 *  5. 0 <= size <= capacity
 */
public class Sequence
{
    private final int DEFAULT_CAPACITY = 10;
    private final int DEFAULT_CURRENT = -1;
    private final int START = 0;

	private LinkedList holder;
	private int current;
    private int capacity;

    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
    	this.current = DEFAULT_CURRENT;
        this.capacity = DEFAULT_CAPACITY;
        this.holder = new LinkedList();
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity){
        this.current = DEFAULT_CURRENT;
        this.capacity = initialCapacity;
        this.holder = new LinkedList();
    }


    /**
     * Adds a string to the sequence in the location before the
     * current element. If the sequence has no current element, the
     * string is added to the beginning of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addBefore(String value)
    {
        capacityReached();

        if(isEmpty()){
            this.holder.insertAtHead(value);
            setCurrent(START);
        }else{
            if(!isCurrent()){
                setCurrent(START);
            }
            this.holder.insertAtIndex(value, getCurrentIndex());
        }
    }


    /**
     * Adds a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value)
    {
        capacityReached();

        if(!isCurrent()){
            setCurrent(size());
            this.holder.insertAtEnd(value);
        }else {
            this.holder.insertAfterGivenData(value, getCurrent());
            setCurrent(getCurrentIndex() + 1);
        }
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {
        return getCurrentIndex() != DEFAULT_CURRENT;
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity()
    {
        return this.capacity;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent()
    {
        if(isCurrent()){
            return this.holder.getDataAtIndex(getCurrentIndex());
        }else{
            return null;
        }
    }


    /**
     * @return the value of the current index
     */
    public int getCurrentIndex(){
        return this.current;
    }


    /**
     * Increase the sequence's capacity to be
     * at least minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity)
    {
        if(getCapacity() < minCapacity){
            this.capacity = minCapacity;
        }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make (just enough) room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another)
    {
        if((size() + another.size()) > this.getCapacity()){
            ensureCapacity((size() + another.size()));
        }
        this.holder.insertAll(another.holder);
    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance()
    {
        if(isCurrent()){
            if(isLastCurrent()){
                setCurrent(DEFAULT_CURRENT);
            }else{
                setCurrent(getCurrentIndex() + 1);
            }
        }
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    public Sequence clone()
    {
        Sequence copy = new Sequence(getCapacity());
        copy.holder = this.holder.clone();
        copy.current = getCurrentIndex();

        return copy;
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent()
    {
        if(isCurrent()){
            if(isLastCurrent()){
                setCurrent(DEFAULT_CURRENT);
                this.holder.removeLast();
            }else{
                if(getCurrentIndex() == START){
                    this.holder.removeHead();
                }else{
                    this.holder.removeAtIndex(getCurrentIndex());
                }
            }
        }
    }


    /**
     * @return the number of elements stored in the sequence.
     */
    public int size()
    {
        return this.holder.getLength();
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start()
    {
        if(isEmpty()){
            setCurrent(DEFAULT_CURRENT);
        }else{
            setCurrent(START);
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
        if(getCapacity() > size()){
            this.capacity = size();
        }
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    public String toString() 
    {
        String sequenceString = "{";

        for (int i = 0; i < size(); i++) {
            if(i == getCurrentIndex()){
                sequenceString += ">";
            }
            sequenceString += this.holder.getDataAtIndex(i);
            if(i+1 != size()) {
                sequenceString += ", ";
            }
        }
        sequenceString += "} ";

        String capacity = String.format("(capacity = %d)", getCapacity());
        return sequenceString + capacity;
    }
    
    /**
     * Checks whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) 
    {
        if(getCurrentIndex() != other.getCurrentIndex()){
            return false;
        }
        return this.holder.equals(other.holder);
    }

    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty()
    {
        return this.holder.isEmpty();
    }

    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        setCurrent(DEFAULT_CURRENT);
        this.holder.clear();
    }


    //PRIVATE HELPER METHODS
    private void capacityReached(){
        if(size() == getCapacity()){
            ensureCapacity((getCapacity() * 2) + 1);
        }
    }

    private boolean isLastCurrent(){
        return getCurrentIndex() == (size() - 1);
    }

    private void setCurrent(int newCurrent){
        this.current = newCurrent;
    }

}