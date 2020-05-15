package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Represents a Sparse array.
 *
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

  private SparseArray.Node<T> list;
  private int length;//length of array
  private T defaultValue;//default value for all elements of array

  // A nested Node<T> class to build our linked list out of. We use a
  // nested class (instead of an inner class) here since we don't need
  // access to the SparseArray object we're part of.
  private static class Node<T> {
    T data;//value of the elements
    int index;//represents index of the element in the main array
    SparseArray.Node<T> next;//next node
    SparseArray.Node<T> prev;//previous node
  }

  /**
   * An array that is meant to be filled primarily with a default value
   * that is not going to change - with the benefit of that default
   * value not being stored numerous times as opposed to once.
   *
   * @param length       The number of indexes the array should have.
   * @param defaultValue The default value for the array.
   * @throws LengthException if length is negative
   */
  public SparseArray(int length, T defaultValue) throws LengthException {
    if (length < 0) {
      throw new LengthException();
    }

    this.length = length;
    this.defaultValue = defaultValue;
  }

  /**
   * Insert a node at the beginning of the linked list.
   *
   * @param i is the index of the element to be prepended
   * @param t is the value  to be prepended
   */
  private void prepend(int i, T t) {
    //setting values for the created node
    SparseArray.Node<T> n = new SparseArray.Node<>();
    n.data = t;
    n.index = i;
    //prepending the node
    n.next = this.list;
    this.list = n;
    if (n.next != null) {
      n.next.prev = n;
    }

  }

  /**
   * finds a node in the list.
   *
   * @param index is the array index of the element to be found in the list
   * @return the node if found in the list, else return null
   */
  private SparseArray.Node<T> find(int index) {
    SparseArray.Node<T> n = this.list;
    while (n != null) {
      //checking if an element with the same index exists in the list
      if (n.index == index) {
        return n;
      }
      n = n.next;
    }
    return null;
  }

  /**
   * removes a node from the list.
   *
   * @param index is the array index of the element to be removed from the list
   */
  private void remove(int index) {
    //if Linked list is empty
    if (this.list == null) {
      return;
    }
    SparseArray.Node<T> n = this.find(index);
    //if node is the first node in the Linked list
    if (n != null) {
      if (this.list == n) {
        this.list = n.next;
        //what to do with prev
      } else { //general case
        n.prev.next = n.next;
        n.next.prev = n.prev;
      }
    }
  }

  /**
   * returns the length of the array.
   *
   * @return the length of the array
   */
  @Override
  public int length() {
    return this.length;
  }

  /**
   * gets value at the given index.
   *
   * @param i Index to read value at.
   * @return the value at the index
   * @throws IndexException when index less than zero or more than length
   */
  @Override
  public T get(int i) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }

    //if element is present in the linked list, the return its changed value
    Node<T> n = this.find(i);
    if (n != null) {
      return n.data;
    }
    //if not present in list, then return default value
    return defaultValue;
  }

  /**
   * puts a value in the array.
   *
   * @param i Index to write value at.
   * @param t Value to write at index.
   * @throws IndexException when index less than zero or more than length
   */
  @Override
  public void put(int i, T t) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }
    SparseArray.Node<T> n = this.find(i);
    if (t != defaultValue) {
      if (n == null) {
        //prepend to list if not present already and value is not default value
        this.prepend(i, t);
      }

      //if already present in the list, then change
      // its value to the one entered by user if its not the default value
      if (n != null) {
        n.data = t;
      }
    }

    //if value entered by user is default value,
    // then remove from list if present already
    if (t == defaultValue) {
      if (n != null) {
        //remove from list if present already
        this.remove(i);
      }
    }
  }

  /**
   * creates a Sparse array iterator.
   *
   * @return a Sparse array iterator
   */
  @Override
  public Iterator<T> iterator() {
    return new SparseArrayIterator();
  }

  /**
   * Iterator class for the Sparse Array.
   */
  private class SparseArrayIterator implements Iterator<T> {

    // Current position in the linked list.
    private int currentIndex;

    /**
     * constructor to set the current index to 0 at the beginning.
     */
    SparseArrayIterator() {

      currentIndex = 0;
    }

    /**
     * determines whether the array has a next element.
     * @return false if array doesn't have a next element
     */
    @Override
    public boolean hasNext() {

      return currentIndex < length;
    }

    /**
     * returns the data/value at the next element, if it exists.
     *
     * @return value of next element
     * @throws NoSuchElementException if doesn't have next
     */
    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      //if not present in list,
      // then return default value,
      // otherwise find it in list and return its value
      SparseArray.Node<T> n = find(currentIndex);
      if (n != null) {
        currentIndex++;
        return n.data;
      }
      currentIndex++;
      return defaultValue;
    }

    @Override
    public void remove() {

      throw new UnsupportedOperationException();
    }
  }
}
