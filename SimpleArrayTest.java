package hw2;

public class SimpleArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new SimpleArray<>(LENGTH, INITIAL);
  }

  @Override
  public void createBadArray() {

    new SparseArray<>(BAD_LENGTH, INITIAL);
  }
}
