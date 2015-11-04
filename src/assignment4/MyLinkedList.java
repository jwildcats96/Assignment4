package assignment4;


/*
 Jacob Jones
 10/30/2015 Lab6
 C202
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /**
     * Create a default LinkedList
     */
    public MyLinkedList() {
    }

    /**
     * Create a LinkedList from an array of objects
     *
     * @param objects
     */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /**
     * @return the first (head) element in the LinkedList
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /**
     * @return the last element in the LinkedList
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Add an element to the beginning of the LinkedList
     *
     * @param e
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /**
     * Add an element to the end of the LinkedList
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     *
     * @return
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     *
     * @return
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the LinkedList.
     *
     * @param index
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /**
     * Override toString() to return elements in the LinkedList
     *
     * @return toString
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /**
     * Clear the LinkedList
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * methods for lab 6 should be implemented here
     */
    /**
     * @return true if this LinkedList contains the element e, otherwise return
     * false
     * @param e
     */
    public boolean contains(E e) {
        boolean found = false;
        Node<E> temp = head;
        
        int i;
        for (i = 1; temp != null && !found; i++) {
            if (temp.element.equals(e)) {
                found = true;
            } else {                
                temp = temp.next;
            }
        }
        
        return found;
    }
   
    //contains method with counters implemented for assignment 4
    public int[] containsCount(E e) {
        int[] count = new int[2];
        count[0] = 0;//false
        count[1] = 1;
        Node<E> temp = head;
        
        int i;
        for (i = 1; temp != null && count[0] == 0; i++) {
            if (temp.element.equals(e)) {
                count[0] = 1; //true
                
            } else {            
                
                count[1]++;
                temp = temp.next;
            }
        }
        
        return count;
    }

    /**
     * @return the element at specified index of this LinkedList, return null if
     * index is invalid.
     * @param index
     */
    public E get(int index) {
        Node<E> current = head;
        int i = 0;
        if (i == index) {

            current = current.next;
            index--;

            return current.element;
        } else {
            return null;
        }
        //index out of bounds
    }

    /**
     * @return the index of the first matching element in this LinkedList,
     * return -1 if no match.
     * @param e
     */
    public int indexOf(E e) {
        int pos = -1;
        Node<E> temp = head;
        int i = 0;
        while (temp != null || temp.element.equals(e)) {
            if (temp.element.equals(e)) {
                pos = i;
            }
            temp = temp.next;
            i++;
        }
        return pos;
    }

    /**
     * @return the index of the last matching element in this LinkedList, return
     * -1 if no match.
     * @param e
     */
    public int lastIndexOf(E e) {
        int pos = -1;
        Node<E> temp = head;
        int i = 0;
        while (temp.next != null) {
            if (temp.element.equals(e)) {
                pos = i;
            }
            temp = temp.next;
            i++;
        }
        return pos;
    }

    /**
     * Replace the element at specified index in this LinkedList with the
     * specified element, return null if index is invalid.
     *
     * @param int index
     * @param e
     */
    public E set(int index, E e) {
        E oldValue = null;

        Node<E> current = head;
        if (index <= size) {
            for (int i = 0; i <= index; i++) {

                current = current.next;
            }
            oldValue = current.element;
            current.element = e;

        }
        return oldValue;
    }

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}

/*
 run:
 (1) [America]
 (2) [Canada, America]
 (3) [Canada, America, Russia]
 (4) [Canada, America, Russia, France]
 (5) [Canada, America, Germany, Russia, France]
 (6) [Canada, America, Germany, Russia, France, Norway]
 (7) [Poland, Canada, America, Germany, Russia, France, Norway]
 (8) [Canada, America, Germany, Russia, France, Norway]
 (9) [Canada, America, Russia, France, Norway]
 (10) [Canada, America, Russia, France]
 (11) The list does not contain Germany
 (12) Invalid position
 (13) The list element France is at position 3
 (14) [India, Canada, America, Russia, France]
 (15) [India, Canada, America, Russia, France, America]
 (16) The list element America occurs last at 5
 (17) [India, Canada, America, Russia, France, China]
 BUILD SUCCESSFUL (total time: 0 seconds)
 */
