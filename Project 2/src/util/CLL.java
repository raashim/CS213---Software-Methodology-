package util;

import softmeth.project2.Technician;

/**
 * A Circular Linked List (CLL) implementation to manage a list of Technicians.
 * This class provides methods to add, delete, display technicians and navigate through the list.
 *  @author Raashi Maheshwari
 *  @author Tanvi Yamarty
 */
public class CLL {
    private Node head = null;
    private Node tail = null;
    private Node pointer=null;
    private int size=0;

    /**
     * Returns the number of technicians in the list.
     *
     * @return the size of the list
     */
    public int getSize(){
        return size;
    }

    /**
     * Resets the pointer to the head of the list for iteration.
     */
    public void resetPointer(){
        pointer=head;
    }
    public void addTech(Technician technician) {
        Node newN = new Node(technician);
        if (head == null) {
            head = newN;
            tail = newN;
            tail.next = head;
        } else {
            newN.next = head;
            tail.next = newN;
            head = newN;
        }
        size++;
    }

    /**
     * Deletes the Technician from the front of the list.
     *
     * @return true if a technician was removed, false if the list was empty
     */
    public boolean deleteTech() {
        if (head == null) { // util.List is empty
            System.out.println("No technicians to remove. The list is empty");
            return false;
        }
        if (head == tail) {
            head = null;
            tail = null;

        } else {
            head = head.next; // increment had
            tail.next = head; // skip
        }
        size--;
        return true;
    }

    /**
     * Displays the list of technicians in a formatted string.
     */
    public void displayTechList() {
        if (head == null) {
            System.out.println("Currently no available technicians");
            return;
        }

        String res="";

        Node curr = head;
        do {
            res += curr.technician.getFname() + " " + curr.technician.getLname() + " (" + curr.technician.getLocation() + ") --> ";
            curr = curr.next;
        } while (curr != head);

        if (res.length() > 0) {
            res = res.substring(0, res.length() - 5);

        }
        System.out.println(res);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isListEmpty(){
        return head==null;
    }

    /**
     * Retrieves the next Technician in the list based on the current pointer position.
     *
     * @return the next Technician, or null if there are no technicians available
     */
    public Technician getNextTech(){
        if(head==null){
            System.out.println("No technicians available right now");
            return null;
        }
        if(pointer==null){
            pointer=head;
        }
        Technician tech=pointer.technician;
        pointer=pointer.next;
        return tech;
    }
}
