import java.util.*;

public class WordBreak {
  private Set<String> dict;
  private String s;

  public boolean wordBreak(String s, List<String> wordDict) {
    this.s = s;
    dict = new HashSet<>(wordDict);

    System.out.println(dict.toString());

    return memoize(0, 0);
  }

  private boolean memoize(int s, int e) {

    return 0;
  }
}
