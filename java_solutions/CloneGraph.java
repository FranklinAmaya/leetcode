import java.util.*;

public class CloneGraph {
  private Map<Node, Node> visited = new HashMap<>();

  public Node cloneGraph(Node node) {
    if (node == null) {
      return node;
    }

    if (visited.containsKey(node)) {
      return visited.get(node);
    }

    Node cloneNode = new Node(node.val, new ArrayList<>(node.val));
    visited.put(node, cloneNode);

    for (Node neighbor : node.neighbors) {
      cloneNode.neighbors.add(cloneGraph(neighbor));
    }

    return cloneNode;
  }
}
