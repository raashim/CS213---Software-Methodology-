package util;

import softmeth.project2.Technician;

/**
 * Represents a node in a linked list that stores a Technician object and a reference to the next node.
 * This class is typically used in a linked list structure to hold data and link to the next node.
 *
 * @author Tanvi Yamarty
 * @author Raashi Maheshwari
 */

public class Node {

    /** The technician stored in this node */
    Technician technician;

    /** The next node in the linked list */
    Node next;

    /**
     * Constructs a Node object with the specified technician.
     * The next reference is initialized to null, indicating that this is the last node in the list.
     *
     * @param technician The technician object to be stored in this node.
     */
    public Node(Technician technician ){
        this.technician=technician;
        this.next=null;
    }
}
