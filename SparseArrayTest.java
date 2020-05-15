package hw2;

public class SparseArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {

    return new SparseArray<>(LENGTH, INITIAL);
  }

  @Override
  public void createBadArray() {

    new SparseArray<>(BAD_LENGTH, INITIAL);
  }
}