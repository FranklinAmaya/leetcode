import java.util.*;

public class test {
  public static void main(String[] args) {
    SmallestStringSwap s = new SmallestStringSwap();
    List<List<Integer>> list = new LinkedList<>();
    list.add(Arrays.asList(0, 3));
    list.add(Arrays.asList(1, 2));
    list.add(Arrays.asList(0, 2));

    System.out.println(s.smallestStringWithSwaps("dcab", list));
  }
}
