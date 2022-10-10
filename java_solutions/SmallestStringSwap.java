import java.util.*;

public class SmallestStringSwap {
  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    int n = s.length();
    UnionFind unionFind = new UnionFind(n);

    for (List<Integer> pair : pairs) {
      unionFind.union(pair.get(0), pair.get(1));
    }

    // key = root, value = list of nodes in that component sorted
    Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int root = unionFind.find(i);
      if (!map.containsKey(root)) {
        map.put(root, new PriorityQueue<Character>());
      }

      map.get(root).add(s.charAt(i));
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      int root = unionFind.find(i);
      sb.append(map.get(root).poll());
    }

    return sb.toString();
  }

  class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];

      for (int i = 0; i < size; i++) {
        root[i] = i;
        rank[i] = 1;
      }
    }

    public int find(int x) {
      if (root[x] == x)
        return x;

      return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      if (rootX != rootY) {
        if (rank[rootX] > rank[rootY])
          root[rootY] = rootX;
        else if (rank[rootX] < rank[rootY])
          root[rootX] = rootY;
        else {
          root[rootX] = rootY;
          rank[rootY]++;
        }
      }
    }
  }

}
