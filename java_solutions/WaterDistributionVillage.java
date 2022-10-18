import java.util.*;

// https://leetcode.com/problems/optimize-water-distribution-in-a-village/
public class WaterDistributionVillage {
  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    // add virtual edges to represent wells as pipes
    PriorityQueue<int[]> pipeCosts = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    for (int i = 0; i < wells.length; i++) {
      pipeCosts.add(new int[] { 0, i + 1, wells[i] });
    }

    // add the rest of pipes
    for (int i = 0; i < pipes.length; i++) {
      pipeCosts.add(pipes[i]);
    }

    // account for extra virtual node when declaring union find constructor
    UnionFind uf = new UnionFind(n + 1);
    int minCost = 0;

    while (!pipeCosts.isEmpty()) {
      int[] pipe = pipeCosts.poll();

      if (uf.union(pipe[0], pipe[1])) {
        minCost += pipe[2];
      }
    }

    return minCost;
  }

  class UnionFind {
    int[] rank;
    int[] root;

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

      // recursively find the parent of x
      return root[x] = find(root[x]);
    }

    public boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      // if they both have the same root return
      if (rootX == rootY) {
        return false;
      }

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