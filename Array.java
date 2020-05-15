package hw2;

import exceptions.IndexException;

/**
 * Interface for an Array that takes a default value to "plaster" over the
 * entire data structure.
 *
 * @param <T> Element type.
 */
public interface Array<T> extends Iterable<T> {
  /**
   * Change value at the given index.
   *
   * @param i Index to write value at.
   * @param t Value to write at index.
   * @throws IndexException if i &lt; 0 or i &gt; length-1.
   */
  void put(int i, T t) throws IndexException;

  /**
   * Value at the given index.
   *
   * @param i Index to read value at.
   * @return Value read at index.
   * @throws IndexException if i &lt; 0 or i &gt; length-1.
   */
  T get(int i) throws IndexException;

  /**
   * Length of array.
   *
   * @return Length of array, always &gt; 0.
   */
  int length();
}
