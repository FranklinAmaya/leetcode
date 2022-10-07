package java_solutions;

import java.util.*;

// https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3912/
public class Solution {
  public int earliestAcq(int[][] logs, int n) {
    Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));

    UnionFind unionFind = new UnionFind(n);

    for (int[] log : logs) {
      unionFind.union(log[1], log[2]);

      if (unionFind.getNumComponents() == 1) {
        return log[0];
      }
    }

    return -1;
  }
}

private class UnionFind {
  private int[] root;
  private int[] rank;
  private int components;

  public UnionFind(int size) {
    root = new int[size];
    rank = new int[size];

    for (int i = 0; i < size; i++) {
      root[i] = i;
      rank[i] = 1;
    }

    components = size;
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

      components--;
    }
  }

  public int getNumComponents() {
    return components;
  }
}
