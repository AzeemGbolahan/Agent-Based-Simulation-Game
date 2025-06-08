/**
 * File Name:    LinkedList.java
 * Author:       Azeem Gbolahan
 * Description:  This file defines a generic singly-linked list class with basic list operations such as
 *               add, remove, get, contains, and iteration. The list is implemented using custom Node and 
 *               Iterator classes, allowing it to store elements of any type T.
 *
 *               Includes:
 *               - Node class for internal structure
 *               - Iterable implementation for enhanced-for loop support
 *               - Custom toArrayList method to convert to standard ArrayList
 *               - equals method to compare with other LinkedLists
 *               - Useful methods such as add(index, item), clear(), isEmpty(), get(index), etc.
 *
 * How to Run:   Compile using `javac LinkedList.java` and run tests or integrate into a simulation.
 *
 * Example Usage:
 *      LinkedList<String> list = new LinkedList<>();
 *      list.add("Hello");
 *      list.add("World");
 *      System.out.println(list); // [ World, Hello ]
 */


 import java.util.ArrayList;
 import java.util.Iterator;
 
 // Main LinkedList class that implements the Iterable interface to allow use in for-each loops
 public class LinkedList<T> implements Iterable<T>{
 
     // Inner static Node class to represent each element in the LinkedList
     private static class Node<T>{
         T data;                // Data stored in the node
         Node<T> next;          // Reference to the next node
 
         // Constructor when next node is unknown (end of list)
         public Node(T item){
             data = item;       // assign the item to the data field of the node
             next = null;       // the next pointer is set to null since we don't know the next node
         }
 
         // Constructor when next node is known
         public Node(T item, Node<T> newNext){
             data = item;       // assign the item to the data field of the node
             next = newNext;    // set the next pointer to the specified node
         }
 
         // Getter for node data
         public T getData(){
             return data;       // return the data inside this node
         }
 
         // Setter for the next node reference
         public void setNext(Node<T> n){
             next = n;          // set the next pointer to the specified node
         }
 
         // Getter for the next node
         public Node<T> getNext(){
             return next;       // return the next node in the list
         }
     }
 
     // Inner class to allow iteration over LinkedList elements
     private class LLIterator implements Iterator<T>{
         Node<T> next;  // Current node to return during iteration
 
         // Constructor initializes the iterator to the head of the list
         public LLIterator(Node<T> head){
             next = head; // set the current node to the head node
         }
 
         // Returns true if there is another element to return
         public boolean hasNext(){
             return next != null; // return true if the next node is not null
         }
 
         // Returns the next element and moves the iterator forward
         public T next() {
             T toReturn = next.getData(); // store the current node's data to return
             next = next.getNext();       // move to the next node
             return toReturn;             // return the stored data
         }
 
         // Empty remove method (optional override, unused)
         public void remove(){
             // not implemented
         }
     }
 
     /**
     * Returns an iterator over the elements in this list in proper sequence.
     * Allows usage of enhanced for-loops on LinkedList.
     */
     public Iterator<T> iterator(){
         return new LLIterator(this.head); // return a new iterator starting from the head
     }
 
     /**
      * Converts the linked list into an ArrayList for easier access or export
      * @return ArrayList containing all elements from the linked list
      */
     public ArrayList<T> toArrayList(){
         ArrayList<T> toArrayList = new ArrayList<>(); // create a new ArrayList to hold the data
         toArrayList.add(this.head.getData());         // add the head node's data first
         for(int i = 0; i < size() - 1; i++){           // iterate through the remaining nodes
             this.head = head.getNext();               // move to the next node
             toArrayList.add(this.head.getData());     // add its data to the ArrayList
         }
         return toArrayList;                           // return the completed ArrayList
     }
 
     int size;           // Tracks the number of nodes in the list
     Node<T> head;       // Points to the first node in the list
 
     // Constructor initializes an empty list
     public LinkedList(){
         size = 0;      // the default size of an empty linkedlist is 0
         head = null;   // an empty linkedlist has no head by definition     
     }
 
     /**
     * Returns the number of elements currently in the list.
     * @return integer representing the size of the list.
     */
     public int size(){
         return size; // returns the size of the linkedlist 
     }
 
     /**
     * Adds a new element to the front (head) of the list.
     * @param item the element to be added to the list.
     */
     public void add(T item){
         Node<T> newNode = new Node<>(item, head); // Create new node and link to current head
         head = newNode;                           // Update head to new node
         size++;                                   // Increment size
     }
 
     /**
      * Adds a node at a specific index in the list
      * @param index the index where the new node should be added
      * @param item the data to be stored in the new node
      */
     public void add(int index, T item){
         if(index < 0 || index > size){ // Check for invalid index
             System.out.println("The index is out of bound");
             return;
         }
 
         if(index == 0){ // Insert at the beginning
             add(item); // adds the item at the beginning of the linkedlist
             return;
         }
 
         Node<T> newNode = new Node<>(item); // Create new node
         Node<T> walker = head;              // Temporary pointer to traverse the list
         Node<T> holder;                     // Holds reference to the node after insertion point
 
         for(int i = 0; i < index - 1; i++){ // Move walker to the node before the desired index
             walker = walker.getNext();
         }
 
         holder = walker.getNext();   // Store current next node
         walker.setNext(newNode);     // Set new node as next
         newNode.setNext(holder);     // Link new node to stored next node
         size++;                      // Increment size
     }

     /**
     * Removes all elements from the list by resetting the head and size.
     */
     public void clear(){
         size = 0;     //set the size of the list to zero 
         head = null;  // set the head to null to lose reference to the rest of the nodes
     }
 
     /**
      * Checks if a value exists in the list
      * @param o the object to look for in the list
      * @return true if the object is found, false otherwise
      */
     public boolean contains(Object o){
         Node<T> walker = head;           // Start from head
         for(int i = 0; i < size; i++){   // Check each node
             if(walker.getData().equals(o)){ // If match found
                 return true;             // return true
             } else {
                 walker = walker.getNext(); // move to next node
             }
         }
         return false; // Not found 
     }
 
/**
     * Compares this list with another object for equality.
     * @param o the object to compare with this list.
     * @return true if the two lists are equal, false otherwise.
     */
     public boolean equals(Object o) {
         if (this == o) return true;                            // Same object
         if (!(o instanceof LinkedList)) return false;         // Not a LinkedList
 
         LinkedList<?> otherList = (LinkedList<?>) o;          // Cast to LinkedList
         if (otherList.size() != size()) return false;         // Sizes must match
 
         Node<T> walker1 = head;                               // Traverse this list
         Node<?> walker2 = otherList.head;                     // Traverse other list
 
         while (walker1 != null) {                             // Traverse till end of list
             if (!walker1.getData().equals(walker2.getData())){
                 return false; // Mismatch in data
             }
             walker1 = walker1.getNext();
             walker2 = walker2.getNext();
         }
         return true; // All elements matched
     }
 
    /**
     * Returns the element at the specified index in the list.
     * @param index the position of the element to retrieve.
     * @return the data stored at that index.
     */
     public T get(int index) {
         if (index < 0 || index >= size()) {                   // Check bounds
             throw new IndexOutOfBoundsException("Index is out of bounds");
         }
 
         Node<T> walker = head;                                // Start from head
         for (int i = 0; i < index; i++) {                     // Traverse to index
             walker = walker.getNext();
         }
         return walker.getData();                              // Return data at index
     }
 
     /**
     * Removes and returns the first element of the list.
     * @return the data stored in the removed head node.
     */
     public boolean isEmpty(){
         return size() == 0; // returns true if size is 0 ; returns false if size is greater than 0
     }
 
    /**
     * Removes and returns the first element of the list.
     * @return the data stored in the removed head node.
     */
     public T remove() {
         if (size() == 0) return null;     // Empty list check
 
         Node<T> walker = head;           // Store current head
         head = walker.getNext();         // Update head to next node
         size--;                          // Decrement size
         return walker.getData();         // Return removed data
     }
 
     /**
      * Removes the node at a specific index and returns its data
      * @param index the position of the node to remove
      * @return the data of the removed node
      */
     public T remove(int index) {
         if (index < 0 || index >= size()) { // check if index is out of bounds
             throw new IndexOutOfBoundsException("Index is out of bounds");
         }
 
         if (index == 0) return remove(); // if index is 0, remove the first node
 
         Node<T> walker = head;           // traverse to node before target
         for (int i = 0; i < index - 1; i++) {
             walker = walker.getNext();
         }
 
         Node<T> toRemove = walker.getNext();     // Node to remove
         walker.setNext(toRemove.getNext());      // Bypass removed node
         size--;                                  // Decrease size
         return toRemove.getData();               // Return removed data
     }
 
     /**
     * Returns a string representation of the list.
     * @return the formatted string representing the linked list.
     */
     public String toString(){
         String str = "[ ";            // Begin string
         Node<T> walker = head;        // Start from head
 
         if(size() == 0) return "[ ]"; // Handle empty case
 
         str += walker.getData();      // Append head data
         for(int i = 0; i < size() - 1; i++){ // Append remaining data
             walker = walker.getNext();
             str += ", " + walker.getData();
         }
         str += " ]";                  // End string
         return str;                   // Return complete string
     }
 }
 