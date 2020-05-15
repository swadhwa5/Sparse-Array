package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public abstract class ArrayTest {

  protected static final int LENGTH = 113;
  protected static final int INITIAL = 7;
  protected static final int BAD_LENGTH = -2;
  private Array<Integer> array;
  private Iterator<Integer> it;

  public abstract Array<Integer> createArray();
  public abstract void createBadArray();

  @Before
  public void setup() {

    array = createArray();
    it = array.iterator();
  }

  @Test (expected = LengthException.class)
  public void testBadLengthException() {

    createBadArray();
  }
  @Test
  public void testLength() {

    assertEquals(LENGTH,array.length());
  }
  @Test
  public void testConstructor() {

    assertEquals(LENGTH, array.length());
  }

  @Test
  public void newArrayDefaultWorks() {
    for (int i = 0; i < LENGTH; i++) {
      assertEquals(INITIAL, array.get(i).intValue());
    }
  }

  @Test
  public void testGet() {
    assertEquals(7, array.get(0).intValue());
  }

  @Test
  public void testPut() {
    array.put(1, 20);
    assertEquals(20, array.get(1).intValue());
  }

  @Test(expected = IndexException.class)
  public void testPutThrowsException() { //can we change its name to make it more specific

    array.put(-1, 30);
  }

  @Test (expected = IndexException.class)
  public void getThrowsExceptionGivenNegativeIndex() {

    array.get(-1);
  }

  @Test (expected = IndexException.class)
  public void getThrowsExceptionWhenIndexLargerThanLength() {

    array.get(LENGTH);
  }

  @Test (expected = IndexException.class)
  public void putThrowsExceptionWhenIndexLargerThanLength() {

    array.put(LENGTH, 4);
  }

  @Test
  public void getWorksAfterPut() {
    array.put(1, 7);
    assertEquals(7, array.get(1).intValue());
    array.put(1, 20);
    assertEquals(20, array.get(1).intValue());
    array.put(2,10);
    assertEquals(10,array.get(2).intValue());
    array.put(2, 5);
    assertEquals(5, array.get(2).intValue());
    array.put(2, 7);
    assertEquals(7, array.get(2).intValue());
    array.put(0, 20);
    assertEquals(20, array.get(0).intValue());
  }

  @Test
  public void newArrayIteratorWorks() {
    for (Integer val: array) {
      assertEquals(INITIAL, val.intValue());
    }
  }

  @Test
  public void newArrayHasNextWorks() {
    array.put(1, 20);
    assertTrue(it.hasNext());
  }

  @Test
  public void hasNextReturnsFalseWhenAllElementsVisited() {
    for (int i = 0; i < array.length(); i++) {
      it.next();
    }
    assertFalse(it.hasNext());
  }

  @Test (expected = NoSuchElementException.class)
  public void nextThrowsExceptionWhenCalledAfterIterationOver() {
    for (int i = 0; i < array.length(); i++) {
      it.next();
    }
    it.next();
  }
}
