package hw2;

public class ListArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new ListArray<>(LENGTH, INITIAL);
  }

  @Override
  public void createBadArray() {

    new SparseArray<>(BAD_LENGTH, INITIAL);
  }
}
