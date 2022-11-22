import java.util.*;

public class ParallelCourses {
  public int minimumSemesters(int n, int[][] relations) {
    List<Integer>[] graph = new List[n + 1];
    int[] inDegrees = new int[n + 1];
    inDegrees[0] = -1;

    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] relation : relations) {
      int prevCourse = relation[0];
      int nextCourse = relation[1];

      graph[prevCourse].add(nextCourse);
      inDegrees[nextCourse]++;
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (inDegrees[i] == 0) {
        q.offer(i);
      }
    }

    int semesters = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      semesters++;

      for (int i = 1; i < size; i++) {
        int curr = q.poll();

        for (int nextCourse : graph[curr]) {
          inDegrees[nextCourse]--;
          if (inDegrees[nextCourse] == 0) {
            q.offer(nextCourse);
          }
        }
      }
    }

    return semesters;
  }
}
