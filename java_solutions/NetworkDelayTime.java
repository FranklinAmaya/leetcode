import java.util.*;

//https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime {
  public int networkDelayTime(int[][] times, int n, int k) {
    // build the graph
    Map<Integer, List<Pair>> graph = new HashMap<>();

    // graph[src][dest] = weight
    for (int[] time : times) {
      int src = time[0];
      int dest = time[1];
      int travelTime = time[2];

      if (!graph.containsKey(src)) {
        graph.put(src, new ArrayList<>());
      }

      graph.get(src).add(new Pair(dest, travelTime));
    }

    // dijkstra
    int[] costs = new int[n + 1];
    Arrays.fill(costs, Integer.MAX_VALUE);
    costs[0] = -1;
    costs[k] = 0;

    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
    pq.add(new Pair(k, 0));

    while (!pq.isEmpty()) {
      Pair curr = pq.poll();

      if (!graph.containsKey(curr.node) || curr.time > costs[curr.node]) {
        continue;
      }

      for (Pair neighbor : graph.get(curr.node)) {
        if (neighbor.time + curr.time < costs[neighbor.node]) {
          costs[neighbor.node] = neighbor.time + curr.time;
          pq.add(new Pair(neighbor.node, costs[neighbor.node]));
        }
      }
    }

    int max = -1;
    for (int i = 1; i < costs.length; i++) {
      max = Math.max(costs[i], max);
    }

    return max != Integer.MAX_VALUE ? max : -1;
  }

  class Pair {
    int node;
    int time;

    public Pair(int node, int time) {
      this.node = node;
      this.time = time;
    }
  }
}

class Main {
  public static void main(String[] args) {
    int[][] times = new int[][] { { 1, 2, 1 }, { 2, 3, 2 }, { 1, 3, 4 } };

    NetworkDelayTime network = new NetworkDelayTime();

    System.out.println(network.networkDelayTime(times, 3, 1));
  }
}