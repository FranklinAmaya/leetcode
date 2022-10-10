import java.util.*;

public class PathsSourceTarget {
  private void dfs(int[][] graph, List<List<Integer>> allPaths, LinkedList<Integer> path, int current) {
    // base case
    if (current == graph.length - 1) {
      allPaths.add(new ArrayList<>(path));
    }

    // recurse over all the neighbors
    for (int neighbor : graph[current]) {
      path.add(neighbor);
      dfs(graph, allPaths, path, neighbor);
      // pop neighbors off so we could start all over for the next neighbor node
      path.removeLast();
    }
  }

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> allPaths = new ArrayList<>();

    LinkedList<Integer> path = new LinkedList<>();
    path.add(0);
    dfs(graph, allPaths, path, 0);

    return allPaths;
  }
}
