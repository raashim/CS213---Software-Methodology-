package util;

import java.util.Iterator;

/**
 * This class represents a list of appointments. It provides methods to add,
 * remove, and manipulate the list, such as sorting and printing appointments by
 * patient, location, or appointment details.
 *
 * @param <E> the type of elements in the list
 *
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;

    /**
     * Default constructor initializes an empty list with an initial capacity of 4.
     */
    public List() {
        this.size = 0;
        this.objects = (E[]) new Object[4];
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve
     * @return the element at the specified index
     */
    public E getE(int index) {
        return this.objects[index];
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e The element to check for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E e) {
        int num = find(e);
        if (num != -1) {
            return true;
        }
        return false;
    }

    /**
     * Adds an element to the list if it is not already present.
     *
     * @param arr The element to add to the list
     */
    public void add(E arr) {
        if (!contains(arr)) {

            int n = objects.length - 1;
            if (size == n) {
                grow();
            }
            objects[size++] = arr;
        }
    }

    /**
     * Removes the specified element from the list.
     *
     * @param e The element to remove
     */
    public void remove(E e) {
        int index = find(e);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }
            objects[--size] = null;
        }
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve
     * @return the element at the specified index
     */
    public E get(int index) {
        if ((index >= size) || (index < 0)) {
        }
        return objects[index];
    }

    /**
     * Expands the array to accommodate more elements.
     */
    private void grow() {
        int size = objects.length;

        E[] newObj = (E[]) new Object[size + 4];
        for (int i = 0; i < size; i++) {
            newObj[i] = objects[i];
        }
        objects = newObj; //stores new array into old array
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the current size of the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Gets the current size of the list.
     *
     * @return the number of elements in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the element at the specified index.
     *
     * @param index The index where the element will be set
     * @param e The element to set
     */
    public void set(int index, E e) {
        if ((index >= size) || (index < 0)) {
        }
        objects[index] = e;
    }

    /**
     * Finds the index of the specified element in the list.
     *
     * @param e The element to search for
     * @return the index of the element or -1 if not found
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * Creates an iterator for the list.
     *
     * @return an iterator for the list
     */
    public Iterator<E> iterator() {
        return new ListIterator<>();
    }


    /**
     * Finds the index of the specified element in the list.
     *
     * @param e the element to search for
     * @return the index of the element or -1 if not found
     */
    private int find(E e) {//helper method
        int NOT_FOUND = -1;
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private class ListIterator<E> implements Iterator<E> {
         int currentIndex = 0;

        /**
         * Checks if there is a next element in the iteration.
         *
         * @return true if there are more elements to iterate over, false otherwise
         */
        public boolean hasNext() {
            return currentIndex<size;
        }

        /**
         * Retrieves the next element in the iteration.
         *
         * @return the next element
         */
        public E next() {
            E elementToReturn = (E) get(currentIndex);
            currentIndex++;
            return elementToReturn;
        }
    }
}
