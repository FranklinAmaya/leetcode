import java.util.*;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class PopulateNextPointers {

  public Node connect(Node root) {
    Queue<Node> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        Node curr = q.poll();

        if (i < size - 1)
          curr.next = q.peek();

        if (curr.left != null)
          q.offer(curr.left);

        if (curr.right != null)
          q.offer(curr.right);
      }
    }

    return root;
  }

  // Definition for a Node.
  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  };

}
