import java.util.ArrayList;
import java.util.*;

public class ConnectAllPoints {
  // kruskals algorithm
  // 1) Ascending Sort all edges by their weight
  // 2) Add edges in that order into the MST. Skip the edges that would produce
  // cycles in the MST
  // 3) Repeat step 2 until N-1 edges are added

  public int minCostConnectPoints(int[][] points) {
    ArrayList<int[]> edges = new ArrayList<>();

    // Storing all edges of our complete graph
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        // weight is manhattan distance
        int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);

        int[] edge = { weight, i, j };
        edges.add(edge);
      }
    }

    // Sort all edges by their weight in increasing order
    Collections.sort(edges, (a, b) -> Integer.compare(a[0], b[0]));

    // add edges by their increasing order until n-1 edges are added
    int cost = 0;
    UnionFind uf = new UnionFind(points.length);
    for (int i = 0, edgesUsed = 0; i < edges.size() && edgesUsed < points.length - 1; i++) {
      int w = edges.get(i)[0];
      int n1 = edges.get(i)[1];
      int n2 = edges.get(i)[2];

      if (uf.union(n1, n2)) {
        cost += w;
        edgesUsed++;
      }
    }

    return cost;
  }

  class UnionFind {
    int[] root;
    int[] rank;

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

    public boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      // this will avoid
      if (rootX == rootY)
        return false;

      if (rank[rootX] < rank[rootY]) {
        root[rootY] = rootX;
      } else if (rank[rootX] > rank[rootY]) {
        root[rootX] = rootY;
      } else {
        root[rootY] = rootX;
        rank[rootX]++;
      }

      return true;
    }
  }
}
