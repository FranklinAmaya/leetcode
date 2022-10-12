import java.util.*;

public class Itinerary {
  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();

    for (List<String> ticket : tickets) {
      if (!graph.containsKey(ticket.get(0))) {
        graph.put(ticket.get(0), new PriorityQueue<>());
      }

      graph.get(ticket.get(0)).add(ticket.get(1));
    }

    // eulerian path, a modification to naive dfs
    Stack<String> stack = new Stack<>();
    List<String> path = new ArrayList<>();
    stack.push("JFK");

    while (!stack.isEmpty()) {
      String curr = stack.peek();

      if (graph.containsKey(curr) && !graph.get(curr).isEmpty())
        stack.push(graph.get(curr).poll());
      else
        path.add(0, stack.pop());
    }

    return path;
  }
}