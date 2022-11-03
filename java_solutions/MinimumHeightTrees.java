import java.util.*;

// https://leetcode.com/problems/minimum-height-trees/
public class MinimumHeightTrees {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n < 2) {
      List<Integer> ans = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        ans.add(i);
      }

      return ans;
    }

    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    int[] inDegree = new int[n];
    for (int[] edge : edges) {
      inDegree[edge[0]]++;
      inDegree[edge[1]]++;
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    // push all the leaves onto the queue
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 1) {
        q.offer(i);
      }
    }

    int remainingNodes = n;
    while (remainingNodes > 2) {
      remainingNodes -= q.size();
      int l = q.size();

      // update the indegree of all the leaves and push the new leaves on q
      for (int i = 0; i < l; i++) {
        int curr = q.poll();

        for (Integer child : graph.get(curr)) {
          inDegree[child]--;

          if (inDegree[child] == 1) {
            q.offer(child);
          }
        }
      }
    }

    List<Integer> result = new ArrayList<>();
    result.addAll(q);

    return result;
  }
}
