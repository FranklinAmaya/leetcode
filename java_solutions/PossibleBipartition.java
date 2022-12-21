import java.util.*;

public class PossibleBipartition {
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
      if (x == root[x])
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
          root[rootY] = rootX;
          rank[rootX] += 1;
        }
      }
    }
  }

  public boolean possibleBipartition(int n, int[][] dislikes) {
    Map<Integer, List<Integer>> adj = new HashMap<>();
    for (int i = 1; i <= n; i++) {
      adj.put(i, new ArrayList<>());
    }

    for (int[] dislike : dislikes) {
      adj.get(dislike[0]).add(dislike[1]);
      adj.get(dislike[1]).add(dislike[0]);
    }

    UnionFind dsu = new UnionFind(n + 1);
    for (int i = 1; i <= n; i++) {
      if (!adj.containsKey(i)) {
        continue;
      }

      for (int neighbor : adj.get(i)) {
        if (dsu.find(i) == dsu.find(neighbor)) {
          return false;
        }
        dsu.union(adj.get(i).get(0), neighbor);
      }
    }

    return true;
  }
}
