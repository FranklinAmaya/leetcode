import java.util.*;

// https://leetcode.com/problems/alien-dictionary/
public class AlienDictionary {
  public String alienOrder(String[] words) {
    List<Integer>[] graph = new ArrayList[26];
    int[] inDegrees = new int[26];
    for (int i = 0; i < 26; i++) {
      inDegrees[i] = -1;
      graph[i] = new ArrayList<>();
    }

    for (String word : words) {
      for (char c : word.toCharArray()) {
        inDegrees[c - 'a'] = 0;
      }
    }

    for (int i = 0; i < words.length - 1; i++) {
      String first = words[i];
      String second = words[i + 1];

      if (first.length() > second.length() && first.startsWith(second)) {
        return "";
      }

      for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
        if (first.charAt(j) != second.charAt(j)) {
          graph[first.charAt(j) - 'a'].add(second.charAt(j) - 'a');
          inDegrees[second.charAt(j) - 'a']++;
          break;
        }
      }
    }

    int total = 0;
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < 26; i++) {
      if (inDegrees[i] == 0) {
        q.offer(i);
      }

      if (inDegrees[i] != -1) {
        total++;
      }
    }

    StringBuilder order = new StringBuilder();
    while (!q.isEmpty()) {
      int curr = q.poll();
      order.append((char) (curr + 'a'));

      for (int neighbor : graph[curr]) {
        inDegrees[neighbor]--;
        if (inDegrees[neighbor] == 0) {
          q.offer(neighbor);
        }
      }
    }

    return order.length() < total ? "" : order.toString();
  }
}
