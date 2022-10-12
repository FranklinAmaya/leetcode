import java.util.*;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class PopulateNextPointers {
  public Node connect(Node root) {
    if (root == null) {
      return root;
    }

    Node leftMost = root;

    while (leftMost.left != null) {
      Node head = leftMost;

      // loop through children
      while (head != null) {
        // establish connection 1
        head.left.next = head.right;

        // establish connection 2
        if (head.next != null)
          head.right.next = head.next.left;

        // go to next node on the same level
        head = head.next;
      }

      // move onto the next level
      leftMost = leftMost.left;
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
