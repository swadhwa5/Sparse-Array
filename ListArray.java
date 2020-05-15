package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array implementation using a linked list. The sole purpose of this
 * (otherwise useless) implementation is to show that we can implement a
 * given interface in different ways.
 *
 * @param <T> Element type.
 */
public class ListArray<T> implements Array<T> {
  // The not-so-obvious representation of our abstract Array: A linked
  // list with "length" nodes instead of an array of "length" slots.
  // We also keep an explicit length, so we don't have to re-compute it
  // every time.
  private Node<T> list;
  private int length;


  /**
   * Constructs a new ListArray.
   *
   * @param n Length of array, must be n &gt; 0.
   * @param t Default value to store in each slot.
   * @throws LengthException if n &le; 0.
   */
  public ListArray(int n, T t) throws LengthException {
    if (n <= 0) {
      throw new LengthException();
    }

    // Initialize all positions as we promise in the specification.
    // Unlike in SimpleArray we cannot avoid the initialization even
    // if t == null since the nodes still have to be created.
    // On the upside we don't need a cast anywhere.
    for (int i = 0; i < n; i++) {
      this.prepend(t);
    }

    // Remember the length!
    this.length = n;
  }

  // Insert a node at the beginning of the linked list.
  private void prepend(T t) {
    Node<T> n = new Node<>();
    n.data = t;
    n.next = this.list;
    this.list = n;
  }

  // Find the node for a given index.
  // Assumes "index" is valid.
  private Node<T> find(int index) {
    Node<T> n = this.list;
    int i = 0;
    while (n != null && i < index) {
      n = n.next;
      i = i + 1;
    }
    return n;
  }

  @Override
  public T get(int i) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }

    Node<T> n = this.find(i);
    return n.data;
  }

  @Override
  public void put(int i, T t) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }

    Node<T> n = this.find(i);
    n.data = t;
  }

  // Unlike in SimpleArray, we have to check the preconditions explicitly
  // in get() and put().

  @Override
  public int length() {

    return this.length;
  }

  @Override
  public Iterator<T> iterator() {

    return new ArrayIterator();
  }

  // A nested Node<T> class to build our linked list out of. We use a
  // nested class (instead of an inner class) here since we don't need
  // access to the ListArray object we're part of.
  private static class Node<T> {
    T data;
    Node<T> next;
  }

  // An iterator to traverse the array from front to back.
  private class ArrayIterator implements Iterator<T> {
    // Current position in the linked list.
    Node<T> current;

    ArrayIterator() {

      current = list;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T t = current.data;
      current = current.next;
      return t;
    }

    @Override
    public boolean hasNext() {

      return current != null;
    }

    @Override
    public void remove() {

      throw new UnsupportedOperationException();
    }
  }
}
