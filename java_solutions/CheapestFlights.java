class CheapestFlights {
  // using this as infinity to prevent overflow
  final int INF = 0x3f3f3f3f;

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    if (src == dst) {
      return 0;
    }

    // initialize graph
    int[] prev = new int[n];
    int[] curr = new int[n];
    for (int i = 0; i < n; i++) {
      prev[i] = INF;
      curr[i] = INF;
    }

    prev[src] = 0;

    // relax edges repeatedly k + 1
    for (int i = 1; i < k + 2; i++) {
      curr[src] = 0;
      // iterate through all the edges
      for (int[] edges : flights) {
        int u = edges[0];
        int v = edges[1];
        int w = edges[2];

        curr[v] = Math.min(curr[v], prev[u] + w);
      }

      prev = curr.clone();
    }

    return curr[dst] != INF ? curr[dst] : -1;
  }
}